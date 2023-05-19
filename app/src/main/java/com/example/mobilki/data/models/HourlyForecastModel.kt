package com.example.mobilki.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HourlyForecastModel(

    @Expose
    @SerializedName("list")
    val list: List<CurrentWeatherModel>

)
