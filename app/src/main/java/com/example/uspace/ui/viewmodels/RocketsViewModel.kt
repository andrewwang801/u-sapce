package com.example.uspace.ui.viewmodels

import androidx.lifecycle.*
import com.example.uspace.network.repository.SpaceRepository
import com.example.uspace.network.repository.SpaceResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class RocketsViewModel @Inject constructor(private val repository: SpaceRepository) : ViewModel() {

    private val _rockets = MutableLiveData<SpaceResult>()
    val rockets: LiveData<SpaceResult>
        get() = _rockets

    fun getRockets() {
        viewModelScope.launch {
            repository.getRockets().collect {
                _rockets.value = it
            }
        }
    }

//    class Factory() : ViewModelProvider.Factory {
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(RocketsViewModel::class.java)) {
//                return RocketsViewModel() as T
//            }
//            throw IllegalArgumentException("")
//        }
//    }
}