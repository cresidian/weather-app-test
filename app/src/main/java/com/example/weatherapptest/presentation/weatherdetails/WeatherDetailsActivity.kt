package com.example.weatherapptest.presentation.weatherdetails

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.weatherapptest.R
import com.example.weatherapptest.app.theme.Purple700
import com.example.weatherapptest.app.theme.WeatherAppTestTheme
import com.example.weatherapptest.core.util.isInternetConnected
import com.example.weatherapptest.presentation.weatherdetails.composables.LoadingIndicator
import com.example.weatherapptest.presentation.weatherdetails.composables.WeatherDetail
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@AndroidEntryPoint
class WeatherDetailsActivity : ComponentActivity() {

    private val viewModel: WeatherDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTestTheme {
                val context = LocalContext.current
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
                            Toast.makeText(
                                context,
                                getString(R.string.not_connected_to_internet),
                                Toast.LENGTH_SHORT
                            ).show()
                            /*if (!isInternetConnected(context)) {
                                Toast.makeText(
                                    context,
                                    getString(R.string.not_connected_to_internet),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            viewModel.getWeatherDetails()*/
                        }, backgroundColor = Purple700) {
                            Icon(
                                Icons.Default.Build,
                                tint = Color.White,
                                contentDescription = "Add"
                            )
                        }
                    }

                    ) {
                        //val state by viewModel.viewStates.collectAsState()
                        //val events by viewModel.viewEvents.collectAsState(null)
                        when (val state = viewModel.viewStates.collectAsState().value) {
                            is WeatherDetailsViewModel.WeatherDetailsViewStates.WeatherDetails -> {
                                WeatherDetail(state.weatherDetailsResponse)
                            }
                            else -> {}
                        }
                        when (val event = viewModel.viewEvents.collectAsState(null).value) {
                            is WeatherDetailsViewModel.WeatherDetailsViewStates.Loading -> {
                                LoadingIndicator(event.isShow)
                            }
                            is WeatherDetailsViewModel.WeatherDetailsViewStates.Error -> {
                                Toast.makeText(context, event.error, Toast.LENGTH_SHORT).show()
                                /***
                                 * We can use this event block to show error messages from api/server using a toast or composable
                                 * ***/
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }
}

