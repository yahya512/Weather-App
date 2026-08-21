package com.example.weatherapp.home.data.mappers

import com.example.weatherapp.home.data.model.DayDto
import com.example.weatherapp.home.domain.model.DayDomainModel

object DayDomainMapper {
    fun mapRoDomain(data: DayDto): DayDomainModel {
        return DayDomainModel(
            avgTempC = data.avgTempC,
            maxWindKph = data.maxWindKph,
            condition = ConditionDomainMapper.mapToDomain(data.condition)
        )
    }
}