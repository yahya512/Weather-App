package com.example.weatherapp.search.data.model

import com.google.gson.annotations.SerializedName

data class SearchResultDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val cityName: String,
    @SerializedName("region") val capitalName: String,
    @SerializedName("country") val countryName: String,
    @SerializedName("lat") val latitude: Float,
    @SerializedName("lon") val longitude: Float,
    @SerializedName("error") val error: ErrorMessageDto?
)
