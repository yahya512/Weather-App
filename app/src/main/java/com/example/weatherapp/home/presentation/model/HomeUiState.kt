package com.example.weatherapp.home.presentation.model

sealed class HomeUiState {
    data class Success(val data: GetWeatherDetailsUiResponse) : HomeUiState()
    data class Error(val errorMessage: String) : HomeUiState()
    data object Loading : HomeUiState()
}