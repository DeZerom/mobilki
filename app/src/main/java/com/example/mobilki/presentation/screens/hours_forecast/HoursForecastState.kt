package com.example.mobilki.presentation.screens.hours_forecast

import com.example.mobilki.domain.models.weather.CurrentWeatherDomainModel

data class HoursForecastState(
    val isLoading: Boolean = false,
    val forecast: List<CurrentWeatherDomainModel> = emptyList(),
    val cityName: String = ""
)