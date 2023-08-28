package com.example.simplestopwatch.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simplestopwatch.databinding.FragmentStopwatchSecondBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StopwatchSecondFragment : Fragment() {

    private var _binding: FragmentStopwatchSecondBinding? = null
    private val binding get() = _binding!!

    private val model by viewModel<SecondFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStopwatchSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.subscribe().observe(viewLifecycleOwner) { formattedTime ->
            binding.textTimeSecondFrag.text = formattedTime
        }

        initButtons()
    }

    private fun initButtons() {
        binding.buttonStartSecondFrag.setOnClickListener {
            model.startStopwatch()
        }

        binding.buttonPauseSecondFrag.setOnClickListener {
            model.pauseStopwatch()
        }

        binding.buttonStopSecondFrag.setOnClickListener {
            model.stopStopwatch()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StopwatchSecondFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}