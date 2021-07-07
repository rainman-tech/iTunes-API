package com.rain.itunes_api.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rain.itunes_api.db.Visit
import com.rain.itunes_api.repository.VisitRepository
import kotlinx.coroutines.launch

class VisitViewModel @ViewModelInject constructor(
    val visitRepository: VisitRepository
): ViewModel() {

    //Insert data to room db
    fun insertVisit(visit: Visit) = viewModelScope.launch {
        visitRepository.insertVisit(visit)
    }

    //Read data from room db
    val visitsSortedByDate = visitRepository.getAllVisitsSortedByDate()

}