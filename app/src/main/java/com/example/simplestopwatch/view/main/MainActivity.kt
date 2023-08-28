package com.example.simplestopwatch.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.simplestopwatch.R
import com.example.simplestopwatch.databinding.ActivityMainBinding
import com.example.simplestopwatch.view.fragments.StopwatchFirstFragment
import com.example.simplestopwatch.view.fragments.StopwatchSecondFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFragments()
    }

    private fun initFragments() {
        replaceFragment(supportFragmentManager, R.id.container_first_fragment, StopwatchFirstFragment.newInstance())
        replaceFragment(supportFragmentManager, R.id.container_second_fragment, StopwatchSecondFragment.newInstance())
    }

    private fun replaceFragment(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment
    ) {
        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }
}