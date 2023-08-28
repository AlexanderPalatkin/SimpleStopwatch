package com.example.simplestopwatch.view.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplestopwatch.model.stopwatch.StopwatchListOrchestrator
import com.example.simplestopwatch.viewmodel.StopwatchViewModelContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SecondFragmentViewModel(
    private val stopwatchListOrchestrator: StopwatchListOrchestrator
) : ViewModel(), StopwatchViewModelContract {

    private val formattedTime: MutableLiveData<String> = MutableLiveData()

    fun subscribe(): LiveData<String> {
        return formattedTime
    }

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