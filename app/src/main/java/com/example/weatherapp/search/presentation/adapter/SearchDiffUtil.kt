package com.example.weatherapp.search.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.search.presentation.model.SearchResultUiModel

class SearchDiffUtil : DiffUtil.ItemCallback<SearchResultUiModel>() {
    override fun areItemsTheSame(
        oldItem: SearchResultUiModel,
        newItem: SearchResultUiModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: SearchResultUiModel,
        newItem: SearchResultUiModel
    ): Boolean {
        return oldItem == newItem
    }
}