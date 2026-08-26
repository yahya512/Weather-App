package com.example.weatherapp.search.domain.model

data class SearchResultDomainModel(
    val id: Int,
    val cityName: String,
    val countryName: String,
    val latitude: Float,
    val longitude: Float,
)
