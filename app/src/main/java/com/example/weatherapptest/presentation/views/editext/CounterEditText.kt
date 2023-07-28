package com.example.weatherapptest.presentation.views.editext

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import androidx.appcompat.widget.AppCompatEditText


class CounterEditText  /* Must use this constructor in order for the layout files to instantiate the class properly */
    (context: Context, attrs: AttributeSet?) :
    AppCompatEditText(context, attrs) {
    private var keyImeChangeListener: KeyImeChange? = null
    fun setKeyImeChangeListener(listener: KeyImeChange?) {
        keyImeChangeListener = listener
    }

    interface KeyImeChange {
        fun onKeyIme(keyCode: Int, event: KeyEvent)
    }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {
        if (keyImeChangeListener != null) {
            keyImeChangeListener!!.onKeyIme(keyCode, event)
        }
        return false
    }
}