package com.example.simplestopwatch.di

import com.example.simplestopwatch.model.stopwatch.ElapsedTimeCalculator
import com.example.simplestopwatch.model.stopwatch.StopwatchListOrchestrator
import com.example.simplestopwatch.model.stopwatch.StopwatchStateCalculator
import com.example.simplestopwatch.model.stopwatch.StopwatchStateHolder
import com.example.simplestopwatch.model.time.TimestampMillisecondsFormatter
import com.example.simplestopwatch.model.time.TimestampProvider
import com.example.simplestopwatch.view.MainTimestampProvider
import com.example.simplestopwatch.view.fragments.FirstFragmentViewModel
import com.example.simplestopwatch.view.fragments.SecondFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {

    single(named("firstFragmentOrchestrator")) {
        StopwatchListOrchestrator(
            stopwatchStateHolder = get(named("firstFragmentStateHolder")),
            scope = get(named("firstCoroutineScope"))
        )
    }

    single(named("secondFragmentOrchestrator")) {
        StopwatchListOrchestrator(
            stopwatchStateHolder = get(named("secondFragmentStateHolder")),
            scope = get(named("secondCoroutineScope"))
        )
    }

    single(named("firstFragmentStateHolder")) {
        StopwatchStateHolder(
            stopwatchStateCalculator = get(),
            elapsedTimeCalculator = get(),
            timestampMillisecondsFormatter = get()
        )
    }

    single(named("secondFragmentStateHolder")) {
        StopwatchStateHolder(
            stopwatchStateCalculator = get(),
            elapsedTimeCalculator = get(),
            timestampMillisecondsFormatter = get()
        )
    }

    single(named("firstCoroutineScope")) {
        CoroutineScope(Dispatchers.Default + SupervisorJob())
    }

    single(named("secondCoroutineScope")) {
        CoroutineScope(Dispatchers.Default + SupervisorJob())
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
        FirstFragmentViewModel(stopwatchListOrchestrator = get(named("firstFragmentOrchestrator")))
    }

    viewModel {
        SecondFragmentViewModel(stopwatchListOrchestrator = get(named("secondFragmentOrchestrator")))
    }
}