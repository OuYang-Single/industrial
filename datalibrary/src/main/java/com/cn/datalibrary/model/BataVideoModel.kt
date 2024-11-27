package com.cn.datalibrary.model

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.cn.datalibrary.api.ApiRepository
import com.google.gson.Gson
import com.ijcsj.common_library.bean.DataTitle
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.util.Hexs

class BataVideoModel  (private val repository: ApiRepository,var gson:Gson) : BaseModel() {

    fun initData(): ObservableList<DataTitle> {
        val projectBaseList: ObservableList<DataTitle> = ObservableArrayList()
        var projectBase1=DataTitle("L1 :","",false)
        var projectBase2=DataTitle("冷却器温度 :",false)
        var projectBase3=DataTitle("L2 :","",false)
        var projectBase4=DataTitle("进水压力 :",false)
        var projectBase5=DataTitle("L3 :",false)
        var projectBase6=DataTitle("回水压力 :",false)

        var projectBase8=DataTitle("出煤压力 :",false)

        var projectBase10=DataTitle("回煤压力 :",false)
        var projectBase11=DataTitle("出煤温度 :",false)
        var projectBase12=DataTitle("流量值 :",false)
        var projectBase13=DataTitle("回煤温度 :",false)
        projectBaseList.add(projectBase1)
        projectBaseList.add(projectBase2)
        projectBaseList.add(projectBase3)
        projectBaseList.add(projectBase4)
        projectBaseList.add(projectBase5)
        projectBaseList.add(projectBase6)

        projectBaseList.add(projectBase8)

        projectBaseList.add(projectBase10)
        projectBaseList.add(projectBase11)
        projectBaseList.add(projectBase12)
        projectBaseList.add(projectBase13)
        return projectBaseList
    }

    fun setCan099Data(it: CanFrame, list: ObservableList<DataTitle>) {
        var  datr1=gson.fromJson(gson.toJson(list[0]), DataTitle::class.java)
        var  datr2=gson.fromJson(gson.toJson(list[2]), DataTitle::class.java)
        var  datr4=gson.fromJson(gson.toJson(list[4]), DataTitle::class.java)
        datr1.value=(it.data[0].toFloat()/(10).toFloat()).toString()+" A"
        list[0]=datr1
        datr2.value=(it.data[1].toFloat()/(10).toFloat()).toString()+" A"
        list[2]=datr2
        datr4.value=(it.data[2].toFloat()/(10).toFloat()).toString()+" A"
        list[4]=datr4

        var  datr3=gson.fromJson(gson.toJson(list[3]), DataTitle::class.java)
        var  datr5=gson.fromJson(gson.toJson(list[5]), DataTitle::class.java)
        var  datr7=gson.fromJson(gson.toJson(list[6]), DataTitle::class.java)
        datr3.value=(it.data[5].toFloat()/(10).toFloat()).toString()+" bar"
        list[3]=datr3
        datr5.value=(it.data[6].toFloat()/(10).toFloat()).toString()+" bar"
        list[5]=datr5
        datr7.value=(it.data[7].toFloat()/(10).toFloat()).toString()+" bar"
        list[6]=datr7
    }

    fun setCan100Data(it: CanFrame, list: ObservableList<DataTitle>) {
        var  lsit1=gson.fromJson(gson.toJson(list[9]), DataTitle::class.java)
        var  lsit4=gson.fromJson(gson.toJson(list[8]), DataTitle::class.java)
        var  lsit3=gson.fromJson(gson.toJson(list[10]), DataTitle::class.java)
        var data1= ( it.data[0].toFloat()/(10).toFloat()).toInt()
        if (data1>=0){
            lsit1.value= "$data1 L/min"
            list[9]=lsit1
        }else{
            lsit1.value= "0.0 L/min"
            list[9]=lsit1
        }

        var  lsit2=gson.fromJson(gson.toJson(list[7]), DataTitle::class.java)
        var data2= (it.data[7].toFloat()/(10).toFloat()).toString()
        lsit2.value="$data2 bar"
        list[7]=lsit2

        var data3=  ( Hexs.pinJie2ByteToInt(it.data[4],it.data[3]).toFloat()/(10).toFloat()).toString()
        lsit3.value= "$data3 ℃"
        list[10]=lsit3

        var data4=  ( Hexs.pinJie2ByteToInt(it.data[2],it.data[1]).toFloat()/(10).toFloat()).toString()
        lsit4.value= "$data4 ℃"
        list[8]=lsit4

        var  lsit5=gson.fromJson(gson.toJson(list[1]), DataTitle::class.java)
        var data5=  ( Hexs.pinJie2ByteToInt(it.data[6],it.data[5]).toFloat()/(10).toFloat()).toString()
        lsit5.value= "$data5 ℃"
        list[1]=lsit5

    }
}