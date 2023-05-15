package com.example.mobilki.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherModel(

    @Expose
    @SerializedName("id")
    val id: Int? = null,

    @Expose
    @SerializedName("main")
    val main: String? = null,

    @Expose
    @SerializedName("description")
    val description: String? = null,

    @Expose
    @SerializedName("icon")
    val icon: String? = null

)
