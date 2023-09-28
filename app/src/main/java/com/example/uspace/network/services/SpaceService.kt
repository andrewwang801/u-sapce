package com.example.uspace.network.services

import com.example.uspace.network.dto.CrewDTO
import com.example.uspace.network.dto.RocketDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface SpaceService {

    @GET("v4/rockets")
    suspend fun getRockets() : Response<List<RocketDTO>>

    @GET("v4/crew")
    suspend fun getCrew() : Response<List<CrewDTO>>
}

//private val retrofit = Retrofit.Builder()
//    .baseUrl("https://api.spacexdata.com/")
//    .addConverterFactory(GsonConverterFactory.create())
//    .build()
//
//object SpaceAPI {
//    val spaceService : SpaceService = retrofit.create(SpaceService::class.java)
//}