package com.example.weatherapptest.domain.model.responses


import androidx.room.Entity
import com.example.weatherapptest.domain.model.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_details_table")
data class WeatherDetailsResponse(
    val base: String,
    val cod: Int,
    val dt: Int,
    val id: Int,
    @SerializedName("main")
    val temperatureDetails: TemperatureDetails,
    val name: String,
    val timezone: Int,
    val visibility: Int,
)