package com.henryhans.forcastmvvm.ui.weather.current

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.henryhans.forcastmvvm.R
import com.henryhans.forcastmvvm.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.launch
import okhttp3.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.io.IOException

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory : CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)

        bindUI()
        //fetchJson()
    }

    private fun bindUI() = launch{
        val currentWeather = viewModel.weather.await()

        currentWeather.observe(viewLifecycleOwner, Observer{

            if( it == null )
                return@Observer

            textView.text = it.toString()
        })

    }


    fun fetchJson(){

        //textView.text = "inside fetchJson"
        val url = "http://api.weatherstack.com/current?access_key=fa7662dd524935c210fde528d6e65cb1&query=New%20York"

        val request = Request
            .Builder()
            .url(url)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback{
            override fun onResponse(call: Call, response: Response) {
                textView.text = response?.body?.string()
                println(response?.body?.string())
            }

            override fun onFailure(call: Call, e: IOException) {
                //textView.text = "exception"
                println("Exception")
            }
        })
    }
}