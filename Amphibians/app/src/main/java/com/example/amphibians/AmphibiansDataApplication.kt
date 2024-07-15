package com.example.amphibians

import android.app.Application
import com.example.amphibians.data.AppContainer
import com.example.amphibians.data.DefaultAppContainer

class AmphibiansDataApplication : Application(){
    lateinit var container: AppContainer

    override fun onCreate() {
        container = DefaultAppContainer()
        super.onCreate()
    }
}