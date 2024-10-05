package com.cn.datalibrary.model

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.cn.datalibrary.api.ApiRepository
import com.cn.datalibrary.fragment.DataFragment
import com.cn.datalibrary.fragment.FormFragment
import com.cn.datalibrary.fragment.HistoryFragment
import com.cn.datalibrary.fragment.InputFragment
import com.cn.datalibrary.fragment.OutputFragment
import com.cn.datalibrary.fragment.VersionFragment
import com.ijcsj.common_library.bean.DataTitle
import com.ijcsj.common_library.model.BaseModel

class BataMainVideoModel  (private val repository: ApiRepository) : BaseModel() {

    fun initData(): ObservableList<DataTitle> {
        val projectBaseList: ObservableList<DataTitle> = ObservableArrayList()
        var projectBase1=DataTitle("数据",true)
        var projectBase2=DataTitle("输出",false)
        var projectBase3=DataTitle("输入",false)
        var projectBase4=DataTitle("历史故障查询",false)
        var projectBase5=DataTitle("温度曲线",false)
        var projectBase6=DataTitle("版本号",false)
        projectBaseList.add(projectBase1)
        projectBaseList.add(projectBase2)
        projectBaseList.add(projectBase3)
        projectBaseList.add(projectBase4)
        projectBaseList.add(projectBase5)
        projectBaseList.add(projectBase6)
        return projectBaseList
    }
    fun initDataFragment(): ArrayList<Fragment> {
        val projectBaseList: ArrayList<Fragment> = ArrayList()
        projectBaseList.add(DataFragment())
        projectBaseList.add(OutputFragment())
        projectBaseList.add(InputFragment())
        projectBaseList.add(HistoryFragment())
        projectBaseList.add(FormFragment())
        projectBaseList.add(VersionFragment())
        return projectBaseList
    }
}