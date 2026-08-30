package com.example.weatherapp.home.data.mappers

import com.example.weatherapp.home.data.model.ForecastDayDto
import com.example.weatherapp.home.domain.model.ForecastDomainModel

object ForecastDomainMapper {
    fun mapToDomain(data: List<ForecastDayDto>): ForecastDomainModel {
        return ForecastDomainModel(
            forecastDay = data.map { ForecastDayDomainMapper.mapToDomain(it) }
        )
    }
}