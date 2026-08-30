package com.example.weatherapp.search.domain.usecase

import com.example.weatherapp.home.domain.model.ApiResultStatus
import com.example.weatherapp.search.domain.model.SearchResultDomainModel
import com.example.weatherapp.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchByCityUseCase @Inject constructor(val repository: SearchRepository) {
    suspend operator fun invoke(city: String): ApiResultStatus<List<SearchResultDomainModel>> {
        return repository.searchByCity(city)
    }
}