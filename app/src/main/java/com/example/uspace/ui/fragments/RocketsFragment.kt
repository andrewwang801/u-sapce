package com.example.uspace.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uspace.R
import com.example.uspace.databinding.FragmentRocketsBinding
import com.example.uspace.network.repository.SpaceResult
import com.example.uspace.ui.adapters.RocketsAdapter
import com.example.uspace.ui.viewmodels.RocketsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [RocketsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class RocketsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RocketsViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentRocketsBinding
    private lateinit var adapter: RocketsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = RocketsAdapter()
        binding = FragmentRocketsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        // Observe
        viewModel.rockets.observe(viewLifecycleOwner) { result ->
            binding.loading = false
            handleResult(result)
        }

        // Get Data
        binding.loading = true
        viewModel.getRockets()
    }

    private fun setupRecyclerView() {
        // setup recycler view
        binding.apply {
            rocketsList.layoutManager = LinearLayoutManager(requireContext())
            rocketsList.adapter = adapter
            rocketsList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

    }

    private fun handleResult(result: SpaceResult) {

        when (result) {
            SpaceResult.Error ->  {
                Snackbar.make(binding.root, R.string.error_loading_data, Snackbar.LENGTH_SHORT).setAction(R.string.try_again) {
                    viewModel.getRockets()
                }.show()
            }

            is SpaceResult.RocketResult -> {
                adapter.submitList(result.rockets)
            }
        }
    }
}