package com.manoj.weatherapp.data.dataSource

import com.manoj.weatherapp.domain.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("weather")
    suspend fun getWeather(
        @Query("q")
        city: String,
        @Query("units")
        unit: String,
        @Query("APPID")
        appId: String
    ): WeatherResponse
}