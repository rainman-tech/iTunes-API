package com.rain.itunes_api.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rain.itunes_api.domain.model.ItunesMedia
import com.rain.itunes_api.repository.ItunesMediaRepository
import kotlinx.coroutines.launch

class ItunesMediaViewModel @ViewModelInject constructor(
    private val repository: ItunesMediaRepository
) : ViewModel() {

    private val _itunesMediaItems = MutableLiveData<List<ItunesMedia>>()
    val itunesMediaItems: LiveData<List<ItunesMedia>> = _itunesMediaItems

    init {
        viewModelScope.launch {
            val result = repository.search(
                "Star",
                "au",
                "movie")
            _itunesMediaItems.value = result
        }
    }

}