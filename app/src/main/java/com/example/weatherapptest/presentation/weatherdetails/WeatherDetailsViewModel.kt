package com.example.weatherapptest.presentation.weatherdetails

import android.util.Log
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
        data class ShowLoad(val isShow: Boolean) : WeatherDetailsViewStates()
        data class ShowError(val error: String) : WeatherDetailsViewStates()
        data class SetWeatherDetails(val weatherDetailsResponse: WeatherDetailsResponse) :
            WeatherDetailsViewStates()
    }

    fun getWeatherDetails() {
        emitEvent(WeatherDetailsViewStates.ShowLoad(true))
        viewModelScope.launch {
            val localWeatherDetails = localWeatherDetailsUseCase.invoke()
            if (localWeatherDetails != null) {
                emitEvent(WeatherDetailsViewStates.ShowLoad(false))
                emitEvent(WeatherDetailsViewStates.SetWeatherDetails(localWeatherDetails))
            }
            when (val result = remoteWeatherDetailsUseCase.invoke(31.5322138, 74.2846168)) {
                is NetworkResponse.Success -> {
                    emitEvent(WeatherDetailsViewStates.SetWeatherDetails(result.data))
                }
                is NetworkResponse.Error -> {
                    //emitEvent(WeatherDetailsViewStates.ShowError(result.error.message))
                }
            }
        }
    }
}