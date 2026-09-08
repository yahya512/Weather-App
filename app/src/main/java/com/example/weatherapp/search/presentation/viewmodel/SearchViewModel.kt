package com.example.weatherapp.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.home.domain.model.ApiResultStatus
import com.example.weatherapp.search.domain.usecase.SearchByCityUseCase
import com.example.weatherapp.search.presentation.mappers.SearchListUiMapper
import com.example.weatherapp.search.presentation.model.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchByCityUseCase
) : ViewModel() {


    private val _searchStateFlow = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val searchState: StateFlow<SearchUiState> = _searchStateFlow.asStateFlow()

    private val _cityName = MutableStateFlow("")

    init {
        observeSearchQuery()
    }
    private suspend fun loadSearchResult(query: String) {
        _searchStateFlow.emit(SearchUiState.Loading)
        when (val result = searchUseCase(query)) {
            is ApiResultStatus.Success -> {
                val searchResultUiList = SearchListUiMapper.mapToUi(result.data)
                _searchStateFlow.emit(SearchUiState.Success(searchResultUiList))
            }

            is ApiResultStatus.Error -> {
                _searchStateFlow.emit(SearchUiState.Error(result.errorMessage))
            }
        }
    }

    fun setCityName(cityName: String) {

        this._cityName.value = cityName
    }

    // Crash here make a debug and review the flow again

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            _cityName.debounce(500.milliseconds).distinctUntilChanged().filter { it.isNotBlank() }
                .collectLatest { query ->
                    if (query.isNotBlank()) {
                        loadSearchResult(query)
                    }
                }
        }
    }
}