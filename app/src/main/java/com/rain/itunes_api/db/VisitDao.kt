package com.rain.itunes_api.db

import androidx.lifecycle.LiveData
import androidx.room.*

//Room database DAO(data access object) which contains queries for interacting with Database
@Dao
interface VisitDao {

    //Creating an object in the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVisit(visit: Visit)

    //Retrieving all instances the user opened the application
    @Query("SELECT * FROM visit_table ORDER BY timestamp ASC")
    fun getAllVisitsSortedByDate(): LiveData<List<Visit>>

}