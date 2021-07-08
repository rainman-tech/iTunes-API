package com.rain.itunes_api.db

import androidx.room.*

//Room database DAO(data access object) which contains queries for interacting with Database
@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite_table where trackId = :id")
    fun checkFavoriteInstances(id: Int): Int

}