package com.example.weatherapptest.presentation.weatherdetails.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapptest.domain.model.responses.WeatherDetailsResponse
import com.example.weatherapptest.util.convertMillisecondsToDate


@Composable
fun WeatherDetail(weatherDetailsResponse: WeatherDetailsResponse) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        backgroundColor = Color.White
    ) {
        val location = weatherDetailsResponse.name
        val temperatureInKelvin = weatherDetailsResponse.temperatureDetails.temp
        val temperatureInCelsius = (temperatureInKelvin - 273.15).toInt()
        val requestDate = if (weatherDetailsResponse.createdAt == null) {
            convertMillisecondsToDate(System.currentTimeMillis())
        } else {
            convertMillisecondsToDate(weatherDetailsResponse.createdAt)
        }
        Column(Modifier.padding(24.dp)) {
            Text("Location: $location", color = Color.Gray)
            Text("Current Temperature: $temperatureInCelsius °C", color = Color.Gray)
            Text("Date: $requestDate", color = Color.Gray)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text("More Details", color = Color.Gray)
            }
        }
    }
}