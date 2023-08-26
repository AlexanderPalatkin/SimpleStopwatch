package com.example.simplestopwatch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplestopwatch.model.stopwatch.StopwatchListOrchestrator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class StopwatchViewModel(
    private val stopwatchListOrchestrator: StopwatchListOrchestrator
) : ViewModel(), StopwatchViewModelContract {

    internal val formattedTime: MutableLiveData<String> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.Main + SupervisorJob()) {
            stopwatchListOrchestrator.ticker.collect {elapsedTime ->
                formattedTime.value = elapsedTime
            }
        }
    }

    override fun startStopwatch() {
        stopwatchListOrchestrator.start()
    }

    override fun pauseStopwatch() {
        stopwatchListOrchestrator.pause()
    }

    override fun stopStopwatch() {
        stopwatchListOrchestrator.stop()
    }
}