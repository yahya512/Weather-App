package com.example.weatherapp.home.presentation.mappers

import com.example.weatherapp.home.domain.model.DayDomainModel
import com.example.weatherapp.home.presentation.model.DayUiModel

object DayUiMapper {
    fun mapToUi(data: DayDomainModel): DayUiModel {
        return DayUiModel(
            avgTempC = data.avgTempC,
            maxWindKph = data.maxWindKph,
            condition = ConditionUiMapper.mapToUi(data.condition)
        )
    }
}