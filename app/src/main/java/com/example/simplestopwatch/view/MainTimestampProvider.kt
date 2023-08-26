package com.example.simplestopwatch.view

import com.example.simplestopwatch.model.time.TimestampProvider

class MainTimestampProvider: TimestampProvider {

    override fun getMilliseconds(): Long {
        return System.currentTimeMillis()
    }
}