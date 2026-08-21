package com.example.weatherapp.home.data.model

import com.google.gson.annotations.SerializedName

data class ErrorMessageDto(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)
