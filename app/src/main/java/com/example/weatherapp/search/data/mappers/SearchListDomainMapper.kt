package com.example.weatherapp.search.data.mappers

import com.example.weatherapp.search.data.model.SearchResultDto
import com.example.weatherapp.search.domain.model.SearchResultDomainModel

object SearchListDomainMapper {
    fun mapToDomain(data: List<SearchResultDto>): List<SearchResultDomainModel> {
        return data.map {
            SearchDomainMapper.mapToDomain(it)
        }
    }
}