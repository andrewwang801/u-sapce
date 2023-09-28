package com.example.uspace.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.legacy.widget.Space
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uspace.R
import com.example.uspace.databinding.FragmentCrewBinding
import com.example.uspace.network.repository.SpaceResult
import com.example.uspace.ui.adapters.CrewAdapter
import com.example.uspace.ui.adapters.CrewClickListener
import com.example.uspace.ui.models.CrewAgency
import com.example.uspace.ui.viewmodels.CrewViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CrewFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val crewViewModel: CrewViewModel by viewModels { viewModelFactory }

    lateinit var adapter: CrewAdapter
    private lateinit var binding: FragmentCrewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        adapter = CrewAdapter(CrewClickListener {
            Toast.makeText(requireContext(), it.agency, Toast.LENGTH_SHORT).show()
        })
        binding = FragmentCrewBinding.inflate(inflater)

        binding.filter.setOnCheckedChangeListener() { radioGroup, i ->
            when (i) {
                R.id.nasa -> { crewViewModel.changeCrewAgency(CrewAgency.NASA) }
                R.id.spacex -> { crewViewModel.changeCrewAgency(CrewAgency.SPACEX) }
                R.id.jsxa -> { crewViewModel.changeCrewAgency(CrewAgency.JAXA) }
                R.id.esa -> { crewViewModel.changeCrewAgency(CrewAgency.ESA) }
                else -> {}
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = crewViewModel

        setupRecyclerView()

        crewViewModel.crew.observe(viewLifecycleOwner) {
            handleResult(it)
        }

        crewViewModel.crewAgency.observe(viewLifecycleOwner) {
            adapter.submitList(crewViewModel.getFilteredCrew())
        }

        crewViewModel.getCrew()
    }

    private fun setupRecyclerView() {
        binding.apply {
            crewList.adapter = adapter
            crewList.layoutManager = LinearLayoutManager(requireContext())
            crewList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

    }

    private fun handleResult(result: SpaceResult) {
        when (result) {
            SpaceResult.Error -> Snackbar.make(binding.root, R.string.error_loading_data, Snackbar.LENGTH_SHORT).setAction(R.string.try_again) {
                crewViewModel.getCrew()
            }.show()

            is SpaceResult.CrewResult -> {
                adapter.submitList(result.crew)
            }
        }
    }
}