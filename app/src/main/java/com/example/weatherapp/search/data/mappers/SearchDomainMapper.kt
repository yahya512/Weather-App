package com.example.weatherapp.search.data.mappers

import com.example.weatherapp.search.data.model.SearchResultDto
import com.example.weatherapp.search.domain.model.SearchResultDomainModel

object SearchDomainMapper {
    fun mapToDomain(data: SearchResultDto): SearchResultDomainModel {
        return SearchResultDomainModel(
            id = data.id,
            cityName = data.cityName,
            capitalName = data.capitalName,
            countryName = data.countryName,
            latitude = data.latitude,
            longitude = data.longitude
        )
    }
}