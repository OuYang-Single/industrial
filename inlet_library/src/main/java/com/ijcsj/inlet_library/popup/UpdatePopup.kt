package com.ijcsj.inlet_library.popup

import android.content.Context
import android.widget.ProgressBar
import android.widget.TextView
import com.hjq.shape.view.ShapeTextView
import com.ijcsj.inlet_library.R
import com.lxj.xpopup.core.CenterPopupView

class UpdatePopup (context: Context, var RightClick: OnClickListener): CenterPopupView(context) {
    var tv_left: ShapeTextView? = null
    var tv_right: ShapeTextView? = null
    var tv_cancel: ShapeTextView? = null
    var tv_title: TextView? = null
    var tv_title_s: TextView? = null
    var progress: ProgressBar? = null

    override fun getImplLayoutId(): Int {
        return R.layout.app_update_popup
    }

    override fun onCreate() {
        super.onCreate()

        tv_title = findViewById<TextView>(R.id.tv_title)
        tv_title_s = findViewById<TextView>(R.id.tv_title_s)
        progress = findViewById<ProgressBar>(R.id.progress)
        tv_left = findViewById<ShapeTextView>(R.id.tv_left)
        tv_right = findViewById<ShapeTextView>(R.id.tv_right)
        tv_left?.setOnClickListener(OnClickListener { dismiss() })
        tv_right?.setOnClickListener(OnClickListener {
         //   tv_left.set
            tv_right?.visibility= INVISIBLE
            tv_left?.visibility= INVISIBLE
            tv_title?.visibility= GONE
            progress?.visibility= VISIBLE
            tv_title_s?.visibility= VISIBLE
            RightClick.onClick(it)

        })
    }



}