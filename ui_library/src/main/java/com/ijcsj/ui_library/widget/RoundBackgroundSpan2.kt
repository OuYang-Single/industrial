package com.ijcsj.ui_library.widget

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan
import kotlin.math.round


/**
 * @description:
 * @author:  79120
 * @date :   2021/4/2 17:13
 */
class RoundBackgroundSpan2(
    private val radius: Float,
    private val margin: Int,
    private val bgColor: Int
) : ReplacementSpan() {
    private var mWidthSize = 0

    /**
     * 配合maxWidth来确定SpannableString的长度
     */
    override fun getSize(
        paint: Paint,
        text: CharSequence?,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?,
    ): Int {
        val textWidth = paint.measureText(text, start, end)
        val paintFontMetrics = paint.fontMetrics
        fm?.apply {
            ascent = paintFontMetrics.ascent.toInt()
            bottom = paintFontMetrics.bottom.toInt() + radius.toInt()
            descent = paintFontMetrics.descent.toInt()
            leading = paintFontMetrics.leading.toInt()
            top = paintFontMetrics.top.toInt() - radius.toInt()
        }
        mWidthSize = margin + round(textWidth + 3 * radius).toInt()
        return mWidthSize
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint,
    ) {
        val bgRect = RectF(
            x,
            top.toFloat(),
            x + mWidthSize - margin,
            bottom.toFloat()
        )
        val textColor = paint.color
        val textSize = paint.textSize
        paint.style = Paint.Style.FILL
        paint.color = bgColor
        paint.isAntiAlias = true
        canvas.drawRoundRect(bgRect, radius, radius, paint)
        paint.color = textColor
        paint.textSize = textSize
        canvas.drawText(text, start, end, x + 1.5F * radius, y.toFloat(), paint)
    }
}