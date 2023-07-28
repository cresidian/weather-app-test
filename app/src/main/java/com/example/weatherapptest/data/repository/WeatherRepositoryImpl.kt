package com.example.weatherapptest.data.repository

import com.example.weatherapptest.core.network.NetworkResponse
import com.example.weatherapptest.core.network.base.BaseNetworkApi
import com.example.weatherapptest.data.sources.local.database.WeatherAppDatabase
import com.example.weatherapptest.data.sources.remote.OpenWeatherApiEndpoint
import com.example.weatherapptest.domain.model.responses.WeatherDetailsResponse
import com.example.weatherapptest.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val apiEndpoint: OpenWeatherApiEndpoint,
    private val weatherAppDatabase: WeatherAppDatabase
) : BaseNetworkApi(), WeatherRepository {

    override suspend fun getWeatherDetailsFromApi(
        latitude: Double,
        longitude: Double
    ): NetworkResponse<WeatherDetailsResponse> {
        val response = executeApiCall {
            apiEndpoint.getCurrentWeatherDetails(latitude = latitude, longitude = longitude)
        }
        if (response is NetworkResponse.Success) {
            saveWeatherDetailsToLocalDb(response.data)
        }
        return response
    }

    override suspend fun getWeatherDetailsFromLocalDb(): WeatherDetailsResponse? {
        return weatherAppDatabase.weatherDetailsDao().getWeatherDetails()
    }

    override suspend fun saveWeatherDetailsToLocalDb(weatherDetails: WeatherDetailsResponse) {
        weatherAppDatabase.weatherDetailsDao()
            .insert(weatherDetails.copy(createdAt = System.currentTimeMillis()))
    }

}