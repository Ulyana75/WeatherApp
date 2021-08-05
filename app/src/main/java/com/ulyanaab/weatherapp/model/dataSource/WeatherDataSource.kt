package com.ulyanaab.weatherapp.model.dataSource

import com.ulyanaab.weatherapp.model.retrofit.WeatherModel

interface WeatherDataSource {

    fun getWeather(city: String): WeatherModel

}