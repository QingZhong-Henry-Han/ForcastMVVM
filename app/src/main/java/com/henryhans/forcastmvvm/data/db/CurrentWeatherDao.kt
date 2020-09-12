package com.henryhans.forcastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.henryhans.forcastmvvm.data.db.entity.CurrentWeatherEntry
import com.henryhans.forcastmvvm.data.db.entity.WEATHER_CURRENT_ID
import com.henryhans.forcastmvvm.data.db.unitlocalized.MetricCurrentWeatherEntry
import com.henryhans.forcastmvvm.data.db.unitlocalized.ImperialCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry : CurrentWeatherEntry)

    @Query("select * from current_weather where id = $WEATHER_CURRENT_ID")
    fun getWeatherMetric() : LiveData<MetricCurrentWeatherEntry>

    @Query("select * from current_weather where id = $WEATHER_CURRENT_ID")
    fun getWeatherImperial() : LiveData<ImperialCurrentWeatherEntry>

}