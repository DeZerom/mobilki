package com.example.mobilki.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityModel(

    @Expose
    @SerializedName("name")
    val name: String? = null,

    @Expose
    @SerializedName("local_names")
    val localNames: LocalNamesModel? = null,

    @Expose
    @SerializedName("lat")
    val lat: Double? = null,

    @Expose
    @SerializedName("lon")
    val lon: Double? = null,

    @Expose
    @SerializedName("country")
    val country: String? = null

)
