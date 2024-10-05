package com.ijcsj.ui_library.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText
import com.ijcsj.ui_library.R
import com.ijcsj.ui_library.anko.dp
import com.ijcsj.ui_library.utils.ContextExt.color
import com.ijcsj.ui_library.utils.ContextExt.drawable


/**
 * https://www.cnblogs.com/chenrui7/p/10830735.html
 */
/**
 * 验证码输入框
 */
class VerificationEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    companion object {
        const val DEFAULT_CODE_LENGTH = 4
        const val DEFAULT_CODE_MARGIN = 20F
        const val DEFAULT_CODE_WIDTH = 150
        const val DEFAULT_CODE_HEIGHT = 150
        const val BLINK = 500L
    }

    /**
     *验证码个数
     */
    private var mCodeLength = DEFAULT_CODE_LENGTH


    /**
     * 验证码之间的间隔
     */
    private var mCodeMargin = DEFAULT_CODE_MARGIN.dp

    /**
     * 验证码背景
     */
    private var mCodeBackground: Drawable? = null

    /**
     * 验证码宽度
     */
    private var mCodeWidth = DEFAULT_CODE_WIDTH.dp

    /**
     * 验证码高度
     */
    private var mCodeHeight = DEFAULT_CODE_HEIGHT

    /**
     * 光标相关
     */
    private var mCursorColor = context.color(R.color.main_color)
    private var mCursorWidth: Float = 1.5F.dp
    private var mBlink: Blink? = null
    private var mCursorVisible = true
    private var mCursorFlag = false

    /**
     * 输入完成监听
     */
    private var inputTextListener: OnInputTextListener? = null

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.VerificationEditText)
        for (i in 0 until typedArray.indexCount) {
            when (val index = typedArray.getIndex(i)) {
                R.styleable.VerificationEditText_codeLength -> {
                    mCodeLength = typedArray.getInteger(index, DEFAULT_CODE_LENGTH)
                }
                R.styleable.VerificationEditText_codeBackground -> {
                    mCodeBackground = typedArray.getDrawable(index)
                }
                R.styleable.VerificationEditText_codeMargin -> {
                    mCodeMargin = typedArray.getDimension(index, mCodeMargin)
                }
                R.styleable.VerificationEditText_codeWidth -> {
                    mCodeWidth = typedArray.getDimensionPixelSize(index, mCodeWidth)
                }
                R.styleable.VerificationEditText_codeCursorVisible -> {
                    mCursorVisible = typedArray.getBoolean(index, mCursorVisible)
                }
                R.styleable.VerificationEditText_codeCursorColor -> {
                    mCursorColor = typedArray.getColor(index, mCursorColor)
                }
                R.styleable.VerificationEditText_codeCursorWidth -> {
                    mCursorWidth = typedArray.getDimension(index, mCursorWidth)
                }
            }
        }
        typedArray.recycle()

        if (mCodeBackground == null) {
            mCodeBackground = context.drawable(R.drawable.bg_code_edit_line)
        }
        if (mCodeLength <= 0) {
            throw IllegalArgumentException("code length must large than 0!!!")
        }

        /**
         * 禁用长按事件
         */
        isLongClickable = false
        /**
         * 隐藏EditText自带光标，防止onDraw方法一直被调用
         */
        isCursorVisible = false
        /**
         * 设置输入框最大长度
         */
        setMaxLength(mCodeLength)
        /**
         * 设置背景为透明
         */
        background = null
        inputType = InputType.TYPE_CLASS_NUMBER
        imeOptions = EditorInfo.IME_ACTION_DONE
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onTextContextMenuItem(id: Int): Boolean {
        return false
    }

    /**
     *设置输入框最大长度
     */
    private fun setMaxLength(maxLength: Int) {
        if (maxLength >= 0) {
            filters = arrayOf<InputFilter>(LengthFilter(maxLength))
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        if (widthMode == MeasureSpec.AT_MOST) {
            mCodeHeight = mCodeWidth
            val newWidth = mCodeWidth * mCodeLength + (mCodeLength - 1) * mCodeMargin
            setMeasuredDimension(
                MeasureSpec.makeMeasureSpec(newWidth.toInt(), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(mCodeHeight, MeasureSpec.EXACTLY)
            )
        } else {
            mCodeWidth = ((widthSize - mCodeMargin * (mCodeLength - 1)) / mCodeLength).toInt()
            mCodeHeight = mCodeWidth
            setMeasuredDimension(
                widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(mCodeHeight, MeasureSpec.EXACTLY)
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        drawBackground(canvas)
        drawText(canvas)
        drawCursor(canvas)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        resumeBlink()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        suspendBlink()
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        text?.apply {
            if (length >= mCodeLength) {
                suspendBlink()
                inputTextListener?.onInputTextComplete(this)
            } else if (length + 1 == mCodeLength && lengthBefore == 1) {
                resumeBlink()
            }
        }
    }

    //禁止移动光标
    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        val text: CharSequence? = text
        if (text != null) {
            if (selStart != text.length || selEnd != text.length) {
                setSelection(text.length, text.length)
                return
            }
        }
        super.onSelectionChanged(selStart, selEnd)
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus) {
            mBlink?.uncancel()
            makeBlink()
        } else {
            mBlink?.cancel()
        }
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (focused) {
            makeBlink()
        }
    }

    /**
     * 绘制背景
     */
    private fun drawBackground(canvas: Canvas) {
        mCodeBackground?.run {
            val currentIndex = 0.coerceAtLeast(editableText.length)
            val count = canvas.save()
            for (i in 0 until mCodeLength) {
                bounds = Rect(0, 0, mCodeWidth, mCodeHeight)
                state = if (currentIndex == i) {
                    intArrayOf(android.R.attr.state_selected)
                } else {
                    intArrayOf(android.R.attr.state_enabled)
                }
                draw(canvas)
                canvas.translate(mCodeWidth + mCodeMargin, 0F)
            }
            canvas.restoreToCount(count)
        }
    }

    /**
     * 绘制文本
     */
    private fun drawText(canvas: Canvas) {
        val count = canvas.save()
        canvas.translate(0f, 0f)
        paint.color = currentTextColor
        for (i in editableText.indices) {
            val textWidth = paint.measureText(editableText[i].toString())
            val fontMetrics = Paint.FontMetrics()
            paint.getFontMetrics(fontMetrics)
            val x = (mCodeWidth + mCodeMargin) * i + mCodeWidth / 2f - textWidth / 2f
            val y = mCodeHeight / 2f - (fontMetrics.top + fontMetrics.bottom) / 2f
            canvas.drawText(editableText[i].toString(), x, y, paint)
        }
        canvas.restoreToCount(count)
    }

    /**
     * 绘制光标
     */
    private fun drawCursor(canvas: Canvas) {
        if (!mCursorVisible) return
        paint.style = Paint.Style.FILL
        paint.color = mCursorColor
        paint.strokeWidth = mCursorWidth
        val cursorPosition: Int = editableText.length
        mCursorFlag = !mCursorFlag
        if (mCursorFlag && hasFocus()) {
            val count = canvas.save()
            canvas.translate(
                (mCodeWidth + mCodeMargin) * cursorPosition + mCodeWidth / 2,
                mCodeWidth.toFloat() / 4
            )
            canvas.drawLine(0F, 0F, 0F, mCodeWidth.toFloat() / 2, paint)
            canvas.restoreToCount(count)
        }
    }

    private fun suspendBlink() {
        mBlink?.cancel()
    }

    private fun resumeBlink() {
        if (mBlink != null) {
            mBlink?.uncancel()
            makeBlink()
        }
    }

    private fun makeBlink() {
        if (shouldBlink()) {
            if (mBlink == null) mBlink = Blink()
            removeCallbacks(mBlink)
            postDelayed(mBlink, BLINK)
        } else {
            if (mBlink != null) removeCallbacks(mBlink)
        }
    }

    private fun shouldBlink(): Boolean {
        if (!mCursorVisible || !isFocused) return false
        val start: Int = selectionStart
        if (start < 0) return false
        val end: Int = selectionEnd
        return if (end < 0) false else start == end
    }

    inner class Blink : Runnable {
        private var mCancelled = false
        override fun run() {
            if (mCancelled) {
                return
            }
            removeCallbacks(this)
            if (shouldBlink()) {
                if (layout != null) {
                    invalidate()
                }
                postDelayed(this, BLINK)
            }
        }

        fun cancel() {
            if (!mCancelled) {
                removeCallbacks(this)
                mCancelled = true
            }
        }

        fun uncancel() {
            mCancelled = false
        }
    }

    fun setOnInputTextListener(listener: OnInputTextListener) {
        this.inputTextListener = listener
    }

    fun interface OnInputTextListener {
        fun onInputTextComplete(text: CharSequence)
    }
}