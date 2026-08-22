package com.example.weatherapp.home.data.model

import com.google.gson.annotations.SerializedName

data class DayDto(
    @SerializedName("avgtemp_c") val avgTempC: Double,
    @SerializedName("maxwind_kph") val maxWindKph: Double,
    @SerializedName("condition") val condition: ConditionDto
)
