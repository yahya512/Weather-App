package com.example.weatherapp.home.presentation.mappers

import com.example.weatherapp.home.domain.model.LocationDomainModel
import com.example.weatherapp.home.presentation.model.LocationUiModel

object LocationUiMapper {
    fun mapToUi(data: LocationDomainModel): LocationUiModel {
        return LocationUiModel(
            name = data.name,
            localtime = data.localtime
        )
    }
}