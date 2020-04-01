package com.hamz.weatherapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamz.weatherapp.data.model.Main
import com.hamz.weatherapp.data.source.WeatherRepository
import com.hamz.weatherapp.data.source.remote.WeatherServiceFactory
import com.tosanaji.tajilestari.util.ApiException
import com.tosanaji.tajilestari.util.Coroutines

class MainViewModel: ViewModel {

    private val TAG: String = MainViewModel::class.java.simpleName

    private val weatherRepository: WeatherRepository =
        WeatherRepository(
            WeatherServiceFactory.getService()
        )

    constructor() {
        getWeather()
    }

    private val mMain = MutableLiveData<Main>()
    val main: LiveData<Main>
        get() = mMain

    private val mName = MutableLiveData<String>()
    val name: LiveData<String>
        get() = mName

    private val mLoader = MutableLiveData<Boolean>()
    val loader: LiveData<Boolean>
        get() = mLoader

    private val mError = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = mError

    fun getWeather() {
        mLoader.postValue(true)

        Coroutines.main {
            try {
                val response = weatherRepository.getWeather()

                mMain.postValue(response.main)
                mName.postValue(response.name)

                mLoader.postValue(false)
            } catch (e: ApiException) {
                Log.e(TAG, "getWeather error : ${e.toString()}")
                mError.postValue(true)
            }
        }
    }
}