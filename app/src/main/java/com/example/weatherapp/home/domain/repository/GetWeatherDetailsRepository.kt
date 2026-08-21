package com.example.weatherapp.home.domain.repository

import com.example.weatherapp.home.domain.model.ApiResultStatus
import com.example.weatherapp.home.domain.model.GetWeatherDetailsDomainResponse

interface GetWeatherDetailsRepository {
    suspend fun getWeatherDetails(
        latitudeAndLongitude: String,
        days: Int,
        hour: Int
    ): ApiResultStatus<GetWeatherDetailsDomainResponse>
}