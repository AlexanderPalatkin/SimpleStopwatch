package com.example.simplestopwatch.model.stopwatch

import com.example.simplestopwatch.model.time.TimestampProvider

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider
) {

    fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()

        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }

        return timePassedSinceStart + state.elapsedTime
    }
}