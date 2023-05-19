package com.example.mobilki.presentation.screens.weather

sealed class WeatherScreenSideEffect {
    object RequestGpsPermission: WeatherScreenSideEffect()

    class GoToHoursForecast(val lat:  Double, val lon: Double) : WeatherScreenSideEffect()
}
