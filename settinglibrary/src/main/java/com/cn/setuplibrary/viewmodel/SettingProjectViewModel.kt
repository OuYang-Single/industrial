package com.cn.setuplibrary.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cn.setuplibrary.model.SettingProjectModel
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel

class SettingProjectViewModel (override val model: SettingProjectModel) : MvmBaseViewModel<IBaseView, SettingProjectModel>(){

   public val projectBaseList: ArrayList<String> = ArrayList()
    public val fragmentList: ArrayList<Fragment>   = ArrayList()

    public override fun initModel() {
        projectBaseList.add("工程参数设置")
        projectBaseList.add("修改工程密码")
        fragmentList.addAll(model.initDataFragment())
    }
}