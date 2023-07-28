package com.example.weatherapptest.presentation.weatherdetails.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherapptest.presentation.jetpack.ui.theme.Purple700

@Composable
fun LoadingIndicator(isShow:Boolean) {
    if (isShow) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Purple700)
        }
    }
}