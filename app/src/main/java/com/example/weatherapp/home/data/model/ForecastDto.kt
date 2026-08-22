package com.example.weatherapp.home.data.model

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("forecastday") val forecastDay: List<ForecastDayDto>
)