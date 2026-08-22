package com.example.weatherapp.home.data.repository

import com.example.weatherapp.home.data.mappers.GetWeatherResponseDomainMapper
import com.example.weatherapp.home.data.remote.GetWeatherDetailsApi
import com.example.weatherapp.home.data.remote.safeApiCall
import com.example.weatherapp.home.domain.model.ApiResultStatus
import com.example.weatherapp.home.domain.model.GetWeatherDetailsDomainResponse
import com.example.weatherapp.home.domain.repository.GetWeatherDetailsRepository
import javax.inject.Inject

class GetWeatherRepositoryImpl @Inject constructor(val apiResponse: GetWeatherDetailsApi) :
    GetWeatherDetailsRepository {
    override suspend fun getWeatherDetails(
        latitudeAndLongitude: String, days: Int, hour: Int
    ): ApiResultStatus<GetWeatherDetailsDomainResponse> {
        val response =
            safeApiCall { apiResponse.getWeatherDetails(latitudeAndLongitude, days, hour) }
        return when (response) {
            is ApiResultStatus.Error -> {
                ApiResultStatus.Error(response.errorMessage)
            }

            is ApiResultStatus.Success -> {
                val getWeatherDomainResponse =
                    GetWeatherResponseDomainMapper.mapToDomain(response.data)
                ApiResultStatus.Success(getWeatherDomainResponse)
            }
        }
    }
}