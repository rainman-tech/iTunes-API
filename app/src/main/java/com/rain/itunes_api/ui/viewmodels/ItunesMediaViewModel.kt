package com.rain.itunes_api.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rain.itunes_api.db.Favorite
import com.rain.itunes_api.domain.model.ItunesMedia
import com.rain.itunes_api.repository.FavoriteRepository
import com.rain.itunes_api.repository.ItunesMediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItunesMediaViewModel @ViewModelInject constructor(
    private val repository: ItunesMediaRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    //Create a mutable live data and immutable live data to restrict fragment or activities
    //from changing data from API
    private val _itunesMediaItems = MutableLiveData<List<ItunesMedia>>()
    val itunesMediaItems: LiveData<List<ItunesMedia>> = _itunesMediaItems

    //Call repository function from a coroutine scope since repo has suspend function
    init {
        search("Star", "us", "movie")
    }

    //Perform search request with the itunes-api and cross check values with favorites
    //saved in the room database, update value of domain model with the result of comparison
    fun search(term: String, country: String, media: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.search(
                term,
                country,
                media)

            for (itunesMedia in result) {
                if (favoriteRepository.checkFavoriteInstances(itunesMedia.trackId!!) > 0) {
                    itunesMedia.isFavorite = true
                }
            }

            _itunesMediaItems.postValue(result)
        }
    }

    //Set itunesMedia value to true or false and save or delete the favorite to room database
    fun updateItunesMedia(itunesMedia: ItunesMedia) {
        val position = _itunesMediaItems.value!!.indexOf(itunesMedia)

        if (_itunesMediaItems.value!![position].isFavorite) {
            _itunesMediaItems.value!![position].isFavorite = false
            deleteFavorite(Favorite(itunesMedia.trackId, itunesMedia.trackName))
        } else {
            _itunesMediaItems.value!![position].isFavorite = true
            addFavorite(Favorite(itunesMedia.trackId, itunesMedia.trackName))
        }
    }

    private fun addFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.insertFavorite(favorite)
        }
    }

    private fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteFavorite(favorite)
        }
    }

}