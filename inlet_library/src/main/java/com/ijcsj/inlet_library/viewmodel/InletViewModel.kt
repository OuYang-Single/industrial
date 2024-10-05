package com.ijcsj.inlet_library.viewmodel

import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.Constant
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.inlet_library.model.InletModel
import com.ijcsj.service_library.login.LoginServiceProvider
import com.ijcsj.ui_library.utils.AppGlobals


class InletViewModel (override val model: InletModel) : MvmBaseViewModel<IBaseView, InletModel>() {
    private val _introduceDataList = MutableLiveData<List<Int>>()
    val introduceDataList: LiveData<List<Int>> get() = _introduceDataList

    public override fun initModel() {
        AppGlobals.get()?.let {
         if ("com.cn.phoneapp"== it.packageName)  {
             ARouter.getInstance().build(Constant.ACTIVITY_PHONE_MAIN_PATH).navigation()
         }else{
             ARouter.getInstance().build(Constant.ACTIVITY_MAIN_PATH).navigation()
         }
        }

    }

    public fun introduceData(){
        _introduceDataList.postValue(model.getIntroduceData())

    }

    var onSkipClick=  BindingCommand<BindingAction>{
        ARouter.getInstance().build(Constant.ACTIVITY_LOGIN_PATH).navigation()
        getPageView()?.closure(null)
    }

}