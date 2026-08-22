package com.example.weatherapp.home.data.model

import com.google.gson.annotations.SerializedName

data class ConditionDto(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String

)
