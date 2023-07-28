package com.example.weatherapptest.domain.repository

import com.example.weatherapptest.core.network.NetworkResponse
import com.example.weatherapptest.domain.model.responses.WeatherDetailsResponse

interface WeatherRepository {
    suspend fun getWeatherDetailsFromApi(
        latitude: Double,
        longitude: Double
    ): NetworkResponse<WeatherDetailsResponse>

    suspend fun getWeatherDetailsFromLocalDb(): WeatherDetailsResponse
}