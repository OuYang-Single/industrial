package com.cn.datalibrary.util

import android.content.Context
import android.util.AttributeSet
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.ijcsj.ui_library.widget.LineChart.MyLineChartRenderer


class MyLineChart : LineChart {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    override fun init() {
        super.init()
        //mRenderer 渲染器 设置为自己定义的渲染器
        mRenderer = MyLineChartRenderer(this, mAnimator, mViewPortHandler)
    }

    override fun getLineData(): LineData {
        return mData
    }

    override fun onDetachedFromWindow() {
        // releases the bitmap in the renderer to avoid oom error
        if (mRenderer != null && mRenderer is MyLineChartRenderer) {
            (mRenderer as MyLineChartRenderer).releaseBitmap()
        }
        super.onDetachedFromWindow()
    }
}