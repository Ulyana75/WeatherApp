package com.ulyanaab.weatherapp.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.ulyanaab.weatherapp.R
import com.ulyanaab.weatherapp.utilities.ViewStates
import com.ulyanaab.weatherapp.viewModel.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {

        weatherViewModel.weatherLiveData.observe(this) {
            findViewById<TextView>(R.id.text_city_name).text = "City name: ${it.city_name}"
            findViewById<TextView>(R.id.text_wind_speed).text = "Wind speed: ${it.wind_spd} m/s"
            findViewById<TextView>(R.id.text_temperature).text = "Temperature: ${it.temp} °C"
        }

        weatherViewModel.viewStateLiveData.observe(this) {
            when (it) {
                ViewStates.LOADING -> {
                    findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
                    closeKeyboard()
                }
                else -> findViewById<ProgressBar>(R.id.progress_bar).visibility = View.INVISIBLE
            }
        }

        findViewById<Button>(R.id.btn_get_weather).setOnClickListener {
            val city = findViewById<EditText>(R.id.edit_text_city).text

            if (city.isEmpty()) {
                showToast("Enter a city name!")
            } else {
                weatherViewModel.getWeather(city.toString())
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun closeKeyboard() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            this.currentFocus?.windowToken, 0
        )
    }
}