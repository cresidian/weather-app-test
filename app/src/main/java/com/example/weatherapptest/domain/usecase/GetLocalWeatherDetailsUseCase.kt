package com.example.weatherapptest.domain.usecase

import com.example.weatherapptest.domain.model.responses.WeatherDetailsResponse
import com.example.weatherapptest.domain.repository.WeatherRepository

class GetLocalWeatherDetailsUseCase(private val repository: WeatherRepository) {
    suspend fun invoke(): WeatherDetailsResponse = repository.getWeatherDetailsFromLocalDb()
}