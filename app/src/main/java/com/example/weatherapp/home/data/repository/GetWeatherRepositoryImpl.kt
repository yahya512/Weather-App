package com.example.weatherapp.home.data.repository

import android.util.Log
import com.example.weatherapp.core.LocationLocalDataSource
import com.example.weatherapp.home.data.mappers.GetWeatherResponseDomainMapper
import com.example.weatherapp.home.data.remote.GetWeatherDetailsApi
import com.example.weatherapp.home.data.remote.safeApiCall
import com.example.weatherapp.home.domain.model.ApiResultStatus
import com.example.weatherapp.home.domain.model.GetWeatherDetailsDomainResponse
import com.example.weatherapp.home.domain.repository.GetWeatherDetailsRepository
import javax.inject.Inject

class GetWeatherRepositoryImpl @Inject constructor(
    val apiResponse: GetWeatherDetailsApi,
    val sharedPreferences: LocationLocalDataSource
) :
    GetWeatherDetailsRepository {
    override suspend fun getWeatherDetails(
        latitudeAndLongitude: String?, days: Int, hour: Int
    ): ApiResultStatus<GetWeatherDetailsDomainResponse> {
        Log.d("HomeRepo", "lat and long :$latitudeAndLongitude")
        val localLocation = latitudeAndLongitude
            ?: ("${sharedPreferences.getLatitude()}," + "${sharedPreferences.getLongitude()}")
        Log.d("HomeRepo", "saved lat and long from  pref : $localLocation")

        val response =
            safeApiCall { apiResponse.getWeatherDetails(localLocation, days, hour) }
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

