package com.example.weatherapp.home.data.model

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("name") val name: String,
    @SerializedName("localtime") val localTime: String
)

