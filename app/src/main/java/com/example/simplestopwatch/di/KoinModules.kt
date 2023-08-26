package com.example.simplestopwatch.di

import com.example.simplestopwatch.model.stopwatch.ElapsedTimeCalculator
import com.example.simplestopwatch.model.stopwatch.StopwatchListOrchestrator
import com.example.simplestopwatch.model.stopwatch.StopwatchStateCalculator
import com.example.simplestopwatch.model.stopwatch.StopwatchStateHolder
import com.example.simplestopwatch.model.time.TimestampMillisecondsFormatter
import com.example.simplestopwatch.model.time.TimestampProvider
import com.example.simplestopwatch.view.MainTimestampProvider
import com.example.simplestopwatch.viewmodel.StopwatchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val application = module {

    single {
        StopwatchListOrchestrator(
            stopwatchStateHolder = get(),
            scope = get(named("mainCoroutineScope"))
        )
    }

    single {
        StopwatchStateHolder(
            stopwatchStateCalculator = get(),
            elapsedTimeCalculator = get(),
            timestampMillisecondsFormatter = get()
        )
    }

    single(named("mainCoroutineScope")) {
        CoroutineScope(Dispatchers.Main + SupervisorJob())
    }

    single {
        ElapsedTimeCalculator(
            timestampProvider = get()
        )
    }

    single {
        TimestampMillisecondsFormatter()
    }

    single<TimestampProvider> {
        MainTimestampProvider()
    }

    single {
        StopwatchStateCalculator(
            timestampProvider = get(),
            elapsedTimeCalculator = get()
        )
    }

}

val mainScreen = module {

    viewModel {
        StopwatchViewModel(stopwatchListOrchestrator = get())
    }
}
