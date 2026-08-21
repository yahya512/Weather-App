package com.example.weatherapp.home.domain.model

data class GetWeatherDetailsDomainResponse(
    val location: LocationDomainModel,
    val current: CurrentDomainModel,
    val forecast: ForecastDomainModel
)