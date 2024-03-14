package com.manoj.weatherapp.data.dataSource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface ApiCall {
    suspend fun <T> apiCall(call: suspend () -> T): DataWrapper<T>{
        return withContext(Dispatchers.IO) {
            try {
                DataWrapper.Success(call.invoke())
            } catch (e: Throwable) {
                when (e) {
                    is HttpException -> {
                        DataWrapper.Failure(e.code(), e.response()?.errorBody())
                    }

                    else -> {
                        DataWrapper.Failure(null, null)
                    }
                }
            }
        }
    }
}