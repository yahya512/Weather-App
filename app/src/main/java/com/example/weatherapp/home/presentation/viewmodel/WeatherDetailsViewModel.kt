package com.example.weatherapp.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.home.domain.model.ApiResultStatus
import com.example.weatherapp.home.domain.usecase.GetWeatherDetailsUseCase
import com.example.weatherapp.home.presentation.mappers.GetWeatherDetailsUiMapper
import com.example.weatherapp.home.presentation.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getWeatherDetails: GetWeatherDetailsUseCase
) : ViewModel() {
    private val _stateFlowWeatherDetails = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val stateWeatherDetails = _stateFlowWeatherDetails.asStateFlow()

    private var latitude: Float = 0.0f
    private var longitude: Float = 0.0f

    fun loadWeatherDetails() {
        viewModelScope.launch {
            _stateFlowWeatherDetails.emit(HomeUiState.Loading)

            when (val result = getWeatherDetails("$latitude,$longitude", 7, 16)) {
                is ApiResultStatus.Success -> {
                    val getWeatherDetailsUiResponse = GetWeatherDetailsUiMapper.mapToUi(result.data)
                    _stateFlowWeatherDetails.emit(HomeUiState.Success(getWeatherDetailsUiResponse))
                }

                is ApiResultStatus.Error -> {
                    _stateFlowWeatherDetails.emit(HomeUiState.Error(result.errorMessage))
                }
            }
        }
    }

    fun setLatitudeAndLongitude(latitude: Float, longitude: Float) {
        this.latitude = latitude
        this.longitude = longitude
    }
}