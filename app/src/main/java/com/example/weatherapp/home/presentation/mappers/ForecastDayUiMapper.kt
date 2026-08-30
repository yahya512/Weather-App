package com.example.weatherapp.home.presentation.mappers

import com.example.weatherapp.home.domain.model.ForecastDayDomainModel
import com.example.weatherapp.home.presentation.model.ForecastDayUiModel

object ForecastDayUiMapper {
    fun mapToUi(data: ForecastDayDomainModel): ForecastDayUiModel {
        return ForecastDayUiModel(
            date = data.date,
            day = DayUiMapper.mapToUi(data.day)
        )
    }
}