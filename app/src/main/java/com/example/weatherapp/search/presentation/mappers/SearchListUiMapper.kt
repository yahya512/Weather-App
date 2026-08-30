package com.example.weatherapp.search.presentation.mappers

import com.example.weatherapp.search.domain.model.SearchResultDomainModel
import com.example.weatherapp.search.presentation.model.SearchResultUiModel

object SearchListUiMapper {
    fun mapToUi(data: List<SearchResultDomainModel>): List<SearchResultUiModel> {
        return data.map {
            SearchUiMapper.mapToUi(it)
        }
    }
}