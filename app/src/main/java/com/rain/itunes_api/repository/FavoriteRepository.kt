package com.rain.itunes_api.repository

import com.rain.itunes_api.db.Favorite
import com.rain.itunes_api.db.FavoriteDao
import javax.inject.Inject

//Repository used for room database favorite_db
class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao,
) {

    suspend fun insertFavorite(favorite: Favorite) {
        favoriteDao.insertFavorite(favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite) {
        favoriteDao.deleteFavorite(favorite)
    }

    fun checkFavoriteInstances(id: Int): Int {
        return favoriteDao.checkFavoriteInstances(id)
    }

}