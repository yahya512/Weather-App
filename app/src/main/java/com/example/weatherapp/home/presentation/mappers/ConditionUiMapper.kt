package com.example.weatherapp.home.presentation.mappers

import com.example.weatherapp.home.domain.model.ConditionDomainModel
import com.example.weatherapp.home.presentation.model.ConditionUiModel

object ConditionUiMapper {
    fun mapToUi(data: ConditionDomainModel): ConditionUiModel {
        return ConditionUiModel(
            text = data.text,
            icon = data.icon
        )
    }
}