package com.example.weatherapp.home.data.mappers

import com.example.weatherapp.home.data.model.ConditionDto
import com.example.weatherapp.home.domain.model.ConditionDomainModel

object ConditionDomainMapper {
    fun mapToDomain(data: ConditionDto): ConditionDomainModel {
        return ConditionDomainModel(
            text = data.text,
            icon = data.icon
        )
    }
}