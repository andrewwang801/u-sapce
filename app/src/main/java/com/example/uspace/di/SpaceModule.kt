package com.example.uspace.di

import com.example.uspace.network.repository.SpaceRepository
import com.example.uspace.network.repository.SpaceRepositoryImpl
import com.example.uspace.network.services.SpaceService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class SpaceModule {

//    @Singleton
    @Provides
    fun provideSpaceService() : SpaceService {
        return Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpaceService::class.java)
    }

//    @Singleton
    @Provides
    fun provideSpaceRepository(service: SpaceService): SpaceRepository = SpaceRepositoryImpl(service)
}