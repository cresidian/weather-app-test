package com.example.weatherapptest.presentation.views.validation

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout


abstract class EditTextValidationRule(final override val view: TextInputLayout) :
    FormValidationRule {
    val editText: EditText = view.editText!!
    var originalHint: String? = ""

    init {
        if (view.hint != null) {
            originalHint = view.hint!!.toString().replace("*", "").trim { it <= ' ' }
        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (view.error.isNullOrEmpty()) {
                    return
                }
                view.isErrorEnabled = false
                view.error = null
            }
        })
    }

    override fun showError() {
        editText.clearFocus()
        view.isErrorEnabled = true
        view.error = errorMessage
    }

    override fun hideError() {
        view.isErrorEnabled = false
        view.error = null
    }
}
