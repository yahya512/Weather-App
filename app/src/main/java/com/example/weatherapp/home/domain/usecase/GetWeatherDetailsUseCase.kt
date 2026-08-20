package com.example.weatherapp.home.domain.usecase

import com.example.weatherapp.home.domain.model.ApiResultStatus
import com.example.weatherapp.home.domain.model.GetWeatherDetailsResponse
import com.example.weatherapp.home.domain.repository.GetWeatherDetails
import javax.inject.Inject

class GetWeatherDetailsUseCase @Inject constructor(val repository: GetWeatherDetails) {
    suspend operator fun invoke(): ApiResultStatus<GetWeatherDetailsResponse> {
        return repository.getWeatherDetails()
    }
}