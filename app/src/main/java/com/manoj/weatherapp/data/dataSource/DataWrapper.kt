package com.manoj.weatherapp.data.dataSource

import okhttp3.ResponseBody

sealed class DataWrapper<out T> {
    class Success<out T>(val value: T) : DataWrapper<T>()
    class Failure(val errorCode: Int?, val errorBody: ResponseBody?) : DataWrapper<Nothing>()
    data object Loading : DataWrapper<Nothing>()
    data object None: DataWrapper<Nothing>()
}
