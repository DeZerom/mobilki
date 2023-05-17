package com.example.mobilki.domain.models.weather

data class CurrentWeatherDomainModel(
    val weatherName: String,
    val weatherDescription: String,
    val weatherIcon: String,
    val temp: Double,
    val pressure: Double,
    val cityName: String,
    val countryCode: String,
    val lat: Double,
    val lon: Double
)
