package com.henryhans.forcastmvvm.data.network

import androidx.lifecycle.LiveData
import com.henryhans.forcastmvvm.data.db.entity.CurrentWeatherEntry
import com.henryhans.forcastmvvm.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather : LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location : String
    )
}