package com.ijcsj.web_library.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.LinearLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.ijcsj.common_library.ui.MvvmBaseActivity
import com.ijcsj.ui_library.utils.immersive
import com.ijcsj.ui_library.widget.CommonTitleBar
import com.ijcsj.web_library.R
import com.ijcsj.web_library.databinding.ActivityWebBinding
import com.ijcsj.web_library.viewmodel.WebViewModel
import me.jingbin.web.ByWebView
import me.jingbin.web.OnByWebClientCallback
import me.jingbin.web.OnTitleProgressCallback
import org.koin.androidx.viewmodel.ext.android.viewModel


@Route(path = "/h5/home")
class WebActivity : MvvmBaseActivity<ActivityWebBinding, WebViewModel>() {
    override val viewModel by viewModel<WebViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.activity_web
    var url: String? = null
    var title: String? = null
    var byWebView: ByWebView?=null;
    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }

    @SuppressLint("WrongConstant")
    override fun onAgainCreate(savedInstanceState: Bundle?) {
        immersive(darkMode = true)
        url = intent.getStringExtra("url")
        title = intent.getStringExtra("title")
        viewModel.initModel()
        title?.let {
            viewDataBinding?.topBarAc?.centerTextView?.text=title;
        }
        viewDataBinding?.let {
            byWebView = ByWebView
                .with(this)
                .useWebProgress(true) // 是否使用进度条，默认true，如使用可不用配置
                .setWebParent(it.callMeasure, LinearLayout.LayoutParams(-1, -1)) // 设置WebView父容器
                .useWebProgress("#ffb6cf", "#ff0000", 3) // 进度条渐变色(开始颜色，结束颜色，高度)
                .setOnTitleProgressCallback(object : OnTitleProgressCallback() {

                }) // title 和 progress 监听
                .setOnByWebClientCallback(object : OnByWebClientCallback(){


                }) // WebViewClient监听
                .loadUrl(url)
            // 启用返回键功能
            // 启用返回键功能
            byWebView?.webView?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK && byWebView?.webView?.canGoBack() == true) {
                    byWebView?.webView?.goBack() // 返回上一页
                    return@OnKeyListener true
                }
                false
            })
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK&&  byWebView?.webView?.canGoBack() == true) {
            byWebView?.webView?.goBack()
            return true
        }
          return super.dispatchKeyEvent(event)
    }
 override fun topBarAc(): CommonTitleBar? {
        return viewDataBinding?.topBarAc;
    }

}


