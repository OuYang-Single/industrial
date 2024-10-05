/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.ijcsj.ui_library.widget

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RadialGradient
import android.graphics.Rect
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.view.animation.LinearInterpolator
import java.lang.Float.min
import java.lang.Math.toRadians
import kotlin.math.max
import kotlin.math.sqrt
import kotlin.math.tan

class ShimmerDrawable : Drawable() {
    private val mUpdateListener = AnimatorUpdateListener { invalidateSelf() }
    private val mShimmerPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mDrawRect = Rect()
    private val mShaderMatrix = Matrix()
    private var mValueAnimator: ValueAnimator? = null
    private var mStaticAnimationProgress = -1f

    var shimmer: Shimmer? = null
        set(shimmer) {
            field = shimmer
            if (shimmer != null) {
                mShimmerPaint.xfermode = PorterDuffXfermode(
                    if (shimmer.alphaShimmer) PorterDuff.Mode.DST_IN else PorterDuff.Mode.SRC_IN
                )
            }
            updateShader()
            updateValueAnimator()
            invalidateSelf()
        }

    /** Starts the shimmer animation.  */
    fun startShimmer() {
        if (!isShimmerStarted && callback != null) {
            mValueAnimator?.start()
        }
    }

    /** Stops the shimmer animation.  */
    fun stopShimmer() {
        if (isShimmerStarted) {
            mValueAnimator?.cancel()
        }
    }

    /** Return whether the shimmer animation has been started.  */
    val isShimmerStarted: Boolean
        get() = mValueAnimator?.isStarted == true

    /** Return whether the shimmer animation is running.  */
    val isShimmerRunning: Boolean
        get() = mValueAnimator?.isRunning == true

    public override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        mDrawRect.set(bounds)
        updateShader()
        maybeStartShimmer()
    }

    fun setStaticAnimationProgress(value: Float) {
        if (value.compareTo(mStaticAnimationProgress) == 0 || value < 0f && mStaticAnimationProgress < 0f) {
            return
        }
        mStaticAnimationProgress = min(value, 1f)
        invalidateSelf()
    }

    fun clearStaticAnimationProgress() {
        setStaticAnimationProgress(-1f)
    }

    override fun draw(canvas: Canvas) {
        if (shimmer == null || mShimmerPaint.shader == null) {
            return
        }
        val tiltTan = tan(toRadians(shimmer!!.tilt.toDouble())).toFloat()
        val translateHeight = mDrawRect.height() + tiltTan * mDrawRect.width()
        val translateWidth = mDrawRect.width() + tiltTan * mDrawRect.height()
        val animatedValue: Float = if (mStaticAnimationProgress < 0f) {
            (mValueAnimator?.animatedValue as? Float) ?: 0f
        } else {
            mStaticAnimationProgress
        }
        val dxy: Pair<Float, Float> = when (shimmer!!.direction) {
            Shimmer.LEFT_TO_RIGHT -> {
                offset(-translateWidth, translateWidth, animatedValue) to 0f
            }
            Shimmer.RIGHT_TO_LEFT -> {
                offset(translateWidth, -translateWidth, animatedValue) to 0f
            }
            Shimmer.TOP_TO_BOTTOM -> {
                0f to offset(-translateHeight, translateHeight, animatedValue)
            }
            Shimmer.BOTTOM_TO_TOP -> {
                0f to offset(translateHeight, -translateHeight, animatedValue)
            }
            else -> {
                offset(-translateWidth, translateWidth, animatedValue) to 0f
            }
        }
        mShaderMatrix.reset()
        mShaderMatrix.setRotate(shimmer!!.tilt, mDrawRect.width() / 2f, mDrawRect.height() / 2f)
        mShaderMatrix.preTranslate(dxy.first, dxy.second)
        mShimmerPaint.shader.setLocalMatrix(mShaderMatrix)
        canvas.drawRect(mDrawRect, mShimmerPaint)
    }

    override fun setAlpha(alpha: Int) {
        // No-op, modify the Shimmer object you pass in instead
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        // No-op, modify the Shimmer object you pass in instead
    }

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "if (shimmer != null && (shimmer!!.clipToChildren || shimmer!!.alphaShimmer)) PixelFormat.TRANSLUCENT else PixelFormat.OPAQUE",
            "android.graphics.PixelFormat",
            "android.graphics.PixelFormat"
        )
    )
    override fun getOpacity(): Int {
        return if (shimmer != null && (shimmer!!.clipToChildren || shimmer!!.alphaShimmer)) PixelFormat.TRANSLUCENT else PixelFormat.OPAQUE
    }

    private fun offset(start: Float, end: Float, percent: Float): Float {
        return start + (end - start) * percent
    }

    private fun updateValueAnimator() {
        if (shimmer == null) {
            return
        }
        val started: Boolean
        if (mValueAnimator != null) {
            started = mValueAnimator!!.isStarted
            mValueAnimator!!.cancel()
            mValueAnimator!!.removeAllUpdateListeners()
        } else {
            started = false
        }
        mValueAnimator = ValueAnimator.ofFloat(0f, 1f + (shimmer!!.repeatDelay / shimmer!!.animationDuration).toFloat())
        mValueAnimator?.interpolator = LinearInterpolator()
        mValueAnimator?.repeatMode = shimmer!!.repeatMode
        mValueAnimator?.startDelay = shimmer!!.startDelay
        mValueAnimator?.repeatCount = shimmer!!.repeatCount
        mValueAnimator?.duration = shimmer!!.animationDuration + shimmer!!.repeatDelay
        mValueAnimator?.addUpdateListener(mUpdateListener)
        if (started) {
            mValueAnimator?.start()
        }
    }

    fun maybeStartShimmer() {
        if (mValueAnimator != null && !mValueAnimator!!.isStarted
            && shimmer != null && shimmer!!.autoStart
            && callback != null
        ) {
            mValueAnimator!!.start()
        }
    }

    private fun updateShader() {
        val bounds = bounds
        val boundsWidth = bounds.width()
        val boundsHeight = bounds.height()
        if (boundsWidth == 0 || boundsHeight == 0 || shimmer == null) {
            return
        }
        val width = shimmer!!.width(boundsWidth)
        val height = shimmer!!.height(boundsHeight)
        val shader: Shader
        when (shimmer!!.shape) {
            Shimmer.LINEAR -> {
                val vertical = (shimmer!!.direction == Shimmer.TOP_TO_BOTTOM
                        || shimmer!!.direction == Shimmer.BOTTOM_TO_TOP)
                val endX = if (vertical) 0 else width
                val endY = if (vertical) height else 0
                shader = LinearGradient(
                    0F, 0F, endX.toFloat(), endY.toFloat(), shimmer!!.colors, shimmer!!.positions, Shader.TileMode.CLAMP
                )
            }
            Shimmer.RADIAL -> shader = RadialGradient(
                width / 2f,
                height / 2f, (max(width, height) / sqrt(2.0)).toFloat(),
                shimmer!!.colors,
                shimmer!!.positions,
                Shader.TileMode.CLAMP
            )
            else -> {
                val vertical = (shimmer!!.direction == Shimmer.TOP_TO_BOTTOM
                        || shimmer!!.direction == Shimmer.BOTTOM_TO_TOP)
                val endX = if (vertical) 0 else width
                val endY = if (vertical) height else 0
                shader = LinearGradient(
                    0F, 0F, endX.toFloat(), endY.toFloat(), shimmer!!.colors, shimmer!!.positions, Shader.TileMode.CLAMP
                )
            }
        }
        mShimmerPaint.shader = shader
    }
}