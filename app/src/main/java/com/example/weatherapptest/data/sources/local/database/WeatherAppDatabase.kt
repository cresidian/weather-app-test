package com.example.weatherapptest.data.sources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapptest.data.sources.local.database.WeatherAppDatabase.Companion.DATA_BASE_VERSION
import com.example.weatherapptest.data.sources.local.database.converters.Converters
import com.example.weatherapptest.data.sources.local.database.dao.WeatherDetailsDao
import com.example.weatherapptest.domain.model.responses.WeatherDetailsResponse

@Database(
    entities = [
        WeatherDetailsResponse::class,
    ],
    version = DATA_BASE_VERSION
)
@TypeConverters(Converters::class)
abstract class WeatherAppDatabase : RoomDatabase() {
    abstract fun weatherDetailsDao(): WeatherDetailsDao

    companion object {
        const val DATA_BASE_NAME = "WeatherAppDatabase"
        const val DATA_BASE_VERSION = 1
    }
}
