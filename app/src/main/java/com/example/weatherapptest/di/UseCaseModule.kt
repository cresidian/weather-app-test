package com.example.weatherapptest.di

import com.example.weatherapptest.domain.repository.*
import com.example.weatherapptest.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideGetRemoteWeatherDetailsUseCase(weatherRepository: WeatherRepository): GetRemoteWeatherDetailsUseCase {
        return GetRemoteWeatherDetailsUseCase(weatherRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetLocalWeatherDetailsUseCase(weatherRepository: WeatherRepository): GetLocalWeatherDetailsUseCase {
        return GetLocalWeatherDetailsUseCase(weatherRepository)
    }
}