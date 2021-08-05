package com.ulyanaab.weatherapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ulyanaab.weatherapp.model.dataSource.WeatherDataSource
import com.ulyanaab.weatherapp.model.dataSource.WeatherDataSourceImpl
import com.ulyanaab.weatherapp.model.retrofit.WeatherModel
import com.ulyanaab.weatherapp.utilities.ViewStates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    val weatherLiveData: LiveData<WeatherModel> get() = _weatherLiveData
    private val _weatherLiveData = MutableLiveData<WeatherModel>()

    val viewStateLiveData: LiveData<ViewStates> get() = _viewStateLiveData
    private val _viewStateLiveData = MutableLiveData<ViewStates>()

    private val weatherDataSource: WeatherDataSource = WeatherDataSourceImpl()


    fun getWeather(city: String) {

        _viewStateLiveData.value = ViewStates.LOADING

        CoroutineScope(Dispatchers.IO).launch {
            _weatherLiveData.postValue(weatherDataSource.getWeather(city))
            _viewStateLiveData.postValue(ViewStates.LOADED)
        }
    }

}