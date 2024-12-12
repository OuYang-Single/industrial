package com.cn.main_library.model

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.cn.main_library.R
import com.cn.main_library.api.ApiRepository
import com.cn.main_library.base.ProjectBase
import com.cn.main_library.base.ProjectsBase
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.common_library.util.Hexs
import com.lxj.xpopup.core.BasePopupView

class MainVideoModel  (private val repository: ApiRepository) : BaseModel() {

    fun initData(): ObservableList<ProjectBase> {

 /*       dbString.add(ProjectsBase("进水", R.mipmap.ic_in_iet))
        dbString.add(ProjectsBase("排气", R.mipmap.ic_exhaust))
        dbString.add(ProjectsBase("排水", R.mipmap.ic_drainage))*/
        val projectBaseList: ObservableList<ProjectBase> = ObservableArrayList()
        var projectBase1=ProjectBase("出煤温度","--","℃")
        var projectBase2=ProjectBase("进水压力","--","Bar")
        var projectBase3=ProjectBase("功率","--","kW")
        var projectBase4=ProjectBase("比率","--","%")
        var projectBase5=ProjectBase("出煤压力","--","Bar")
        projectBaseList.add(projectBase1)
        projectBaseList.add(projectBase2)
        projectBaseList.add(projectBase3)
        projectBaseList.add(projectBase4)
        projectBaseList.add(projectBase5)
        return projectBaseList
    }

    fun initPhoneData(): ObservableList<ProjectBase> {
        val projectBaseList: ObservableList<ProjectBase> = ObservableArrayList()
        var projectBase1=ProjectBase("流量","--","L/min")
        var projectBase2=ProjectBase("比率","--","%")
        var projectBase3=ProjectBase("功率","--","kW")
        var projectBase4=ProjectBase("出煤温度","--","℃")
        var projectBase5=ProjectBase("回煤温度","--","℃")
        var projectBase6=ProjectBase("设定温度","--","℃")
        var projectBase7=ProjectBase("进水压力","--","bar")
        var projectBase8=ProjectBase("出煤压力","--","bar")
        var projectBase9=ProjectBase("回煤压力","--","bar")
        projectBaseList.add(projectBase1)
        projectBaseList.add(projectBase2)
        projectBaseList.add(projectBase3)
        projectBaseList.add(projectBase4)
        projectBaseList.add(projectBase5)
        projectBaseList.add(projectBase6)
        projectBaseList.add(projectBase7)
        projectBaseList.add(projectBase8)
        projectBaseList.add(projectBase9)
        return projectBaseList
    }

    fun setCan102Data(canFrame: Byte,dbString:ObservableList<ProjectsBase>):ObservableList<ProjectsBase> {

        val daata: ObservableList<ProjectsBase> = ObservableArrayList()
        for (i in 0 until dbString.size) {
            if (Hexs.getBitByByte( canFrame,i)==1){
                daata.add(dbString[i])
            }
        }
       /* var data2=    Hexs.getBitByByte( canFrame.data[0],1)==1
        var data3=   Hexs.getBitByByte( canFrame.data[0],2)==1
        var data4=    Hexs.getBitByByte( canFrame.data[0],3)==1
        var data5=    Hexs.getBitByByte( canFrame.data[0],4)==1
        var data6=    Hexs.getBitByByte( canFrame.data[0],5)==1
        var data7=    Hexs.getBitByByte( canFrame.data[0],5)==1
        daata.add(data1)
        daata.add(data1)*/
       return daata;
    }

}