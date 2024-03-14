package com.manoj.weatherapp.data.repository

import com.manoj.weatherapp.data.dataSource.Api
import com.manoj.weatherapp.data.dataSource.ApiCall
import com.manoj.weatherapp.domain.repository.ApiRepo
import com.manoj.weatherapp.data.dataSource.DataWrapper
import com.manoj.weatherapp.domain.model.WeatherResponse
import javax.inject.Inject

class ApiRepoImpl @Inject constructor(
    private val api: Api
) : ApiRepo, ApiCall {
    override suspend fun getWeather(
        city: String,
        unit: String,
        appId: String
    ): DataWrapper<WeatherResponse> = apiCall {
        api.getWeather(city, unit, appId)
    }
}