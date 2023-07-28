package com.example.weatherapptest.presentation.views.textview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.style.ReplacementSpan

enum class ImageSpanGravity { CENTERED, BOTTOM }

class GravityImageSpan(val drawable: Drawable,
                       val gravity: ImageSpanGravity = ImageSpanGravity.CENTERED,
                       private val drawablePaddingStart: Int = 0,
                       private val drawablePaddingEnd: Int = 0) : ReplacementSpan() {

    constructor(context: Context,
                drawableRes: Int,
                gravity: ImageSpanGravity = ImageSpanGravity.CENTERED,
                drawablePaddingStart: Int = 0,
                drawablePaddingEnd: Int = 0) : this(context.getDrawable(drawableRes)!!, gravity, drawablePaddingStart, drawablePaddingEnd)

    init {
        drawable.mutate()
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    }

    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fontMetricsInt: Paint.FontMetricsInt?): Int {
        val rect = drawable.bounds
        if (fontMetricsInt != null) {
            val paintFontMetrics = paint.fontMetricsInt
            fontMetricsInt.ascent = paintFontMetrics.ascent
            fontMetricsInt.descent = paintFontMetrics.descent
            fontMetricsInt.top = paintFontMetrics.top
            fontMetricsInt.bottom = paintFontMetrics.bottom
        }
        return rect.right + drawablePaddingStart + drawablePaddingEnd
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        val target = drawable
        canvas.save()
        val drawableHeight = target.intrinsicHeight
        val fontAscent = paint.fontMetricsInt.ascent
        val fontDescent = paint.fontMetricsInt.descent
        val translationY = if (gravity == ImageSpanGravity.CENTERED) {
            (bottom - top) / 2 - drawableHeight / 2
        } else {
            bottom - target.bounds.bottom + (drawableHeight - fontDescent + fontAscent)
        }
        canvas.translate(x + drawablePaddingStart, translationY.toFloat())
        target.draw(canvas)
        canvas.restore()
    }
}
