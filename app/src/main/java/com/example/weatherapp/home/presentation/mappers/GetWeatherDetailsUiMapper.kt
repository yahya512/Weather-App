package com.example.weatherapp.home.presentation.mappers

import com.example.weatherapp.home.domain.model.GetWeatherDetailsDomainResponse
import com.example.weatherapp.home.presentation.model.GetWeatherDetailsUiResponse

object GetWeatherDetailsUiMapper {
    fun mapToUi(data: GetWeatherDetailsDomainResponse): GetWeatherDetailsUiResponse {
        return GetWeatherDetailsUiResponse(
            location = LocationUiMapper.mapToUi(data.location),
            current = CurrentUiMapper.mapToUi(data.current),
            forecast = ForecastUIMapper.mapToUi(data.forecast.forecastDay)
        )
    }
}