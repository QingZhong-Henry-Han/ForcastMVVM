package com.henryhans.forcastmvvm.data


import com.henryhans.forcastmvvm.data.network.ConnectivityInterceptor
import com.henryhans.forcastmvvm.data.network.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY="fa7662dd524935c210fde528d6e65cb1"

//http://api.weatherstack.com/current?access_key=fa7662dd524935c210fde528d6e65cb1&query=New%20York

interface WeatherstackApiService {
    @GET("current")
    fun  getCurrentWeather(
        @Query("q") location: String
    ): Deferred<CurrentWeatherResponse>

    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): WeatherstackApiService{

            val requestInterceptor = Interceptor{ chain ->

                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)

            }

            //loggingHttpInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherstackApiService::class.java)

       }
    }
}