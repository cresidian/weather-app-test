package com.example.weatherapptest.core.network

import com.example.weatherapptest.domain.model.WeatherAppBackendError

sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Error(val error: WeatherAppBackendError) : NetworkResponse<Nothing>()
}
