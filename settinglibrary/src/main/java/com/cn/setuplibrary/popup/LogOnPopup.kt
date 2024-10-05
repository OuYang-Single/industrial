package com.cn.setuplibrary.popup

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.cn.setuplibrary.adapter.StyleAdapter
import com.hjq.shape.view.ShapeTextView
import com.ijcsj.stUplibrary.R
import com.ijcsj.ui_library.utils.ScreenUtils
import com.lxj.xpopup.core.CenterPopupView


class LogOnPopup(context: Context,var RightClick: StyleAdapter.OnClickListeners): CenterPopupView(context) {
   var imgClose:ImageView?=null
   var logOn: ShapeTextView?=null
   var et_poople: EditText?=null
    override fun getImplLayoutId(): Int {
      /*  hideBottomNavigationBar(hostWindow)*/
        return R.layout.log_on_popup
    }

    override fun onCreate() {
        super.onCreate()
        hideBottomNavigationBar((context as Activity).window)
        logOn = findViewById<ShapeTextView>(R.id.log_on)
        imgClose = findViewById<ImageView>(R.id.img_close)
        et_poople = findViewById<EditText>(R.id.et_poople)

        imgClose?.setOnClickListener {
            dismiss()
            hideBottomNavigationBar((it.context as Activity).window)
        }
        logOn?.setOnClickListener {

            RightClick.onClick(et_poople!!.text.toString(),1)
            hideBottomNavigationBar((it.context as Activity).window)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        hideBottomNavigationBar((context as Activity).window)
    }

    fun hideBottomNavigationBar(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 在 Android 11（API 级别 30）及以上版本中，可以直接使用 Window 的 setDecorFitsSystemWindows 方法
            window.setDecorFitsSystemWindows(false)
        } else {
            // 在 Android 10（API 级别 29）中，可以尝试使用以下方法
            val decorView: View = window.getDecorView()
            val uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // 隐藏导航栏
                    or View.SYSTEM_UI_FLAG_IMMERSIVE // 全屏模式
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_FULLSCREEN) // 隐藏状态栏
            decorView.systemUiVisibility = uiOptions
        }
    }

    override fun getMaxWidth(): Int {
        return ScreenUtils.getScreenPixelSize(context)[0]/2.toInt()
    }
}