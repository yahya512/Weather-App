package com.example.weatherapp.home.data.remote

import com.example.weatherapp.home.data.model.GetWeatherDetailsDtoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GetWeatherDetailsApi {
    @GET(GET_FORECAST_URL)
    fun getWeatherDetails(
        @Query("q") latitudeAndLongitude: String,
        @Query("days") days: Int,
        @Query("hour") hour: Int,
        ): GetWeatherDetailsDtoResponse
}