package com.example.weatherapptest.presentation.jetpack

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapptest.R
import com.example.weatherapptest.domain.model.responses.WeatherDetailsResponse
import com.example.weatherapptest.presentation.jetpack.ui.theme.Purple700
import com.example.weatherapptest.presentation.jetpack.ui.theme.WeatherAppTestTheme
import com.example.weatherapptest.presentation.weatherdetails.WeatherDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Scaffold(backgroundColor = Color.White, topBar = {
                        TopAppBar(title = {
                            Text(
                                text = getString(R.string.app_name),
                                color = Color.White
                            )
                        }, backgroundColor = Purple700)
                    }, floatingActionButton = {
                        FloatingActionButton(onClick = {
                            viewModel.getWeatherDetails()
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }

                    ) {
                        val state by viewModel.viewStates.collectAsState()
                        when (state) {
                            is WeatherDetailsViewModel.WeatherDetailsViewStates.ShowLoad -> {
                                val isShow =
                                    (state as WeatherDetailsViewModel.WeatherDetailsViewStates.ShowLoad).isShow
                            }
                            is WeatherDetailsViewModel.WeatherDetailsViewStates.ShowError -> {
                                val error =
                                    (state as WeatherDetailsViewModel.WeatherDetailsViewStates.ShowError).error
                                print(error)
                            }
                            is WeatherDetailsViewModel.WeatherDetailsViewStates.SetWeatherDetails -> {
                                val weatherDetailsResponse =
                                    (state as WeatherDetailsViewModel.WeatherDetailsViewStates.SetWeatherDetails).weatherDetailsResponse
                                WeatherDetail(weatherDetailsResponse, "29-Jul-23")
                            }
                            else -> {
                                // Handle other states if required
                            }
                        }

                    }
                }
            }
        }
    }
}


@Composable
fun WeatherDetail(weatherDetailsResponse: WeatherDetailsResponse, date: String) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        backgroundColor = Color.White
    ) {
        val location = "${weatherDetailsResponse.name}, ${weatherDetailsResponse.name}"
        val temperatureInKelvin = weatherDetailsResponse.temperatureDetails.temp
        val temperatureInCelsius = (temperatureInKelvin - 273.15).toInt()

        Column(Modifier.padding(24.dp)) {
            Text("Location: $location", color = Color.Gray)
            Text("Current Temperature: $temperatureInCelsius °C", color = Color.Gray)
            Text("Date: $date", color = Color.Gray)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text("More Details", color = Color.Gray)
            }
        }
    }
}