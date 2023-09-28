package com.example.uspace.network.repository

import com.example.uspace.ui.models.Crew
import com.example.uspace.ui.models.Rocket

sealed class SpaceResult {
    object Loading: SpaceResult()
    object Error: SpaceResult()

    data class RocketResult(val rockets: List<Rocket>) : SpaceResult()
    data class CrewResult(val crew: List<Crew>) : SpaceResult()
}