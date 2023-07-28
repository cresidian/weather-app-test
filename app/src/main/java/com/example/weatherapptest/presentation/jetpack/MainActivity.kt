package com.example.weatherapptest.presentation.jetpack

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.weatherapptest.R
import com.example.weatherapptest.presentation.jetpack.ui.theme.Purple700
import com.example.weatherapptest.presentation.jetpack.ui.theme.WeatherAppTestTheme
import com.example.weatherapptest.presentation.weatherdetails.WeatherDetailsViewModel
import com.example.weatherapptest.presentation.weatherdetails.composables.LoadingIndicator
import com.example.weatherapptest.presentation.weatherdetails.composables.WeatherDetail
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
                        }, backgroundColor = Purple700) {
                            Icon(
                                Icons.Default.Build,
                                tint = Color.White,
                                contentDescription = "Add"
                            )
                        }
                    }

                    ) {
                        val state by viewModel.viewStates.collectAsState()
                        when (state) {
                            is WeatherDetailsViewModel.WeatherDetailsViewStates.Loading -> {
                                val isShow = (state as WeatherDetailsViewModel.WeatherDetailsViewStates.Loading).isShow
                                LoadingIndicator(isShow)
                            }
                            is WeatherDetailsViewModel.WeatherDetailsViewStates.Error -> {
                                val error = (state as WeatherDetailsViewModel.WeatherDetailsViewStates.Error).error
                                Toast.makeText(LocalContext.current,error,Toast.LENGTH_SHORT).show()
                            }
                            is WeatherDetailsViewModel.WeatherDetailsViewStates.WeatherDetails -> {
                                val weatherDetailsResponse = (state as WeatherDetailsViewModel.WeatherDetailsViewStates.WeatherDetails).weatherDetailsResponse
                                WeatherDetail(weatherDetailsResponse)
                            }
                            else -> {}
                        }

                    }
                }
            }
        }
    }
}

