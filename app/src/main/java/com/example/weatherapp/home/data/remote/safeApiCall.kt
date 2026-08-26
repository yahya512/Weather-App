package com.example.weatherapp.home.data.remote

import com.example.weatherapp.home.data.model.GetWeatherDetailsDtoResponse
import com.example.weatherapp.home.domain.model.ApiResultStatus
import com.example.weatherapp.search.data.model.SearchResultDto
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResultStatus<T> {
    return try {
        when (val result = apiCall()) {
            is GetWeatherDetailsDtoResponse -> {
                if (result.error?.message == null) {
                    ApiResultStatus.Success(result)
                } else {
                    ApiResultStatus.Error(result.error.message)
                }
            }

            is SearchResultDto -> {
                if (result.error?.message == null) {
                    ApiResultStatus.Success(result)
                } else {
                    ApiResultStatus.Error(result.error.message)
                }
            }

            else -> {
                ApiResultStatus.Error("Un known Type Response")
            }
        }
    } catch (e: IOException) {
        ApiResultStatus.Error(e.message ?: "Connection Failed")
    } catch (e: HttpException) {
        ApiResultStatus.Error(e.message())
    } catch (e: Exception) {
        ApiResultStatus.Error(e.message ?: "Unknown Error")
    }
}