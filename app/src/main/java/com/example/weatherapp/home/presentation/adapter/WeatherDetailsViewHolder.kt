package com.example.weatherapp.home.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.core.dateFormate
import com.example.weatherapp.core.handleImageUrl
import com.example.weatherapp.databinding.DaysWeatherItemBinding
import com.example.weatherapp.home.presentation.model.ForecastDayUiModel

class WeatherDetailsViewHolder(val binding: DaysWeatherItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindWeatherDetails(item: ForecastDayUiModel) {
        val imageUrl = item.day.condition.icon
        binding.apply {
            dayOfWeekTextView.text = dateFormate(item.date)
            degreeDayNumberTextView.text = item.day.avgTempC.toString()
            maxWindKphTextView.text = item.day.maxWindKph.toString()
            Glide.with(itemView.context)
                .load(
                    imageUrl.handleImageUrl()
                )
                .placeholder(R.drawable.cloud_icon)
                .into(cloudIconImageView)
        }
    }
}