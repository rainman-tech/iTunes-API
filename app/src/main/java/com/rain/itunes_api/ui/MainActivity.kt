package com.rain.itunes_api.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rain.itunes_api.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ITunesAPI)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}