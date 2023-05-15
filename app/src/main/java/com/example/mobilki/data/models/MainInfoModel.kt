package com.example.mobilki.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainInfoModel(

    @Expose
    @SerializedName("temp")
    val temp: Double? = null,

    @Expose
    @SerializedName("pressure")
    val pressure: Double? = null

)
