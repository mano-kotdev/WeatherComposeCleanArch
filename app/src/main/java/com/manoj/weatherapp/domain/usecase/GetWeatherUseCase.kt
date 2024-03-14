package com.manoj.weatherapp.domain.usecase

import com.manoj.weatherapp.data.dataSource.DataWrapper
import com.manoj.weatherapp.domain.model.WeatherResponse
import com.manoj.weatherapp.domain.repository.ApiRepo
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val apiRepo: ApiRepo) {
    suspend operator fun invoke(
        city: String,
        unit: String,
        appId: String
    ): DataWrapper<WeatherResponse> {
        return apiRepo.getWeather(city, unit, appId)
    }
}