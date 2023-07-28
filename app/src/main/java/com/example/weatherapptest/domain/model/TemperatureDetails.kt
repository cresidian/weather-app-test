package com.example.weatherapptest.domain.model

import androidx.room.PrimaryKey


data class TemperatureDetails(
    @PrimaryKey(autoGenerate = true) var roomId: Int? = 0,
    val feelsLike: Double,
    val grndLevel: Int,
    val humidity: Int,
    val pressure: Int,
    val seaLevel: Int,
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double
)