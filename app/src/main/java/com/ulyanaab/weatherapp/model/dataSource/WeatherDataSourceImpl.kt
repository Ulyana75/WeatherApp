package com.ulyanaab.weatherapp.model.dataSource

import com.ulyanaab.weatherapp.model.retrofit.ApiFactory
import com.ulyanaab.weatherapp.model.retrofit.WeatherModel
import kotlinx.coroutines.runBlocking

class WeatherDataSourceImpl : WeatherDataSource {

    override fun getWeather(city: String): WeatherModel = runBlocking {
        ApiFactory.getApi().getWeather(city).await().data[0]
    }

}