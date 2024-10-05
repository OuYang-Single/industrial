package com.ijcsj.ui_library.widget

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.ijcsj.ui_library.R
import com.ijcsj.ui_library.anko.dp


/**
 * 用于显示 Loading 的 [View]，支持颜色和大小的设置。
 *
 * @author cginechen
 * @date 2016-09-21
 */
class LoadingView @JvmOverloads
constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.LoadingStyle
) : View(context, attrs, defStyleAttr) {
    private var mSize = 0
    private var mPaintColor = 0
    private var mAnimateValue = 0
    private var mAnimator: ValueAnimator? = null
    private var mPaint: Paint = Paint()

    init {
        val array: TypedArray =
            getContext().obtainStyledAttributes(attrs, R.styleable.LoadingView, defStyleAttr, 0)
        mSize = array.getDimensionPixelSize(R.styleable.LoadingView_loadingViewSize, 32.dp)
        mPaintColor = array.getInt(R.styleable.LoadingView_android_color, Color.WHITE)
        array.recycle()
        initPaint()
    }

    constructor(context: Context?, size: Int, color: Int) : this(context) {
        mSize = size
        mPaintColor = color
        initPaint()
    }

    private fun initPaint() {
        mPaint.color = mPaintColor
        mPaint.isAntiAlias = true
        mPaint.strokeCap = Paint.Cap.ROUND
    }

    fun setColor(color: Int) {
        mPaintColor = color
        mPaint.color = color
        invalidate()
    }

    fun setSize(size: Int) {
        mSize = size
        requestLayout()
    }

    private val mUpdateListener: ValueAnimator.AnimatorUpdateListener =
        ValueAnimator.AnimatorUpdateListener { animation: ValueAnimator ->
            mAnimateValue = animation.animatedValue as Int
            invalidate()
        }

    fun start() {
        if (mAnimator == null) {
            mAnimator = ValueAnimator.ofInt(0, LINE_COUNT - 1)
            mAnimator?.addUpdateListener(mUpdateListener)
            mAnimator?.duration = 600
            mAnimator?.repeatMode = ValueAnimator.RESTART
            mAnimator?.repeatCount = ValueAnimator.INFINITE
            mAnimator?.interpolator = LinearInterpolator()
            mAnimator?.start()
        } else if (!mAnimator!!.isStarted) {
            mAnimator!!.start()
        }
    }

    fun stop() {
        if (mAnimator != null) {
            mAnimator?.removeUpdateListener(mUpdateListener)
            mAnimator?.removeAllUpdateListeners()
            mAnimator?.cancel()
            mAnimator = null
        }
    }

    private fun drawLoading(canvas: Canvas, rotateDegrees: Int) {
        val width = mSize / 12
        val height = mSize / 6
        mPaint.strokeWidth = width.toFloat()
        canvas.rotate(rotateDegrees.toFloat(), (mSize shr 1).toFloat(), (mSize shr 1).toFloat())
        canvas.translate((mSize shr 1).toFloat(), (mSize shr 1).toFloat())
        for (i in 0 until LINE_COUNT) {
            canvas.rotate(DEGREE_PER_LINE.toFloat())
            mPaint.alpha = (255f * (i + 1) / LINE_COUNT).toInt()
            canvas.translate(0f, ((-mSize shr 1) + (width shr 1)).toFloat())
            canvas.drawLine(0f, 0f, 0f, height.toFloat(), mPaint)
            canvas.translate(0f, ((mSize shr 1) - (width shr 1)).toFloat())
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(mSize, mSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val saveCount = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null)
        drawLoading(canvas, mAnimateValue * DEGREE_PER_LINE)
        canvas.restoreToCount(saveCount)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stop()
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == VISIBLE) {
            start()
        } else {
            stop()
        }
    }

    companion object {
        private const val LINE_COUNT = 12
        private const val DEGREE_PER_LINE = 360 / LINE_COUNT
    }
}