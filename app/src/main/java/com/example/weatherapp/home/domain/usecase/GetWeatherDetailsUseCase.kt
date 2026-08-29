package com.example.weatherapp.home.domain.usecase

import com.example.weatherapp.home.domain.model.ApiResultStatus
import com.example.weatherapp.home.domain.model.GetWeatherDetailsDomainResponse
import com.example.weatherapp.home.domain.repository.GetWeatherDetailsRepository
import javax.inject.Inject

class GetWeatherDetailsUseCase @Inject constructor(val repository: GetWeatherDetailsRepository) {
    suspend operator fun invoke(
        latitudeAndLongitude: String,
        days: Int,
        hour: Int
    ): ApiResultStatus<GetWeatherDetailsDomainResponse> {
        return repository.getWeatherDetails(latitudeAndLongitude, days, hour)
    }
}