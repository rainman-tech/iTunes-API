package com.rain.itunes_api.repository

import com.rain.itunes_api.db.Visit
import com.rain.itunes_api.db.VisitDao
import javax.inject.Inject

//Repository used for room database visit_db
class VisitRepository @Inject constructor(
    val visitDao: VisitDao
) {

    suspend fun insertVisit(visit: Visit) = visitDao.insertVisit(visit)

    fun getAllVisitsSortedByDate() = visitDao.getAllVisitsSortedByDate()

}