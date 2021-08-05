package com.ulyanaab.weatherapp.model.retrofit

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current")
    fun getWeather(@Query("city") city: String): Deferred<WeatherResponse>

}