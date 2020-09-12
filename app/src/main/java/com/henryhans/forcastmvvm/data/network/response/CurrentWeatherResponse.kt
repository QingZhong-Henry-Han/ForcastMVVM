package com.henryhans.forcastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.henryhans.forcastmvvm.data.db.entity.CurrentWeatherEntry
import com.henryhans.forcastmvvm.data.db.entity.Location
import com.henryhans.forcastmvvm.data.db.entity.Request

data class CurrentWeatherResponse(
    @SerializedName("request")
    val request: Request,
    @SerializedName("location")
    val location: Location,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)