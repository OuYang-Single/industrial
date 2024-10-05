package com.cn.setuplibrary.popup

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.cn.setuplibrary.adapter.StyleAdapter
import com.ijcsj.stUplibrary.R
import com.ijcsj.ui_library.utils.ScreenUtils
import com.lxj.xpopup.core.CenterPopupView


class StylePopup(context: Context, var list: MutableList<String>, var styleAdapter: StyleAdapter): CenterPopupView(context) {
   var imgClose:ImageView?=null
   var recyclerView:RecyclerView?=null
    override fun getImplLayoutId(): Int {

        return R.layout.style_popup
    }

    override fun onCreate() {
        super.onCreate()
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        imgClose = findViewById<ImageView>(R.id.img_close)
        recyclerView?.adapter = styleAdapter
        styleAdapter.refresh(list)
        imgClose?.setOnClickListener {
            dismiss()
            hideBottomNavigationBar((it.context as Activity).window)
        }
    }

    override fun onDismiss() {
        super.onDismiss()
        hideBottomNavigationBar((context as Activity).window)
    }
    override fun  onBackPressed( ): Boolean {
          return true;
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)

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

    override fun getMaxHeight(): Int {
        return ScreenUtils.getScreenPixelSize(context)[1]- ScreenUtils.getScreenPixelSize(context)[1]/5.toInt()
    }
    override fun getMaxWidth(): Int {
        return ScreenUtils.getScreenPixelSize(context)[0]/2.toInt()
    }
}