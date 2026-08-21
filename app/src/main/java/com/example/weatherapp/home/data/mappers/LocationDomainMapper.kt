package com.example.weatherapp.home.data.mappers

import com.example.weatherapp.home.data.model.LocationDto
import com.example.weatherapp.home.domain.model.LocationDomainModel

object LocationDomainMapper {
    fun mapToDomain(data: LocationDto): LocationDomainModel {
        return LocationDomainModel(
            name = data.name,
            localtime = data.localTime
        )
    }
}