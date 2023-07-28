package com.example.weatherapptest.presentation.weatherdetails

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.weatherapptest.core.base.activty.BaseActivity
import com.example.weatherapptest.databinding.ActivityWeatherDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class WeatherDetailsActivity : BaseActivity() {

    private lateinit var bind: ActivityWeatherDetailsBinding
    private val viewModel: WeatherDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityWeatherDetailsBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)
        init()
        initObservers()
    }

    private fun init() {

    }

    private fun initObservers() {
        with(viewModel) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewStates.collect { uiState ->
                        when (uiState) {
                            is WeatherDetailsViewModel.WeatherDetailsViewStates.ShowLoad -> {
                                //bind.includeLoader.loaderView.setVisible(uiState.isShow)
                            }
                            is WeatherDetailsViewModel.WeatherDetailsViewStates.ShowError -> {
                                showToast(uiState.error)
                            }
                            is WeatherDetailsViewModel.WeatherDetailsViewStates.SetWeatherDetails -> {

                            }
                            else -> {

                            }
                        }
                    }
                }
            }
        }
    }
}