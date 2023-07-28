package com.example.weatherapptest.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapptest.data.sources.local.database.WeatherAppDatabase
import com.example.weatherapptest.data.sources.local.database.WeatherAppDatabase.Companion.DATA_BASE_NAME
import com.example.weatherapptest.data.sources.local.database.dao.WeatherDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DatabaseModule {

    @Provides
    @ViewModelScoped
    fun provideWeatherAppDatabase(@ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(
            applicationContext,
            WeatherAppDatabase::class.java, DATA_BASE_NAME
        ).build()


    @Provides
    @ViewModelScoped
    fun provideWeatherDetailsDao(weatherAppDatabase: WeatherAppDatabase): WeatherDetailsDao =
        weatherAppDatabase.weatherDetailsDao()

}