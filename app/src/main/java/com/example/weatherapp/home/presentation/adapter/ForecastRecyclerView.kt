package com.example.weatherapp.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherapp.databinding.DaysWeatherItemBinding
import com.example.weatherapp.home.presentation.model.ForecastDayUiModel

class ForecastRecyclerView :
    ListAdapter<ForecastDayUiModel, WeatherDetailsViewHolder>(ForecastDiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherDetailsViewHolder {
        val view =
            DaysWeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherDetailsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: WeatherDetailsViewHolder,
        position: Int
    ) {
        val itemPosition = getItem(position)
        holder.bindWeatherDetails(itemPosition)
    }
}