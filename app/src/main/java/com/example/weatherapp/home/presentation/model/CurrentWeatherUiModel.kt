package com.example.weatherapp.home.presentation.model

data class CurrentWeatherUiModel(
    val lastUpdate: String,
    val tempC: Double,
    val feelsLike: Double,
    val humidity: Int,
    val windKph: Double,
    val condition: ConditionUiModel
)
