package com.example.weatherapp.search.data.repository

import com.example.weatherapp.home.data.remote.safeApiCall
import com.example.weatherapp.home.domain.model.ApiResultStatus
import com.example.weatherapp.search.data.mappers.SearchListDomainMapper
import com.example.weatherapp.search.data.remote.SearchResultApi
import com.example.weatherapp.search.domain.model.SearchResultDomainModel
import com.example.weatherapp.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(val apiResponse: SearchResultApi) :
    SearchRepository {
    override suspend fun searchByCity(city: String): ApiResultStatus<List<SearchResultDomainModel>> {
        val result = safeApiCall { apiResponse.searchResultResponse(city) }
        return when (result) {
            is ApiResultStatus.Error -> {
                ApiResultStatus.Error(result.errorMessage)
            }

            is ApiResultStatus.Success -> {
                val searchDomainList = SearchListDomainMapper.mapToDomain(result.data)
                ApiResultStatus.Success(searchDomainList)
            }
        }
    }
}