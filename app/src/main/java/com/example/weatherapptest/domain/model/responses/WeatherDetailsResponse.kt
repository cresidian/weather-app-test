package com.example.weatherapptest.domain.model.responses

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapptest.domain.model.TemperatureDetails
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "weather_details_table")
data class WeatherDetailsResponse(
    @PrimaryKey(autoGenerate = true) var roomId: Int? = null,
    val cod: Int,
    val dt: Int,
    val id: Int,
    val timezone: Int,
    val createdAt: Long?,
    val visibility: Int,
    val name: String,
    val base: String,
    @Embedded(prefix = "temperature_")
    @SerializedName("main")
    val temperatureDetails: TemperatureDetails,
) : Serializable
