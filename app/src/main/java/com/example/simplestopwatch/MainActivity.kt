package com.example.simplestopwatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simplestopwatch.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(
                    timestampProvider
                )
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(Dispatchers.Main + SupervisorJob())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {

            stopwatchListOrchestrator.ticker.collect() {
                binding.textTime.text = it
            }
        }

        binding.buttonStart.setOnClickListener {
            stopwatchListOrchestrator.start()
        }

        binding.buttonPause.setOnClickListener {
            stopwatchListOrchestrator.pause()
        }

        binding.buttonStop.setOnClickListener {
            stopwatchListOrchestrator.stop()
        }
    }
}