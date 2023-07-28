package com.example.weatherapptest.di

import android.content.Context
import com.example.weatherapptest.core.network.ApiClient
import com.example.weatherapptest.core.network.serlializer.DateSerializer
import com.example.weatherapptest.data.sources.remote.OpenWeatherApiEndpoint
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApiClient(
        @ApplicationContext appContext: Context,
        gson: Gson,
    ) = ApiClient(appContext, gson).getApiClient()

    @Singleton
    @Provides
    fun provideApiEndpoint(apiClient: Retrofit): OpenWeatherApiEndpoint =
        apiClient.create(OpenWeatherApiEndpoint::class.java)

    @Singleton
    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateSerializer())
            .create()
}