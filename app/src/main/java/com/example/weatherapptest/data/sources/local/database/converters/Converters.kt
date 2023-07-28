package com.example.weatherapptest.data.sources.local.database.converters

import androidx.room.TypeConverter
import com.example.weatherapptest.domain.model.TemperatureDetails
import com.example.weatherapptest.domain.model.responses.WeatherDetailsResponse
import com.example.weatherapptest.util.Constants
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromWeatherDetailsResponse(weatherDetailsResponse: WeatherDetailsResponse): String {
        return gson.toJson(weatherDetailsResponse)
    }

    @TypeConverter
    fun toWeatherDetailsResponse(json: String): WeatherDetailsResponse {
        return gson.fromJson(json, WeatherDetailsResponse::class.java)
    }

    @TypeConverter
    fun fromTemperatureDetails(temperatureDetails: TemperatureDetails): String {
        return gson.toJson(temperatureDetails)
    }

    @TypeConverter
    fun toTemperatureDetails(json: String): TemperatureDetails {
        return gson.fromJson(json, TemperatureDetails::class.java)
    }
}