package com.example.simplestopwatch.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simplestopwatch.databinding.ActivityMainBinding
import com.example.simplestopwatch.viewmodel.StopwatchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val model by viewModel<StopwatchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.formattedTime.observe(this) { formattedTime ->
            binding.textTime.text = formattedTime
        }

        initButtons()
    }

    private fun initButtons() {
        binding.buttonStart.setOnClickListener {
            model.startStopwatch()
        }

        binding.buttonPause.setOnClickListener {
            model.pauseStopwatch()
        }

        binding.buttonStop.setOnClickListener {
            model.stopStopwatch()
        }
    }
}