package com.ijcsj.mylibrary.popup

import android.content.Context
import com.hjq.shape.view.ShapeTextView
import com.ijcsj.my_library.R
import com.lxj.xpopup.core.CenterPopupView

class LogOffPopup (context: Context ,var RightClick:  OnClickListener): CenterPopupView(context) {
    var tv_left: ShapeTextView? = null
    var tv_right: ShapeTextView? = null
    var tv_cancel: ShapeTextView? = null

    override fun getImplLayoutId(): Int {
        return R.layout.log_off_popup
    }

    override fun onCreate() {
        super.onCreate()
        tv_left = findViewById<ShapeTextView>(R.id.tv_left)
        tv_right = findViewById<ShapeTextView>(R.id.tv_right)
        tv_left?.setOnClickListener(OnClickListener { dismiss() })
        tv_right?.setOnClickListener(OnClickListener { dismiss()
            RightClick.onClick(it)
        })
    }



}