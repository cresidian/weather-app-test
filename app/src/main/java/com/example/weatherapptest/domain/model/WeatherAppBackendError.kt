package com.example.weatherapptest.domain.model

import com.google.gson.annotations.SerializedName

data class WeatherAppBackendError(
    @SerializedName(value="message",alternate = ["detail"])
    val message: String,
    @SerializedName("ErrorCode")
    val errorCode: Int,
    @SerializedName("ErrorMessage")
    val errorMessage: String=""
)