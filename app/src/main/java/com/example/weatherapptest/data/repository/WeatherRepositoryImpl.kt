package com.example.weatherapptest.data.repository

import com.example.weatherapptest.core.network.NetworkResponse
import com.example.weatherapptest.core.network.base.BaseNetworkApi
import com.example.weatherapptest.data.sources.local.database.WeatherAppDatabase
import com.example.weatherapptest.data.sources.remote.OpenWeatherApiEndpoint
import com.example.weatherapptest.domain.model.WeatherDetails
import com.example.weatherapptest.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val apiEndpoint: OpenWeatherApiEndpoint,
    private val weatherAppDatabase: WeatherAppDatabase
) : BaseNetworkApi(), WeatherRepository {

    override suspend fun getWeatherDetailsFromApi(): NetworkResponse<WeatherDetails> {
        val response = executeApiCall {

            apiEndpoint.getCurrentWeatherDetails()
        }
        if (response is NetworkResponse.Success) {

        }
        return response
    }

    override suspend fun getWeatherDetailsFromLocalDb(): WeatherDetails? {
        TODO("Not yet implemented")
    }

}