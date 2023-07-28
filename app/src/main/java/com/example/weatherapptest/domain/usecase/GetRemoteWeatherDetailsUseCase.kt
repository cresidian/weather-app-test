package com.example.weatherapptest.domain.usecase

import com.example.weatherapptest.core.network.NetworkResponse
import com.example.weatherapptest.domain.model.responses.WeatherDetailsResponse
import com.example.weatherapptest.domain.repository.WeatherRepository

class GetRemoteWeatherDetailsUseCase(private val repository: WeatherRepository) {
    suspend fun invoke(
        latitude: Double,
        longitude: Double
    ): NetworkResponse<WeatherDetailsResponse> =
        repository.getWeatherDetailsFromApi(latitude = latitude, longitude = longitude)
}