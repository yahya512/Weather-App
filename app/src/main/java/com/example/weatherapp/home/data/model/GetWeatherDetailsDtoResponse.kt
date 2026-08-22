package com.example.weatherapp.home.data.model

import com.google.gson.annotations.SerializedName

data class GetWeatherDetailsDtoResponse(
    @SerializedName("location") val location: LocationDto,
    @SerializedName("current") val current: CurrentDto,
    @SerializedName("forecast") val forecast: ForecastDto,
    @SerializedName("error") val error: ErrorMessageDto?
)
