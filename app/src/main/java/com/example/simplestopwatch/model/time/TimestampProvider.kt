package com.example.simplestopwatch.model.time

interface TimestampProvider {

    fun getMilliseconds(): Long
}