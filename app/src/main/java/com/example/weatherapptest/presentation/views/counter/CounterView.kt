package com.example.weatherapptest.presentation.views.counter

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.widget.TextViewCompat
import androidx.core.widget.doOnTextChanged
import com.example.weatherapptest.core.extensions.setEnable
import com.example.weatherapptest.databinding.LayoutCounterBinding

class CounterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ICounterView {

    val binding: LayoutCounterBinding =
        LayoutCounterBinding.inflate(LayoutInflater.from(context), this, true)

    var value: Int
        get() = binding.etCounter.text.toString().toInt()
        set(value) = binding.etCounter.setText(value.toString())
    override var minValue: Int = DEFAULT_MIN_VALUE
        set(value) {
            field = value
            binding.etCounter.setText(field.toString())
        }
    override var maxValue: Int = DEFAULT_MAX_VALUE
    override var stepCount: Int = DEFAULT_STEP_COUNT

    private lateinit var counterButtonsCallback: CounterButtonsCallback
    private lateinit var counterValueChangedCallback: CounterValueChangedCallback

    ///TODO(Will implement later)
    /*private var counterBackground = 0
    private var decrementButtonColor = 0
    private var incrementButtonColor = 0
    private var counterTextAppearance = 0*/

    init {
        ///TODO(Will implement later)
        /*context.withStyledAttributes(attrs, R.styleable.counter_view) {
            counterBackground = getResourceId(R.styleable.counter_view_counterBackground,0)
            decrementButtonColor = getColor(R.styleable.counter_view_decrementButtonColor,0)
            incrementButtonColor = getColor(R.styleable.counter_view_decrementButtonColor,0)
            counterTextAppearance = getResourceId(R.styleable.counter_view_counterTextAppearance,0)
        }*/
        setDecrementButtonEnabled(value != 0)
        binding.etCounter.doOnTextChanged { _, _, _, _ ->
            if (binding.etCounter.text!!.isEmpty()) {
                //binding.etCounter.append(minValue.toString())
                return@doOnTextChanged
            }
            if (binding.etCounter.text.toString().toInt() > maxValue) {
                binding.etCounter.setText(maxValue.toString())
                return@doOnTextChanged
            }
            if (minValue > DEFAULT_START_ZERO && binding.etCounter.text!!.startsWith(
                    DEFAULT_START_ZERO.toString()
                )
            ) {
                binding.etCounter.setText("")
                return@doOnTextChanged
            }
            setDecrementButtonEnabled(value != 0)
            if (this::counterValueChangedCallback.isInitialized)
                counterValueChangedCallback.onValueChanged(value)
        }
        binding.fabMinus.setOnClickListener {
            val count = binding.etCounter.text.toString().toInt() - stepCount
            if (count >= minValue)
                binding.etCounter.setText(count.toString())
            if (this::counterButtonsCallback.isInitialized)
                counterButtonsCallback.onDecrementButtonClicked()
        }
        binding.fabPlus.setOnClickListener {
            val count = binding.etCounter.text.toString().toInt() + stepCount
            if (count <= maxValue)
                binding.etCounter.setText(count.toString())
            if (this::counterButtonsCallback.isInitialized)
                counterButtonsCallback.onIncrementButtonClicked()
        }
    }

    override fun setCounterBackground(resourceId: Int) {
        binding.etCounter.background = ContextCompat.getDrawable(context, resourceId)
    }

    override fun setCounterButtonsColor(resourceId: Int) {
        binding.fabMinus.backgroundTintList = ColorStateList.valueOf(resourceId)
    }

    /*** Set button size FloatingActionButton.SIZE_MINI or FloatingActionButton.SIZE_NORMAL ***/
    override fun setCounterButtonsSize(size: Int) {
        binding.fabMinus.size = size
        binding.fabPlus.size = size
    }

    override fun setCounterTextSize(resourceId: Int) {
        TextViewCompat.setTextAppearance(binding.etCounter, resourceId)
    }

    override fun enableUserInput(enable: Boolean) {
        binding.etCounter.isFocusable = enable
    }

    override fun setCounterFieldSize(height: Int, width: Int) {
        binding.etCounter.width = resources.getDimensionPixelSize(width)
        binding.etCounter.height = resources.getDimensionPixelSize(height)
    }

    override fun setDecrementButtonEnabled(isEnable: Boolean) {
        binding.fabMinus.setEnable(isEnable)
    }

    override fun setIncrementButtonEnabled(isEnable: Boolean) {
        binding.fabPlus.setEnable(isEnable)
    }

    override fun setButtonsCustomSize(size: Int) {
        binding.fabPlus.customSize = resources.getDimensionPixelSize(size)
        binding.fabMinus.customSize = resources.getDimensionPixelSize(size)
    }

    override fun setButtonsImageSize(size: Int) {
        binding.fabPlus.setMaxImageSize(size)
        binding.fabMinus.setMaxImageSize(size)
    }

    override fun setCounterTextPadding(padding: Int) {
        binding.etCounter.setPadding(resources.getDimensionPixelSize(padding))
    }

    override fun setCounterButtonListener(
        counterButtonsCallback: CounterButtonsCallback
    ) {
        this.counterButtonsCallback = counterButtonsCallback
    }

    override fun setCounterValueChangedListener(
        counterValueChangedCallback: CounterValueChangedCallback
    ) {
        this.counterValueChangedCallback = counterValueChangedCallback
    }

    companion object {
        private const val DEFAULT_MIN_VALUE = 0
        private const val DEFAULT_MAX_VALUE = 999
        private const val DEFAULT_STEP_COUNT = 1
        private const val DEFAULT_START_ZERO = 0
    }

    interface CounterButtonsCallback {
        fun onDecrementButtonClicked()
        fun onIncrementButtonClicked()
    }

    interface CounterValueChangedCallback {
        fun onValueChanged(value: Int)
    }

}