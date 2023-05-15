package com.example.mobilki.app.di

import com.example.mobilki.data.network.apis.GeoCodingApiService
import com.example.mobilki.data.network.apis.WeatherApiService
import com.example.mobilki.data.network.interceptors.ApiKeyInterceptor
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    @Provides
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofitBuilder(client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
    }

    @Provides
    fun provideGeoApiService(builder: Retrofit.Builder): GeoCodingApiService {
        return builder
            .baseUrl(BASE_GEO_URL)
            .build()
            .create(GeoCodingApiService::class.java)
    }

    @Provides
    fun provideCurrentWeatherApiService(builder: Builder): WeatherApiService {
        return builder
            .baseUrl(BASE_WEATHER_URL)
            .build()
            .create(WeatherApiService::class.java)
    }

    companion object {
        private const val BASE_GEO_URL = "http://api.openweathermap.org/"
        private const val BASE_WEATHER_URL = "https://api.openweathermap.org/"
    }

}
