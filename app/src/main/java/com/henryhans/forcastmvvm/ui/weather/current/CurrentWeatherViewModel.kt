package com.henryhans.forcastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import com.henryhans.forcastmvvm.data.repository.ForecastRepositoy
import com.henryhans.forcastmvvm.internal.UnitSystem
import com.henryhans.forcastmvvm.internal.lazyDeferred


class CurrentWeatherViewModel(
    private val forecastRepositoy: ForecastRepositoy
) : ViewModel() {

    private val unitSystem = UnitSystem.METRIC

    val isMetric : Boolean
        get() = unitSystem == UnitSystem.METRIC


    val weather by lazyDeferred {
        forecastRepositoy.getCurrentWeather(isMetric)
    }

}