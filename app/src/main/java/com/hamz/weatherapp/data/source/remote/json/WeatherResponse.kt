package com.hamz.weatherapp.data.source.remote.json

import com.google.gson.annotations.SerializedName
import com.hamz.weatherapp.data.model.Main

data class WeatherResponse(
    @SerializedName("main")
    val main: Main,

    @SerializedName("name")
    val name: String
)
