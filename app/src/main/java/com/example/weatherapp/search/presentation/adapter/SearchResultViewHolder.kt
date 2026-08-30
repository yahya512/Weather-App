package com.example.weatherapp.search.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.CardSearchItemBinding
import com.example.weatherapp.search.presentation.model.SearchResultUiModel

class SearchResultViewHolder(val binding: CardSearchItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindSearch(item: SearchResultUiModel, onClickItem: OnClickItem) {
        binding.apply {
            cityNameTextView.text = item.cityName
            countryNameTextView.text = item.countryName
            regionNameTextView.text = item.capitalName
            cardSearchResultConstraintlayout.setOnClickListener {
                onClickItem.onClick(item)
            }
        }
    }
}