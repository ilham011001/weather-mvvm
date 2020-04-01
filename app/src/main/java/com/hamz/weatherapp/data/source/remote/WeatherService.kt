package com.hamz.weatherapp.data.source.remote

import com.hamz.weatherapp.data.source.remote.json.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun getWheater(
        @Query("q") q: String = "London,uk",
        @Query("appid") appId: String = "b6907d289e10d714a6e88b30761fae22"
    ): Response<WeatherResponse>
}