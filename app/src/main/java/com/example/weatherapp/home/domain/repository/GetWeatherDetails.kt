package com.example.weatherapp.home.domain.repository

import com.example.weatherapp.home.domain.model.ApiResultStatus
import com.example.weatherapp.home.domain.model.GetWeatherDetailsResponse

interface GetWeatherDetails {
    suspend fun getWeatherDetails(): ApiResultStatus<GetWeatherDetailsResponse>
}