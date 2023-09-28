package com.example.uspace.network.repository

import kotlinx.coroutines.flow.Flow


interface SpaceRepository {

    suspend fun getRockets(): Flow<SpaceResult>

    suspend fun getCrew(): Flow<SpaceResult>
}