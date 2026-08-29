package com.example.weatherapp.di

import com.example.weatherapp.home.domain.repository.GetWeatherDetails
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
    fun provideRepositoryForGetWeatherDetailsUseCase(repository: GetWeatherDetails): GetWeatherDetailsUseCase {
        return GetWeatherDetailsUseCase(repository)
    }
}