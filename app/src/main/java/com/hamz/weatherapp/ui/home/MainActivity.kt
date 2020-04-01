package com.hamz.weatherapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hamz.weatherapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setupView()
        setupObserverView()
    }

    private fun setupView() {
        swipe_refresh_layout.setOnRefreshListener {
            mainViewModel.getWeather()
        }
    }

    private fun setupObserverView() {
        mainViewModel.loader.observe(this, Observer { isLoading ->
            main_view.visibility = if (isLoading) View.GONE else View.VISIBLE
//            progress_bar.visibility = if (isLoading) View.VISIBLE else View.GONE
            swipe_refresh_layout.isRefreshing = isLoading
        })

        mainViewModel.error.observe(this, Observer {isError ->
            if (isError != null)
                if (isError) Toast.makeText(applicationContext, "failed to fetch data", Toast.LENGTH_SHORT).show()
        })

        mainViewModel.main.observe(this, Observer { main ->
            if (main != null) {
                var celsius: Double = 0.0
                val kelvin = main?.temp
                if (kelvin != null) celsius = kelvin - 273.15

                text_temp.text = Math.round(celsius).toString() + " C"
            }
        })

        mainViewModel.name.observe(this, Observer { name ->
            if (name != null) {
                text_name.text = name
            }
        })
    }
}
