package com.example.mobilki.domain.models.weather

import java.util.Date

data class CurrentWeatherDomainModel(
    val weatherName: String,
    val weatherDescription: String,
    val weatherIcon: String,
    val temp: Double,
    val pressure: Double,
    val date: Date,
    val dateText: String,
    val cityName: String,
    val countryCode: String,
    val lat: Double,
    val lon: Double
)
