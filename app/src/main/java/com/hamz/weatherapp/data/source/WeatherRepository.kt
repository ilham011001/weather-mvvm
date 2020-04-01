package com.hamz.weatherapp.data.source

import com.hamz.weatherapp.data.source.remote.SafeApiRequest
import com.hamz.weatherapp.data.source.remote.WeatherService
import com.hamz.weatherapp.data.source.remote.json.WeatherResponse

class WeatherRepository(
    val weatherService: WeatherService
) : SafeApiRequest() {

    suspend fun getWeather(): WeatherResponse {
        return apiRequest {
            weatherService.getWheater()
        }
    }
}