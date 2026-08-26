package com.example.weatherapp.search.domain.repository

import com.example.weatherapp.home.domain.model.ApiResultStatus
import com.example.weatherapp.search.domain.model.SearchResultDomainModel

interface SearchRepository {
    suspend fun searchByCity(city: String): ApiResultStatus<List<SearchResultDomainModel>>
}