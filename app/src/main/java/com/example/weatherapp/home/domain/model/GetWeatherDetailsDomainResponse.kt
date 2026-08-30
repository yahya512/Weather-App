package com.example.weatherapp.home.domain.model

data class GetWeatherDetailsDomainResponse(
    val location: LocationDomainModel,
    val current: CurrentWeatherDomainModel,
    val forecast: ForecastDomainModel
)