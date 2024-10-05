package com.cn.setuplibrary.viewmodel

import androidx.fragment.app.Fragment
import com.cn.setuplibrary.model.SettingFactoryModel
import com.cn.setuplibrary.model.SettingUserModel
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel

class SettingFactoryViewModel  (override val model: SettingFactoryModel) : MvmBaseViewModel<IBaseView, SettingFactoryModel>(){
    public val projectBaseList: ArrayList<String> = ArrayList()
    public val fragmentList: ArrayList<Fragment>   = ArrayList()
    public override fun initModel() {
        projectBaseList.add("厂家参数设置")
        projectBaseList.add("厂家调试")
        projectBaseList.add("厂家升级")
        projectBaseList.add("操作日志")
        projectBaseList.add("修改密码")
        fragmentList.addAll(model.initDataFragment())
    }
}