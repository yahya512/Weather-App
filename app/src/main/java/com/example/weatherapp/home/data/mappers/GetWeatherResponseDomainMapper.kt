package com.example.weatherapp.home.data.mappers

import com.example.weatherapp.home.data.model.GetWeatherDetailsDtoResponse
import com.example.weatherapp.home.domain.model.GetWeatherDetailsDomainResponse

object GetWeatherResponseDomainMapper {
    fun mapToDomain(data: GetWeatherDetailsDtoResponse): GetWeatherDetailsDomainResponse {
        return GetWeatherDetailsDomainResponse(
            location = LocationDomainMapper.mapToDomain(data.location),
            current = CurrentDomainMapper.mapToDomain(data.current),
            forecast = ForecastDomainMapper.mapToDomain(data.forecast.forecastDay)
        )
    }
}