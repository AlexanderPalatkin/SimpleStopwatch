package com.example.simplestopwatch.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simplestopwatch.databinding.FragmentStopwatchFirstBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class StopwatchFirstFragment : Fragment() {

    private var _binding: FragmentStopwatchFirstBinding? = null
    private val binding get() = _binding!!

    private val model by viewModel<FirstFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStopwatchFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.subscribe().observe(viewLifecycleOwner) { formattedTime ->
            binding.textTimeFirstFrag.text = formattedTime
        }

        initButtons()
    }

    private fun initButtons() {
        binding.buttonStartFirstFrag.setOnClickListener {
            model.startStopwatch()
        }

        binding.buttonPauseFirstFrag.setOnClickListener {
            model.pauseStopwatch()
        }

        binding.buttonStopFirstFrag.setOnClickListener {
            model.stopStopwatch()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StopwatchFirstFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}