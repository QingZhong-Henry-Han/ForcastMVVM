package com.henryhans.forcastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.henryhans.forcastmvvm.data.repository.ForecastRepositoy

class CurrentWeatherViewModelFactory(
    private val forecastRepository : ForecastRepositoy
) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forecastRepository) as T
    }
}