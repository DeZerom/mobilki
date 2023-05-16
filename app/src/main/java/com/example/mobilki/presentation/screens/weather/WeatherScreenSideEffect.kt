package com.example.mobilki.presentation.screens.weather

sealed class WeatherScreenSideEffect {
    object RequestGpsPermission: WeatherScreenSideEffect()
}
