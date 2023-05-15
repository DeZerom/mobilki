package com.example.mobilki.app.di

import com.example.mobilki.data.network.apis.GeoCodingApiService
import com.example.mobilki.data.network.apis.WeatherApiService
import com.example.mobilki.data.network.interceptors.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
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
            .baseUrl(BASE_URL)
            .build()
            .create(GeoCodingApiService::class.java)
    }

    @Provides
    fun provideCurrentWeatherApiService(builder: Retrofit.Builder): WeatherApiService {
        return builder
            .baseUrl(BASE_URL)
            .build()
            .create(WeatherApiService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/"
    }

}
