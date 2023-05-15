package com.example.mobilki.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LocalNamesModel(

    @Expose
    @SerializedName("ru")
    val ruName: String? = null

)
