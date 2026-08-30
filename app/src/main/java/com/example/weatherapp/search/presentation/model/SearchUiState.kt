package com.example.weatherapp.search.presentation.model

sealed class SearchUiState {
    data object Idle : SearchUiState()
    data class Success(val data: List<SearchResultUiModel>) : SearchUiState()
    data object Loading : SearchUiState()
    data class Error(val errorMessage: String) : SearchUiState()
}