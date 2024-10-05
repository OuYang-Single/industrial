package com.cn.setuplibrary.model

import androidx.fragment.app.Fragment
import com.cn.setuplibrary.api.ApiRepository
import com.cn.setuplibrary.fragment.ChangeProjectPasswordFragment
import com.cn.setuplibrary.fragment.ChangeProjectPasswordFragments
import com.cn.setuplibrary.fragment.EngineeringParametersFragment
import com.cn.setuplibrary.fragment.FactoryChangesPasswordFragment
import com.cn.setuplibrary.fragment.ManufacturerDebuggingFragment
import com.cn.setuplibrary.fragment.ManufacturerParametersFragment
import com.cn.setuplibrary.fragment.ManufacturerUpgradeFragment
import com.cn.setuplibrary.fragment.OperationLogFragment
import com.ijcsj.common_library.model.BaseModel

class SettingFactoryModel (private val repository: ApiRepository) : BaseModel() {
    fun initDataFragment(): ArrayList<Fragment> {
        val projectBaseList: ArrayList<Fragment> = ArrayList()
        projectBaseList.add(ManufacturerParametersFragment())
        projectBaseList.add(ManufacturerDebuggingFragment())
        projectBaseList.add(ManufacturerUpgradeFragment())
        projectBaseList.add(OperationLogFragment())
        projectBaseList.add(ChangeProjectPasswordFragments())
        return projectBaseList
    }
}