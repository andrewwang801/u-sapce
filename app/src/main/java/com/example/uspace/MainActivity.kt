package com.example.uspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uspace.databinding.ActivityMainBinding
import com.example.uspace.ui.adapters.TabsAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TabsAdapter(this)
        binding.pager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.rockets_tab)
                1 -> tab.text = getString(R.string.crew_tab)
                2 -> tab.text = getString(R.string.dragons_tab)
                3 -> tab.text = getString(R.string.capsules_tab)
            }
        }.attach()
    }
}