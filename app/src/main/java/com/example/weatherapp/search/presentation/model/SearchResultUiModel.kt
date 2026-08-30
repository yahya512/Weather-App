package com.example.weatherapp.search.presentation.model

data class SearchResultUiModel(
    val id: Int,
    val cityName: String,
    val capitalName: String,
    val countryName: String,
    val latitude: Float,
    val longitude: Float,
)