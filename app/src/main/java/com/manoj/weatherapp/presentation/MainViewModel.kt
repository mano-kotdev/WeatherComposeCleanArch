package com.manoj.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manoj.weatherapp.data.dataSource.DataWrapper
import com.manoj.weatherapp.domain.model.WeatherResponse
import com.manoj.weatherapp.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private var _mWeatherResponse = MutableStateFlow<DataWrapper<WeatherResponse>>(DataWrapper.None)
    val mWeatherResponse: StateFlow<DataWrapper<WeatherResponse>>
        get() = _mWeatherResponse.asStateFlow()

    fun getWeather() {
        _mWeatherResponse.value = DataWrapper.Loading
        viewModelScope.launch {
            _mWeatherResponse.value = getWeatherUseCase.invoke(
                "dhaka,bd", "metric", "8118ed6ee68db2debfaaa5a44c832918"
            )
        }
    }
}