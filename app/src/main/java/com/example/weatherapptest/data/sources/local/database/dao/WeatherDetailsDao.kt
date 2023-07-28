package com.example.weatherapptest.data.sources.local.database.dao

import androidx.room.*
import com.example.weatherapptest.domain.model.responses.WeatherDetailsResponse

@Dao
interface WeatherDetailsDao : BaseDao<WeatherDetailsResponse> {

    @Query("SELECT * FROM weather_details_table")
    suspend fun getWeatherDetails(): WeatherDetailsResponse

    @Query("DELETE FROM weather_details_table")
    suspend fun deleteWeatherDetails()

}