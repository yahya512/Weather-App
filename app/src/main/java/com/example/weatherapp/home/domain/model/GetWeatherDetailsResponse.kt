package com.example.weatherapp.home.domain.model

data class GetWeatherDetailsResponse(
    val location: LocationDomainModel,
    val current: CurrentDomainModel,
    val forecast: ForecastDomainModel
)