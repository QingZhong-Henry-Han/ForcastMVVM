package com.henryhans.forcastmvvm.data.repository

import androidx.lifecycle.LiveData

import com.henryhans.forcastmvvm.data.db.unitlocalized.UnitSpcificCurrentWeatherEntry

interface ForecastRepositoy {
    suspend fun getCurrentWeather(metric: Boolean) : LiveData<out UnitSpcificCurrentWeatherEntry>
}