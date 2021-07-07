package com.rain.itunes_api.db

import androidx.room.Database
import androidx.room.RoomDatabase

//An abstract class for creating an instance of the database with room
@Database(
    entities = [Visit::class],
    version = 1,
    exportSchema = false
)
abstract class VisitDatabase : RoomDatabase() {

    abstract fun getVisitDao(): VisitDao

}