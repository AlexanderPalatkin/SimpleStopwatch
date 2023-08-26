package com.example.simplestopwatch

interface TimestampProvider {

    fun getMilliseconds(): Long
}