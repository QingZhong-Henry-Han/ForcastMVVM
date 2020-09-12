package com.henryhans.forcastmvvm.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.henryhans.forcastmvvm.internal.StringListToGsonConverter

const val WEATHER_CURRENT_ID : Int = 0

@Entity(tableName = "current_weather")
@TypeConverters(StringListToGsonConverter::class)
data class CurrentWeatherEntry(
    @SerializedName("observation_time")
    val observation_time: String,
    @SerializedName("temperature")
    val temperature: Int,
    @SerializedName("weather_code")
    val weather_code: Int,
    @SerializedName("weather_icons")
    val weather_icons: List<String>,
    @SerializedName("weather_descriptions")
    val weather_descriptions: List<String>,
    @SerializedName("wind_speed")
    val wind_speed: Int,
    @SerializedName("wind_degree")
    val wind_degree: Int,
    @SerializedName("wind_dir")
    val wind_dir: String,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("precip")
    val precip: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("cloudcover")
    val cloudcover: Int,
    @SerializedName("feelslike")
    val feelslike: Int,
    @SerializedName("uv_index")
    val uv_index: Int,
    @SerializedName("visibility")
    val visibility: Int
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = WEATHER_CURRENT_ID
}