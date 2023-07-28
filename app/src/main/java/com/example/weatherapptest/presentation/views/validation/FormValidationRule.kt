package com.example.weatherapptest.presentation.views.validation

import android.view.View

interface FormValidationRule {
    val view: View
    val errorMessage: String
    fun validate(): Boolean
    fun showError()
    fun hideError()
}
