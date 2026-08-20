package com.example.weatherapp.home.domain.model

sealed interface ApiResultStatus<out T> {
    data class Success<T>(val data: T) : ApiResultStatus<T>
    data class Error(val errorMessage: String) : ApiResultStatus<Nothing>
}