package com.henryhans.forcastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.henryhans.forcastmvvm.data.db.CurrentWeatherDao
import com.henryhans.forcastmvvm.data.db.unitlocalized.UnitSpcificCurrentWeatherEntry
import com.henryhans.forcastmvvm.data.network.WeatherNetworkDataSource
import com.henryhans.forcastmvvm.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class ForecastRepositoyImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource

) : ForecastRepositoy {

    init{

        weatherNetworkDataSource.downloadedCurrentWeather.observeForever{
            persistFetchedCurrentWeather(it)
        }

    }

    override suspend fun getCurrentWeather(metric: Boolean) : LiveData<out UnitSpcificCurrentWeatherEntry> {

        return withContext(Dispatchers.IO){
            return@withContext if(metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedCurrentWeather: CurrentWeatherResponse){

        GlobalScope.launch( Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedCurrentWeather.currentWeatherEntry)
        }

    }

    private suspend fun initWeatherData(){
        if(isFetchCurrentDataNeeded(ZonedDateTime.now().minusHours(1))){
            fetchCurrentWeather()
        }

    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather(
            "New York"
        )
    }

    private fun isFetchCurrentDataNeeded(lastFetchtime : ZonedDateTime) : Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)

        return lastFetchtime.isBefore(thirtyMinutesAgo)
    }
}