package com.rain.itunes_api.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//This will generate the top level components. (Hilt generates the components for you automatically)
@HiltAndroidApp
class BaseApplication : Application()