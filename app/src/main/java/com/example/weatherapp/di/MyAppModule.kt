package com.example.weatherapp.di

import com.example.weatherapp.home.data.remote.GetWeatherDetailsApi
import com.example.weatherapp.home.data.repository.GetWeatherRepositoryImpl
import com.example.weatherapp.home.domain.repository.GetWeatherDetailsRepository
import com.example.weatherapp.home.domain.usecase.GetWeatherDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MyAppModule {

    // provide repository for GetWeatherDetailsUseCase
    @Provides
    fun provideRepositoryForGetWeatherDetailsUseCase(repository: GetWeatherDetailsRepository): GetWeatherDetailsUseCase {
        return GetWeatherDetailsUseCase(repository)
    }

    // provide apiService for RepositoryImpl
    @Provides
    fun provideGetWeatherDetailsApiForGetWeatherRepositoryImpl(apiService: GetWeatherDetailsApi): GetWeatherDetailsRepository {
        return GetWeatherRepositoryImpl(apiService)
    }
}