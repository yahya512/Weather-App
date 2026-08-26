package com.example.weatherapp.home.presentation.mappers

import com.example.weatherapp.home.domain.model.ForecastDayDomainModel
import com.example.weatherapp.home.presentation.model.ForecastUiModel

object ForecastUIMapper {
    fun mapToUi(data: List<ForecastDayDomainModel>): ForecastUiModel {
        return ForecastUiModel(
            forecastDay = data.map {
                ForecastDayUiMapper.mapToUi(it)
            }
        )
    }
}