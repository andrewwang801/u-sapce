package com.example.uspace.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uspace.network.repository.SpaceRepository
import com.example.uspace.network.repository.SpaceResult
import com.example.uspace.ui.models.Crew
import com.example.uspace.ui.models.CrewAgency
import kotlinx.coroutines.launch
import javax.inject.Inject

class CrewViewModel @Inject constructor(private val repository: SpaceRepository) : ViewModel() {

    private val result = mutableListOf<Crew>()

    private val _crew = MutableLiveData<SpaceResult>()
    val crew : LiveData<SpaceResult>
        get() = _crew

    private val _crewAgency: MutableLiveData<CrewAgency> = MutableLiveData<CrewAgency>()
    val crewAgency
        get() = _crewAgency

    fun getCrew() {
        viewModelScope.launch {
            repository.getCrew().collect() {
                if (it is SpaceResult.CrewResult) {
                    result.clear()
                    result.addAll(it.crew)
                }
                _crew.value = it
            }
        }
    }

    fun getFilteredCrew() = result.filter { it.agency.lowercase() == _crewAgency.value?.agency }

    fun changeCrewAgency(value: CrewAgency) {
        _crewAgency.value = value
    }
}