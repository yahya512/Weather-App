package com.example.weatherapp.home.domain.model

data class DayDomainModel(
    val avgTempC: Double,
    val maxWindKph: Double,
    val condition: ConditionDomainModel
)
