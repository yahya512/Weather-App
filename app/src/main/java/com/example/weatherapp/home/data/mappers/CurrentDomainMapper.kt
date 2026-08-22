package com.example.weatherapp.home.data.mappers

import com.example.weatherapp.home.data.model.CurrentDto
import com.example.weatherapp.home.domain.model.CurrentDomainModel

object CurrentDomainMapper {
    fun mapToDomain(data: CurrentDto): CurrentDomainModel {
        return CurrentDomainModel(
            lastUpdate = data.lastUpdated,
            tempC = data.tempC,
            humidity = data.humidity,
            windKph = data.windKph,
            feelsLike = data.feelsLike,
            condition = ConditionDomainMapper.mapToDomain(data.condition)
        )
    }
}