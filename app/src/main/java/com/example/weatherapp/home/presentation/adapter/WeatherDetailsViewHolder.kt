package com.example.weatherapp.home.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.core.handleImageUrl
import com.example.weatherapp.databinding.DaysWeatherItemBinding
import com.example.weatherapp.home.presentation.model.ForecastDayUiModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class WeatherDetailsViewHolder(val binding: DaysWeatherItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindWeatherDetails(item: ForecastDayUiModel) {
        val imageUrl = item.day.condition.icon
        binding.apply {
            textDayOfWeek.text = dateFormate(item.date)
            textDegreeDayNumber.text = item.day.avgTempC.toString()
            textMaxWindKph.text = item.day.maxWindKph.toString()
            Glide.with(itemView.context)
                .load(
                    imageUrl.handleImageUrl()
                )
                .placeholder(R.drawable.cloud_icon)
                .into(imageCloudIcon)
        }
    }
}

private fun dateFormate(unFormattedDate: String): String {
    val date = LocalDate.parse(unFormattedDate)
    val formattedDate = date.format(DateTimeFormatter.ofPattern("EEE dd", Locale.ENGLISH))
    return formattedDate
}