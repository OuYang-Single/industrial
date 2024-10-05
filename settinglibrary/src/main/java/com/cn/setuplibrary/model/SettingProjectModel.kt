package com.cn.setuplibrary.model

import androidx.fragment.app.Fragment
import com.cn.setuplibrary.api.ApiRepository
import com.cn.setuplibrary.fragment.ChangeProjectPasswordFragment
import com.cn.setuplibrary.fragment.EngineeringParametersFragment
import com.cn.setuplibrary.fragment.SettingFactoryFragment
import com.cn.setuplibrary.fragment.SettingProjectFragment
import com.cn.setuplibrary.fragment.SettingUserFragment
import com.ijcsj.common_library.model.BaseModel

class SettingProjectModel (private val repository: ApiRepository) : BaseModel() {
    fun initDataFragment(): ArrayList<Fragment> {
        val projectBaseList: ArrayList<Fragment> = ArrayList()
        projectBaseList.add(EngineeringParametersFragment())
        projectBaseList.add(ChangeProjectPasswordFragment())
        return projectBaseList
    }
}