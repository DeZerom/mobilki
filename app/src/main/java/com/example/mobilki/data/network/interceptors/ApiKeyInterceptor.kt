package com.example.mobilki.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url().newBuilder()
            .addQueryParameter(API_ID_KEY, API_ID)
            .build()

        val newRequest = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        private const val API_ID_KEY = "appid"
        private const val API_ID = ApiIdObj.API_ID //зарегайся на openweathermap и сунь свой api key
    }
}
