package com.example.weatherapp.search.presentation.adapter

import com.example.weatherapp.search.presentation.model.SearchResultUiModel

interface OnClickItem {
    fun onClick(item: SearchResultUiModel)
}