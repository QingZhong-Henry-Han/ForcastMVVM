package com.henryhans.forcastmvvm

import android.app.Application
import com.henryhans.forcastmvvm.data.WeatherstackApiService
import com.henryhans.forcastmvvm.data.db.ForecastDatabase
import com.henryhans.forcastmvvm.data.network.ConnectivityInterceptor
import com.henryhans.forcastmvvm.data.network.ConnectivityInterceptorImpl
import com.henryhans.forcastmvvm.data.network.WeatherNetworkDataSource
import com.henryhans.forcastmvvm.data.network.WeatherNetworkDataSourceImpl
import com.henryhans.forcastmvvm.data.repository.ForecastRepositoy
import com.henryhans.forcastmvvm.data.repository.ForecastRepositoyImpl
import com.henryhans.forcastmvvm.ui.weather.current.CurrentWeatherViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton {
            ForecastDatabase(instance())
        }

        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WeatherstackApiService(instance()) }

        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepositoy>() with singleton { ForecastRepositoyImpl(instance(), instance())}

        bind() from provider { CurrentWeatherViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
    }

}