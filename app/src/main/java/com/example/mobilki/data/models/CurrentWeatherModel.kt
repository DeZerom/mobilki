package com.example.mobilki.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrentWeatherModel(

    @Expose
    @SerializedName("weather")
    val weather: List<WeatherModel> = emptyList(),

    @Expose
    @SerializedName("main")
    val main: MainInfoModel? = null,

    @Expose
    @SerializedName("dt")
    val date: Long? = null,

    @Expose
    @SerializedName("dt_txt")
    val dateText: String? = null

)
