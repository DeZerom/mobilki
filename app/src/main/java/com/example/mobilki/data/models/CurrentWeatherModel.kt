package com.example.mobilki.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrentWeatherModel(

    @Expose
    @SerializedName("weather")
    val weather: List<WeatherModel>,

    @Expose
    @SerializedName("main")
    val main: MainInfoModel

)
