package com.example.weatherapptest.presentation.views.counter

interface ICounterView {
    var minValue: Int
    var maxValue: Int
    var stepCount: Int
    fun setCounterBackground(resourceId: Int)
    fun setCounterButtonsColor(resourceId: Int)
    fun setCounterButtonsSize(size: Int)
    fun setCounterFieldSize(height: Int, width: Int)
    fun setCounterTextSize(resourceId: Int)
    fun enableUserInput(enable: Boolean)
    fun setDecrementButtonEnabled(isEnable: Boolean)
    fun setIncrementButtonEnabled(isEnable: Boolean)
    fun setButtonsCustomSize(size: Int)
    fun setCounterTextPadding(padding: Int)
    fun setButtonsImageSize(size: Int)
    fun setCounterButtonListener(
        counterButtonsCallback: CounterView.CounterButtonsCallback
    )
    fun setCounterValueChangedListener(
        counterValueChangedCallback: CounterView.CounterValueChangedCallback
    )
}