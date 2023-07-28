package com.example.weatherapptest.presentation.weatherdetails

import androidx.lifecycle.viewModelScope
import com.example.weatherapptest.core.base.viewmodel.BaseViewModel
import com.example.weatherapptest.core.network.NetworkResponse
import com.example.weatherapptest.domain.model.responses.WeatherDetailsResponse
import com.example.weatherapptest.domain.usecase.GetLocalWeatherDetailsUseCase
import com.example.weatherapptest.domain.usecase.GetRemoteWeatherDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val remoteWeatherDetailsUseCase: GetRemoteWeatherDetailsUseCase,
    private val localWeatherDetailsUseCase: GetLocalWeatherDetailsUseCase
) : BaseViewModel<WeatherDetailsViewModel.WeatherDetailsViewStates>() {

    sealed class WeatherDetailsViewStates {
        data class Loading(val isShow: Boolean) : WeatherDetailsViewStates()
        data class Error(val error: String) : WeatherDetailsViewStates()
        data class WeatherDetails(val weatherDetailsResponse: WeatherDetailsResponse) :
            WeatherDetailsViewStates()
    }

    init {
        getWeatherDetails()
    }

    fun getWeatherDetails() {
        emitEvent(WeatherDetailsViewStates.Loading(true))
        viewModelScope.launch {
            val localWeatherDetails = localWeatherDetailsUseCase.invoke()
            if (localWeatherDetails != null) {
                emitEvent(WeatherDetailsViewStates.Loading(false))
                emitState(WeatherDetailsViewStates.WeatherDetails(localWeatherDetails))
            }
            when (val result = remoteWeatherDetailsUseCase.invoke(LATITUDE, LONGITUDE)) {
                is NetworkResponse.Success -> {
                    emitEvent(WeatherDetailsViewStates.Loading(false))
                    emitState(WeatherDetailsViewStates.WeatherDetails(result.data))
                }
                is NetworkResponse.Error -> {
                    emitEvent(WeatherDetailsViewStates.Loading(false))
                    emitEvent(WeatherDetailsViewStates.Error(result.error.message))
                }
            }
        }
    }

    companion object {
        const val LATITUDE = 31.5322138
        const val LONGITUDE = 74.2846168
    }
}