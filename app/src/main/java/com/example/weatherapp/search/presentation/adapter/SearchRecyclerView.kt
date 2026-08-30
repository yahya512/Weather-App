package com.example.weatherapp.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherapp.databinding.CardSearchItemBinding
import com.example.weatherapp.search.presentation.model.SearchResultUiModel

class SearchRecyclerView(
    private val listener: OnClickItem
) : ListAdapter<SearchResultUiModel, SearchResultViewHolder>(SearchDiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultViewHolder {
        val view = CardSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: SearchResultViewHolder,
        position: Int
    ) {
        val searchItem = getItem(position)
        holder.bindSearch(searchItem, listener)
    }
}