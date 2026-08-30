package com.example.weatherapp.home.presentation.model

data class GetWeatherDetailsUiResponse(
    val location: LocationUiModel,
    val current: CurrentWeatherUiModel,
    val forecast: ForecastUiModel
)
