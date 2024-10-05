package com.cn.industrial.startup

import android.content.Context
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.rousetime.android_startup.AndroidStartup

class ShuJuMMkVStart: AndroidStartup<String>() {
    override fun create(context: Context): String? {
        ShuJuMMkV.getInstances()?.initialize(context)
        return ShuJuMMkVStart::class.java.simpleName
    }

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = true

    override fun dependenciesByName(): List<String> {
        return arrayListOf("com.cn.industrial.startup.LogStart")
    }
}