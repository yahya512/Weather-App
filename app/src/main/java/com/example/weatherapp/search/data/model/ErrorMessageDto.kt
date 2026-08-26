package com.example.weatherapp.search.data.model

import com.google.gson.annotations.SerializedName

data class ErrorMessageDto(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)
