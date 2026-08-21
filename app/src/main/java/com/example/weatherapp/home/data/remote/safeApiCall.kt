package com.example.weatherapp.home.data.remote

import com.example.weatherapp.home.data.model.GetWeatherDetailsDtoResponse
import com.example.weatherapp.home.domain.model.ApiResultStatus
import retrofit2.HttpException
import java.io.IOException

suspend fun safeApiCall(apiCall: suspend () -> GetWeatherDetailsDtoResponse): ApiResultStatus<GetWeatherDetailsDtoResponse> {
    return try {
        val result = apiCall()
        if (result.error?.message != null) {
            ApiResultStatus.Success(result)
        } else {
            ApiResultStatus.Error(result.error?.message ?: "the Data Doesn't Received successfully")
        }
    } catch (e: IOException) {
        ApiResultStatus.Error(e.message ?: "Connection Failed")
    } catch (e: HttpException) {
        ApiResultStatus.Error(e.message())
    } catch (e: Exception) {
        ApiResultStatus.Error(e.message ?: "Unknown Error")
    }

}