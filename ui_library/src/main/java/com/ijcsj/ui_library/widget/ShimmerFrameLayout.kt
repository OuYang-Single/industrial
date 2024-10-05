package com.ijcsj.ui_library.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.ijcsj.ui_library.R
import com.ijcsj.ui_library.widget.Shimmer
import com.ijcsj.ui_library.widget.ShimmerDrawable


/**
 * Shimmer is an Android library that provides an easy way to add a shimmer effect to any [ ]. It is useful as an unobtrusive loading indicator, and was originally
 * developed for Facebook Home.
 *
 *
 * Find more examples and usage instructions over at: facebook.github.io/shimmer-android
 */
class ShimmerFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val mContentPaint = Paint()
    private val mShimmerDrawable: ShimmerDrawable = ShimmerDrawable()

    /** Return whether the shimmer drawable is visible.  */
    private var isShimmerVisible = true
    private var mStoppedShimmerBecauseVisibility = false
    private var shimmer: Shimmer?
        get() = mShimmerDrawable.shimmer
        set(value) {
            mShimmerDrawable.shimmer = value
            if (value != null && value.clipToChildren) {
                setLayerType(LAYER_TYPE_HARDWARE, mContentPaint)
            } else {
                setLayerType(LAYER_TYPE_NONE, null)
            }
        }

    init {
        setWillNotDraw(false)
        mShimmerDrawable.callback = this
        shimmer = if (attrs == null) {
            Shimmer.AlphaHighlightBuilder().build()
        } else {
            val a = context.obtainStyledAttributes(attrs, R.styleable.ShimmerFrameLayout, 0, 0)
            try {
                val shimmerBuilder: Shimmer.Builder<*> = if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_colored)
                    && a.getBoolean(R.styleable.ShimmerFrameLayout_shimmer_colored, false)
                ) Shimmer.ColorHighlightBuilder() else Shimmer.AlphaHighlightBuilder()
                shimmerBuilder.consumeAttributes(a).build()
            } finally {
                a.recycle()
            }
        }
    }

    /** Starts the shimmer animation.  */
    fun startShimmer() {
        if (isAttachedToWindow) {
            mShimmerDrawable.startShimmer()
        }
    }

    /** Stops the shimmer animation.  */
    fun stopShimmer() {
        mStoppedShimmerBecauseVisibility = false
        mShimmerDrawable.stopShimmer()
    }

    /** Return whether the shimmer animation has been started.  */
    val isShimmerStarted: Boolean
        get() = mShimmerDrawable.isShimmerStarted

    /**
     * Sets the ShimmerDrawable to be visible.
     *
     * @param startShimmer Whether to start the shimmer again.
     */
    fun showShimmer(startShimmer: Boolean) {
        isShimmerVisible = true
        if (startShimmer) {
            startShimmer()
        }
        invalidate()
    }

    /** Sets the ShimmerDrawable to be invisible, stopping it in the process.  */
    fun hideShimmer() {
        stopShimmer()
        isShimmerVisible = false
        invalidate()
    }

    val isShimmerRunning: Boolean
        get() = mShimmerDrawable.isShimmerRunning

    public override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mShimmerDrawable.setBounds(0, 0, width, height)
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        // View's constructor directly invokes this method, in which case no fields on
        // this class have been fully initialized yet.
        if (visibility != VISIBLE) {
            // GONE or INVISIBLE
            if (isShimmerStarted) {
                stopShimmer()
                mStoppedShimmerBecauseVisibility = true
            }
        } else if (mStoppedShimmerBecauseVisibility) {
            mShimmerDrawable.maybeStartShimmer()
            mStoppedShimmerBecauseVisibility = false
        }
    }

    public override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mShimmerDrawable.maybeStartShimmer()
    }

    public override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopShimmer()
    }

    public override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        if (isShimmerVisible) {
            mShimmerDrawable.draw(canvas)
        }
    }

    override fun verifyDrawable(who: Drawable): Boolean {
        return super.verifyDrawable(who) || who === mShimmerDrawable
    }

    fun setStaticAnimationProgress(value: Float) {
        mShimmerDrawable.setStaticAnimationProgress(value)
    }

    fun clearStaticAnimationProgress() {
        mShimmerDrawable.clearStaticAnimationProgress()
    }
}
