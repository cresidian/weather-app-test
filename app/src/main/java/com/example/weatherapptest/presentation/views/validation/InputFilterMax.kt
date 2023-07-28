package com.example.weatherapptest.presentation.views.validation

import android.text.InputFilter
import android.text.Spanned


class InputFilterMax(private val max: Int) : InputFilter {
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            val input = (dest.toString() + source.toString()).toInt()
            if (input <= max) return null
        } catch (nfe: NumberFormatException) {
        }
        return ""
    }
}