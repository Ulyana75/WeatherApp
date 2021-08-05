package com.ulyanaab.weatherapp.model.retrofit

data class WeatherModel(
    val city_name: String? = null,
    val wind_spd: Float? = null,
    val temp: Float? = null
)

data class WeatherResponse(
    val data: List<WeatherModel>
)