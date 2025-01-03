package com.ijcsj.ui_library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageButton;

public class WMImageButton extends androidx.appcompat.widget.AppCompatImageView {

    public WMImageButton(Context context) {
        super(context);
    }

    public WMImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WMImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec);
    }
}