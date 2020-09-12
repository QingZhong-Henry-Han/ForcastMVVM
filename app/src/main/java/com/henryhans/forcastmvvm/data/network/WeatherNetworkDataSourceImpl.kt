package com.henryhans.forcastmvvm.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.henryhans.forcastmvvm.data.WeatherstackApiService
import com.henryhans.forcastmvvm.data.network.response.CurrentWeatherResponse
import com.henryhans.forcastmvvm.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val weatherstackApiService : WeatherstackApiService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()

    override val downloadedCurrenrtWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try{
            val  fetchedCurrentWeather = weatherstackApiService
                .getCurrentWeather(location)
                .await()

            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity","No internet connection",e)
        }
        catch (e: Exception){
            Log.e("Api service","Api ervice wrong",e)
        }
    }
}