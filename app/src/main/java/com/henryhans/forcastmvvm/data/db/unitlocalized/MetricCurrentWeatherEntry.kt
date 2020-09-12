package com.henryhans.forcastmvvm.data.db.unitlocalized

import androidx.room.ColumnInfo

data class MetricCurrentWeatherEntry(
    @ColumnInfo(name = "temperature")
    override val temperature: Double,
    @ColumnInfo(name="visibility")
    override val visibility: Double
) : UnitSpcificCurrentWeatherEntry