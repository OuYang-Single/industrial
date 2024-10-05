package com.cn.phoneapp.startup

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.ijcsj.ui_library.BuildConfig
import com.orhanobut.logger.Logger
import com.rousetime.android_startup.AndroidStartup

class ARouterStart : AndroidStartup<String>() {
    override fun create(context: Context): String? {

        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(context as Application?) // 尽可能早，推荐在Application中初始化
        Logger.w("ARouterStart");
        return ARouterStart::class.java.simpleName
    }

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = true


    override fun dependenciesByName(): List<String>? {
        return arrayListOf("com.cn.phoneapp.startup.LogStart")
    }
}