package com.example.mobilki.data.network.apis

import com.example.mobilki.data.models.CurrentWeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("data/2.5/weather")
    suspend fun getWeatherAtCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru"
    ): CurrentWeatherModel?

}
