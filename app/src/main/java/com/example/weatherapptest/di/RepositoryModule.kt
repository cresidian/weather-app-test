package com.example.weatherapptest.di

import com.example.weatherapptest.data.repository.*
import com.example.weatherapptest.data.sources.local.database.WeatherAppDatabase
import com.example.weatherapptest.data.sources.remote.OpenWeatherApiEndpoint
import com.example.weatherapptest.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideWeatherRepository(
        endpoint: OpenWeatherApiEndpoint,
        weatherAppDatabase: WeatherAppDatabase
    ): WeatherRepository {
        return WeatherRepositoryImpl(endpoint, weatherAppDatabase)
    }

}