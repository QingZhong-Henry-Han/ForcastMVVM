package com.henryhans.forcastmvvm.data.db.unitlocalized

import androidx.room.ColumnInfo
import com.henryhans.forcastmvvm.data.db.unitlocalized.UnitSpcificCurrentWeatherEntry

data class ImperialCurrentWeatherEntry(
    @ColumnInfo(name = "temperature")
    override val temperature: Double,
    @ColumnInfo(name="visibility")
    override val visibility: Double

): UnitSpcificCurrentWeatherEntry