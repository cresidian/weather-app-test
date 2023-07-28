package com.example.weatherapptest.presentation.views.validation

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


class IPAddressTextWatcher2(private val editText: EditText) : TextWatcher {
    var cursorPosition = 0
    override fun beforeTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {}

    override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {}

    override fun afterTextChanged(editable: Editable) {
        editText.removeTextChangedListener(this)
        try {
            cursorPosition = editText.selectionStart
            if (editable.isNotEmpty()) {
                var tempIp = ""
                var newIp = ""
                val strIp: String = editText.text.toString()
                val tempParamArr = strIp.split("\\.").toTypedArray()
                if (tempParamArr.isNotEmpty()) {
                    cursorPosition -= tempParamArr.size - 1
                    for (i in tempParamArr.indices) {
                        tempIp += tempParamArr[i]
                    }
                } else {
                    tempIp = strIp
                }
                for (count in tempIp.indices) {
                    if (count == 3 || count == 6 || count == 9 || count == 12) {
                        newIp += "."
                        newIp += tempIp[count]
                        cursorPosition++
                    } else {
                        newIp += tempIp[count]
                    }
                }
                editText.setText(newIp)
                if (newIp.length > cursorPosition) editText.setSelection(cursorPosition) else editText.setSelection(
                    newIp.length
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        editText.addTextChangedListener(this)
    }
}