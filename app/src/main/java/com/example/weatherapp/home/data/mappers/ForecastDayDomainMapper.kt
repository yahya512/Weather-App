package com.example.weatherapp.home.data.mappers

import com.example.weatherapp.home.data.model.ForecastDayDto
import com.example.weatherapp.home.domain.model.ForecastDayDomainModel

object ForecastDayDomainMapper {
    fun mapToDomain(data: ForecastDayDto): ForecastDayDomainModel {
        return ForecastDayDomainModel(
            date = data.date,
            day = DayDomainMapper.mapRoDomain(data.day)
        )
    }
}