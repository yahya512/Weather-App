package com.example.weatherapp.home.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.home.presentation.model.ForecastDayUiModel

class ForecastDiffUtil : DiffUtil.ItemCallback<ForecastDayUiModel>() {
    override fun areItemsTheSame(
        oldItem: ForecastDayUiModel,
        newItem: ForecastDayUiModel
    ): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(
        oldItem: ForecastDayUiModel,
        newItem: ForecastDayUiModel
    ): Boolean {
        return oldItem == newItem
    }
}