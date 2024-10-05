package com.ijcsj.ui_library.toolitem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;

public class RoundBackgroundSpan extends ReplacementSpan {
    private int backgroundColor;
    private int radius;
 
    public RoundBackgroundSpan(int backgroundColor, int radius) {
        this.backgroundColor = backgroundColor;
        this.radius = radius;
    }
 
    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        RectF rect = new RectF(x, top, x + paint.measureText(text, start, end), bottom);
        paint.setColor(backgroundColor);
        canvas.drawRoundRect(rect, radius, radius, paint);
    }
 
    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end));
    }
}