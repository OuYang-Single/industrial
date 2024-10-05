package com.cn.industrial.startup

import android.content.Context
import com.amap.api.location.AMapLocationClient
import com.cn.datalibrary.di.DataModule
import com.cn.main_library.di.MainModule
import com.cn.setuplibrary.di.SettingModule
import com.ijcsj.common_library.di.wangLuModule
import com.ijcsj.inlet_library.di.InletViewModel
import com.ijcsj.login_library.di.LoginModule
import com.ijcsj.mylibrary.di.MyModule
import com.ijcsj.web_library.di.WebVideoModule
import com.kongzue.dialogx.DialogX
import com.orhanobut.logger.Logger
import com.rousetime.android_startup.AndroidStartup
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class InitInStart : AndroidStartup<String>() {
    override fun create(context: Context): String? {
        Logger.w("InitInjectStartupProviderConfig");
        startKoin {
            androidLogger(Level.NONE)
            androidContext(context)
            fragmentFactory()
            modules(arrayOf(wangLuModule,InletViewModel,LoginModule,MyModule,WebVideoModule,MainModule,DataModule,SettingModule).toList())
        }
        //初始化
        DialogX.init(context);
        DialogX.autoRunOnUIThread = true;
        AMapLocationClient.updatePrivacyShow(context,true,true);
        AMapLocationClient.updatePrivacyAgree(context,true);
        //16:9

        return InitInStart::class.java.simpleName
    }

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = true

    override fun dependenciesByName(): List<String>? {
        return arrayListOf("com.cn.industrial.startup.LogStart")
    }
}