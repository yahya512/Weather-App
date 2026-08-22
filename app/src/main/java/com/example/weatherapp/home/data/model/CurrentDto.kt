package com.example.weatherapp.home.data.model

import com.google.gson.annotations.SerializedName

data class CurrentDto(
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("feelslike_c") val feelsLike: Double,
    @SerializedName("condition") val condition: ConditionDto,
)
