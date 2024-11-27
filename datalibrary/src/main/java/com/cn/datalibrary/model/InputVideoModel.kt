package com.cn.datalibrary.model

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.cn.datalibrary.api.ApiRepository
import com.google.gson.Gson
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.bean.DataTitle
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.common_library.util.Hexs

class InputVideoModel   (private val repository: ApiRepository,var gson: Gson) : BaseModel() {

    fun initData(): ObservableList<DataTitle> {
        val projectBaseList: ObservableList<DataTitle> = ObservableArrayList()
        var projectBase1= DataTitle("出煤传感器 ","",true)
        var projectBase2= DataTitle("回煤传感器 ",true)
        var projectBase3= DataTitle("冷却传感器 ","",true)
        var projectBase4= DataTitle("流量传感器 ",true)
        var projectBase5= DataTitle("出煤压力 ",true)
        var projectBase6= DataTitle("回煤压力 ",true)
        var projectBase7= DataTitle("进水压力 ",true)
        var projectBase8= DataTitle("回水压力 ",true)
        var projectBase9= DataTitle("L1相电流状态 ",true)
        var projectBase10= DataTitle("L2相电流状态 ",true)
        var projectBase11= DataTitle("L3相电流状态 ",true)
        var projectBase12= DataTitle("填充水状态 ",true)
        var projectBase13= DataTitle("24V电压状态 ",true)
        var projectBase14= DataTitle("水泵压力状态 ",true)
        var projectBase15= DataTitle("冷却超时状态 ",true)
        var projectBase16= DataTitle("加热超时状态 ",true)
        projectBaseList.add(projectBase1)
        projectBaseList.add(projectBase2)
        projectBaseList.add(projectBase3)
        projectBaseList.add(projectBase4)
        projectBaseList.add(projectBase5)
        projectBaseList.add(projectBase6)
        projectBaseList.add(projectBase7)
        projectBaseList.add(projectBase8)
        projectBaseList.add(projectBase9)
        projectBaseList.add(projectBase10)
        projectBaseList.add(projectBase11)
        projectBaseList.add(projectBase12)
        projectBaseList.add(projectBase13)
        projectBaseList.add(projectBase14)
        projectBaseList.add(projectBase15)
        projectBaseList.add(projectBase16)
        return projectBaseList
    }

    fun setCan099Data(it: CanFrame, list: ObservableList<DataTitle>) {
    /*    var  datr1=gson.fromJson(gson.toJson(list[6]), DataTitle::class.java)
        var  datr2=gson.fromJson(gson.toJson(list[7]), DataTitle::class.java)
        var  datr3=gson.fromJson(gson.toJson(list[4]), DataTitle::class.java)
        datr1.value=(it.data[5].toFloat()/(10).toFloat()).toString()+" bar"
        list[6]=datr1
        datr2.value=(it.data[6].toFloat()/(10).toFloat()).toString()+" bar"
        list[7]=datr2
        datr3.value=(it.data[7].toFloat()/(10).toFloat()).toString()+" bar"
        list[4]=datr3*/
    }

    fun setCan100Data(it: CanFrame, list: ObservableList<DataTitle>) {
    /*    var  datr1=gson.fromJson(gson.toJson(list[5]), DataTitle::class.java)
        datr1.value=(it.data[7].toFloat()/(10).toFloat()).toString()+" bar"
        list[5]=datr1*/
    }
    fun setCan101Data(it: CanFrame, list: ObservableList<DataTitle>) {
        var data1=  Hexs. getBitByByte(it.data[0],0,1)
        var data2=  Hexs. getBitByByte(it.data[0],2,3)
        var data3=  Hexs. getBitByByte(it.data[0],4,5)
        var data7=  Hexs. getBitByByte(it.data[0],6,7)

        var data4=  Hexs. getBitByByte(it.data[3],6,7)
        var data5=  Hexs. getBitByByte(it.data[1],2,3)
        var data6=  Hexs. getBitByByte(it.data[1],4,5)
        var data9=  Hexs. getBitByByte(it.data[1],6,7)
        var data10=  Hexs. getBitByByte(it.data[2],0,1)
        var data11=  Hexs. getBitByByte(it.data[2],2,3)
        var data12=  Hexs. getBitByByte(it.data[2],4,5)
        var data13=  Hexs. getBitByByte(it.data[2],6,7)
        var data14=  Hexs. getBitByByte(it.data[3],0,1)
        var data15=  Hexs. getBitByByte(it.data[3],2,3)
        var data16=  Hexs. getBitByByte(it.data[3],4,5)
        var  datr9=gson.fromJson(gson.toJson(list[8]), DataTitle::class.java)
        if (data9>=1){
            datr9.isSelect= false
        }else{
            datr9.isSelect= true
        }
        list[8]=datr9


        var  datr10=gson.fromJson(gson.toJson(list[9]), DataTitle::class.java)
        if (data10>=1){
            datr10.isSelect= false
        }else{
            datr10.isSelect= true
        }
        list[9]=datr10

        var  datr11=gson.fromJson(gson.toJson(list[10]), DataTitle::class.java)
        if (data11>=1){
            datr11.isSelect= false
        }else{
            datr11.isSelect= true
        }
        list[10]=datr11

        var  datr12=gson.fromJson(gson.toJson(list[11]), DataTitle::class.java)
        if (data12>=1){
            datr12.isSelect= false
        }else{
            datr12.isSelect= true
        }
        list[11]=datr12

        var  datr13=gson.fromJson(gson.toJson(list[12]), DataTitle::class.java)
        if (data13>=1){
            datr13.isSelect= false
        }else{
            datr13.isSelect= true
        }
        list[12]=datr13

        var  datr14=gson.fromJson(gson.toJson(list[13]), DataTitle::class.java)
        if (data14>=1){
            datr14.isSelect= false
        }else{
            datr14.isSelect= true
        }
        list[13]=datr14

        var  datr15=gson.fromJson(gson.toJson(list[14]), DataTitle::class.java)
        if (data15>=1){
            datr15.isSelect= false
        }else{
            datr15.isSelect= true
        }
        list[14]=datr15

        var  datr16=gson.fromJson(gson.toJson(list[15]), DataTitle::class.java)
        if (data16>=1){
            datr16.isSelect= false
        }else{
            datr16.isSelect= true
        }
        list[15]=datr16

        var  datr7=gson.fromJson(gson.toJson(list[0]), DataTitle::class.java)
        if (data7>=1){
            datr7.isSelect= false
        }else{
            datr7.isSelect= true
        }
        list[0]=datr7

        var data8=  Hexs. getBitByByte(it.data[1],0,1)
        var  datr8=gson.fromJson(gson.toJson(list[1]), DataTitle::class.java)
        if (data8>=1){
            datr8.isSelect= false
        }else{
            datr8.isSelect= true
        }
        list[1]=datr8


        var  datr1=gson.fromJson(gson.toJson(list[2]), DataTitle::class.java)
        if (data5>=1){
            datr1.isSelect= false
        }else{
            datr1.isSelect= true
        }
        list[2]=datr1

        var  datr2=gson.fromJson(gson.toJson(list[3]), DataTitle::class.java)
        if (data6>=1){
            datr2.isSelect= false
        }else{
            datr2.isSelect= true
        }
        list[3]=datr2

        var  datr3=gson.fromJson(gson.toJson(list[4]), DataTitle::class.java)
        if (data3>=1){
            datr3.isSelect= false
        }else{
            datr3.isSelect= true
        }
        list[4]=datr3

        var  datr4=gson.fromJson(gson.toJson(list[5]), DataTitle::class.java)
        if (data4>=1){
            datr4.isSelect= false
        }else{
            datr4.isSelect= true
        }
        list[5]=datr4

        var  datr5=gson.fromJson(gson.toJson(list[6]), DataTitle::class.java)
        if (data1>=1){
            datr5.isSelect= false
        }else{
            datr5.isSelect= true
        }
        list[6]=datr5

        var  datr6=gson.fromJson(gson.toJson(list[7]), DataTitle::class.java)
        if (data2>=1){
            datr6.isSelect= false
        }else{
            datr6.isSelect= true
        }
        list[7]=datr6
    }

}