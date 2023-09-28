package com.example.uspace.network.repository

import android.widget.Space
import com.example.uspace.network.dto.toCrew
import com.example.uspace.network.dto.toRocket
import com.example.uspace.network.services.SpaceService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SpaceRepositoryImpl (
    private val service: SpaceService
) : SpaceRepository {
    override suspend fun getRockets(): Flow<SpaceResult> = flow {
        try {
            emit(SpaceResult.Loading)
            val response = service.getRockets()

            if (response.isSuccessful) {
                emit(SpaceResult.RocketResult(response.body()?.map { it.toRocket() }
                    ?: emptyList()))
            } else {
                emit(SpaceResult.Error)
            }
        } catch (exception: Exception) {
            emit(SpaceResult.Error)
        }
    }

    override suspend fun getCrew(): Flow<SpaceResult> = flow {
        try {
            var response = service.getCrew()

            if (response.isSuccessful) {
                emit(SpaceResult.CrewResult(response.body()?.map { it.toCrew() } ?: emptyList()))

            } else {
                emit(SpaceResult.Error)
            }

        } catch (exception: Exception) {
            emit(SpaceResult.Error)
        }
    }
}