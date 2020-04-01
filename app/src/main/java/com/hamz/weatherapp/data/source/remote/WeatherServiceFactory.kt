package com.hamz.weatherapp.data.source.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherServiceFactory {
    companion object {

        @Volatile
        private var INSTANCE: WeatherService? = null

        private const val BASE_URL = "https://samples.openweathermap.org/data/2.5/"

        fun getService(): WeatherService {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(provideOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(WeatherService::class.java)
                INSTANCE = instance
                instance
            }
        }

        private fun provideOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
            client.addInterceptor(interceptor)
            return client.build()
        }
    }
}