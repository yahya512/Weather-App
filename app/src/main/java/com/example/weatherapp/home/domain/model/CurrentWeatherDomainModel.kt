package com.example.weatherapp.home.domain.model


data class CurrentWeatherDomainModel(
    val lastUpdate: String,
    val tempC: Double,
    val feelsLike: Double,
    val humidity: Int,
    val windKph: Double,
    val condition: ConditionDomainModel
)