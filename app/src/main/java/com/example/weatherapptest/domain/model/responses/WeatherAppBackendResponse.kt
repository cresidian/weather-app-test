package com.example.weatherapptest.domain.model.responses


import com.google.gson.annotations.SerializedName

data class WeatherAppBackendResponse(
    @SerializedName("Data")
    val data: Boolean,
    @SerializedName("Error")
    val error: Boolean=false,
    @SerializedName("ErrorCode")
    val errorCode: String="",
    @SerializedName("ErrorMessage")
    val errorMessage: String="",
    @SerializedName("TimeStamp")
    val timeStamp: String="",
)