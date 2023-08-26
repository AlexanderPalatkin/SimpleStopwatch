package com.example.simplestopwatch.application

import android.app.Application
import com.example.simplestopwatch.di.application
import com.example.simplestopwatch.di.mainScreen
import org.koin.core.context.startKoin

class SimpleStopwatch: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}