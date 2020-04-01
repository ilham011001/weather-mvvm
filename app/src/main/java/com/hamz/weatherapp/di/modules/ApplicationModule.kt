package com.hamz.weatherapp.di.modules

import android.app.Application
import com.hamz.weatherapp.WeatherApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val weatherApp: WeatherApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return weatherApp
    }

}