package com.ijcsj.ui_library.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


object ContextExt {

    @JvmStatic
    fun Context.width() = resources.displayMetrics.widthPixels

    @JvmStatic
    fun Context.height() = resources.displayMetrics.heightPixels

    /** 获取状态栏高度*/
    @JvmStatic
    fun Context.statusBarHeight() =
        resources.getIdentifier("status_bar_height", "dimen", "android").takeIf { it > 0 }
            ?.let { resources.getDimensionPixelSize(it) } ?: 0

    /** 获取竖屏下状态栏高度*/
    @JvmStatic
    fun Context.statusBarHeightPortrait() =
        resources.getIdentifier("status_bar_height_portrait", "dimen", "android").takeIf { it > 0 }
            ?.let { resources.getDimensionPixelSize(it) } ?: 0

    @JvmStatic
    fun Context.navBarHeight(): Int =
        resources.getIdentifier("navigation_bar_height", "dimen", "android").takeIf { it != 0 }
            ?.let { resources.getDimensionPixelSize(it) } ?: 0

    @JvmStatic
    fun Context.drawable(@DrawableRes res: Int) = ContextCompat.getDrawable(this, res)

    @JvmStatic
    fun Context.color(@ColorRes res: Int) = ContextCompat.getColor(this, res)

    @JvmStatic
    fun Intent.load(vararg params: Pair<String, Any?>) {
        params.forEach { item ->
            when (item.second) {
                is Int -> putExtra(item.first, item.second as Int)
                is Long -> putExtra(item.first, item.second as Long)
                is Float -> putExtra(item.first, item.second as Float)
                is Boolean -> putExtra(item.first, item.second as Boolean)
                is String -> putExtra(item.first, item.second as String)
                is Parcelable -> putExtra(item.first, item.second as Parcelable)
                is IntArray -> putExtra(item.first, item.second as IntArray)
                is LongArray -> putExtra(item.first, item.second as LongArray)
                is FloatArray -> putExtra(item.first, item.second as FloatArray)
                is BooleanArray -> putExtra(item.first, item.second as BooleanArray)
            }
        }
    }

    @JvmStatic
    inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) {
        val intent = Intent(this, T::class.java)
        intent.load(*params)
        startActivity(intent)
    }

    @JvmStatic
    inline fun <reified T : Activity> Activity.startActivity(vararg params: Pair<String, Any?>) {
        val intent = Intent(this, T::class.java)
        intent.load(*params)
        startActivity(intent)
    }

    @JvmStatic
    inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any?>) {
        val intent = Intent(context, T::class.java)
        intent.load(*params)
        startActivity(intent)
    }

    @JvmStatic
    fun Context.showSoftInput(target: EditText?) {
        target?.post {
            target.isFocusable = true
            target.isFocusableInTouchMode = true
            target.requestFocus()
            val imm =
                getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.showSoftInput(target, InputMethodManager.RESULT_UNCHANGED_SHOWN)
        }
    }

    @JvmStatic
    fun Context.hideSoftInput(target: View?) {
        target?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(target.windowToken, 0)
        }
    }
}