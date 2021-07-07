package com.rain.itunes_api.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.rain.itunes_api.R
import com.rain.itunes_api.db.Visit
import com.rain.itunes_api.ui.viewmodels.VisitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val visitViewModel: VisitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ITunesAPI)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Use visit viewmodel to insert data to database(insert time of opening the application)
        visitViewModel.insertVisit(Visit(System.currentTimeMillis()))
        Log.d("mainActivity", "Application visit added.")
    }
}