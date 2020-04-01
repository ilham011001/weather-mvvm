package com.hamz.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class OpenWeather(
    val id: Double,
    val name: String,
    val cod: Int
) {

    data class Weather(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String
    )

    data class Main(
        val temp: Double,
        val pressure: Int,
        val humidity: Int,
        val temp_min: Double,
        val temp_max: Double
    )
}