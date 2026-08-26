package com.example.weatherapp.home.presentation.mappers

import com.example.weatherapp.home.domain.model.CurrentWeatherDomainModel
import com.example.weatherapp.home.presentation.model.CurrentWeatherUiModel

object CurrentUiMapper {
    fun mapToUi(data: CurrentWeatherDomainModel): CurrentWeatherUiModel {
        return CurrentWeatherUiModel(
            lastUpdate = data.lastUpdate,
            tempC = data.tempC,
            feelsLike = data.feelsLike,
            humidity = data.humidity,
            windKph = data.windKph,
            condition = ConditionUiMapper.mapToUi(data.condition)
        )
    }
}