package com.example.weatherapptest.presentation.views.validation

import android.text.InputFilter
import android.text.Spanned


class InputFilterMin(private val min: Int) : InputFilter {
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
            if (input >= min) return null
        } catch (nfe: NumberFormatException) {
        }
        return ""
    }
}