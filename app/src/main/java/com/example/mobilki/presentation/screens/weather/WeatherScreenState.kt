package com.example.mobilki.presentation.screens.weather

import com.example.mobilki.domain.models.weather.CurrentWeatherDomainModel

data class WeatherScreenState(
    val weatherInfo: CurrentWeatherDomainModel? = null,
    val isLoading: Boolean = false
)
