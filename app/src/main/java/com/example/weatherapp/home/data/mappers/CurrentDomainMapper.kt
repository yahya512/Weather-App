package com.example.weatherapp.home.data.mappers

import com.example.weatherapp.home.data.model.CurrentDto
import com.example.weatherapp.home.domain.model.CurrentWeatherDomainModel

object CurrentDomainMapper {
    fun mapToDomain(data: CurrentDto): CurrentWeatherDomainModel {
        return CurrentWeatherDomainModel(
            lastUpdate = data.lastUpdated,
            tempC = data.tempC,
            humidity = data.humidity,
            windKph = data.windKph,
            feelsLike = data.feelsLike,
            condition = ConditionDomainMapper.mapToDomain(data.condition)
        )
    }
}