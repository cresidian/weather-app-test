package com.example.weatherapptest.presentation.views.validation

import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.widget.EditText


class IPAddressTextWatcher(private val editText: EditText) : TextWatcher {
    override fun afterTextChanged(s: Editable) {
        if (!s.toString().matches(Regex(IPADDRESS_PATTERN))) {
            val ip = format(s.toString())
            editText.removeTextChangedListener(this)
            editText.setText(ip)
            editText.setTextKeepState(ip)
            Selection.setSelection(editText.text, ip.length)
            editText.addTextChangedListener(this)
        }
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    companion object {
        private const val IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"

        fun format(value: String): String {
            val userInput = "" + value.replace("[^\\d\\.]".toRegex(), "")
            val ipBuilder = StringBuilder()
            val address = userInput.split("\\.").toTypedArray()
            var glue: String? = null
            for (part in address) {
                if (glue != null) ipBuilder.append(glue)
                var p = Integer.valueOf(part)
                if (p >= 256) {
                    var i = 1
                    do {
                        p = Integer.valueOf(part.substring(0, part.length - i))
                        i++
                    } while (p >= 256)
                }
                ipBuilder.append(p)
                glue = "."
            }
            if (userInput[userInput.length - 1] == '.') ipBuilder.append(".")
            return ipBuilder.toString()
        }
    }
}