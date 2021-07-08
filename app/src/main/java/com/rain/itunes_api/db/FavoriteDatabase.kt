package com.rain.itunes_api.db

import androidx.room.Database
import androidx.room.RoomDatabase

//An abstract class for creating an instance of the database with room
@Database(
    entities = [Favorite::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao

}