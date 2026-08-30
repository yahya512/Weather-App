package com.example.weatherapp.search.presentation.mappers

import com.example.weatherapp.search.domain.model.SearchResultDomainModel
import com.example.weatherapp.search.presentation.model.SearchResultUiModel

object SearchUiMapper {
    fun mapToUi(data: SearchResultDomainModel): SearchResultUiModel {
        return SearchResultUiModel(
            id = data.id,
            cityName = data.cityName,
            capitalName = data.capitalName,
            countryName = data.countryName,
            latitude = data.latitude,
            longitude = data.longitude
        )
    }
}