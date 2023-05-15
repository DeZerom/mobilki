package com.example.mobilki.data.network.apis

import com.example.mobilki.data.models.CityModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingApiService {

    @GET("geo/1.0/direct")
    suspend fun getCoordinatesOfCity(
        @Query("q") cityName: String
    ): List<CityModel>?

    @GET("geo/1.0/reverse")
    suspend fun getCityOfCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): List<CityModel>?

}
