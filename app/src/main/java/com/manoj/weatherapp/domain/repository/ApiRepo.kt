package com.manoj.weatherapp.domain.repository

import com.manoj.weatherapp.data.dataSource.DataWrapper
import com.manoj.weatherapp.domain.model.WeatherResponse


interface ApiRepo {
    suspend fun getWeather(
        city: String,
        unit: String,
        appId: String
    ): DataWrapper<WeatherResponse>
}