package com.example.weatherapptest.data.sources.remote

import com.example.weatherapptest.domain.model.WeatherDetails
import com.example.weatherapptest.domain.model.requests.*
import com.example.weatherapptest.domain.model.responses.*
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApiEndpoint {

    companion object {
        private const val API_VERSION_PREFIX = "/data"
        private const val API_VERSION = "$API_VERSION_PREFIX/2.5"

        /** Paths **/
        const val PATH_CURRENT_WEATHER = "$API_VERSION/weather"
    }

    @GET(PATH_CURRENT_WEATHER)
    suspend fun getCurrentWeatherDetails(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): WeatherDetails

}