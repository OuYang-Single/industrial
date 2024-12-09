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
        var projectBase1= DataTitle("进水压力 ","",true)
        var projectBase2= DataTitle("回水压力 ",true)
        var projectBase3= DataTitle("出媒压力 ","",true)
        var projectBase4= DataTitle("始流温度 ",true)
        var projectBase5= DataTitle("回流温度 ",true)
        var projectBase6= DataTitle("冷却器温度 ",true)
        var projectBase7= DataTitle("流量状态",true)
        var projectBase8= DataTitle("三相电L1 ",true)
        var projectBase9= DataTitle("三相电L2 ",true)
        var projectBase10= DataTitle("三相电L3 ",true)
        var projectBase11= DataTitle("填充水状态 ",true)
        var projectBase12= DataTitle("24V电压 ",true)
        var projectBase13= DataTitle("水泵状态 ",true)
        var projectBase14= DataTitle("加热超时 ",true)
        var projectBase15= DataTitle("冷却超时 ",true)
        var projectBase16= DataTitle("回媒压力 ",true)
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
    val projectBaseList: ArrayList<Int?> = ArrayList()
    fun setCan101Data(canFrame: CanFrame, list: ObservableList<DataTitle>) {
        projectBaseList.clear()
        var data1=  Hexs. getBitByByte(canFrame.data[0],0,1)
        var data2=  Hexs. getBitByByte(canFrame.data[0],2,3)
        var data3=  Hexs. getBitByByte(canFrame.data[0],4,5)
        var data4=  Hexs. getBitByByte(canFrame.data[0],6,7)
        var data5=  Hexs. getBitByByte(canFrame.data[1],0,1)
        var data6=  Hexs. getBitByByte(canFrame.data[1],2,3)
        var data7=  Hexs. getBitByByte(canFrame.data[1],4,5)
        var data8=  Hexs. getBitByByte(canFrame.data[1],6,7)
        var data9=  Hexs. getBitByByte(canFrame.data[2],0,1)
        var data10=  Hexs. getBitByByte(canFrame.data[2],2,3)
        var data11=  Hexs. getBitByByte(canFrame.data[2],4,5)
        var data12=  Hexs. getBitByByte(canFrame.data[2],6,7)
        var data13=  Hexs. getBitByByte(canFrame.data[3],0,1)
        var data14=  Hexs. getBitByByte(canFrame.data[3],2)
        var data15=  Hexs. getBitByByte(canFrame.data[3],3)
        var data16=  Hexs. getBitByByte(canFrame.data[3],6,7)
        projectBaseList.add(data1)
        projectBaseList.add(data2)
        projectBaseList.add(data3)
        projectBaseList.add(data4)
        projectBaseList.add(data5)
        projectBaseList.add(data6)
        projectBaseList.add(data7)
        projectBaseList.add(data8)
        projectBaseList.add(data9)
        projectBaseList.add(data10)
        projectBaseList.add(data11)
        projectBaseList.add(data12)
        projectBaseList.add(data13)
        projectBaseList.add(data14)
        projectBaseList.add(data15)
        projectBaseList.add(data16)
        for (i in 0 until projectBaseList.size) {
            val datr=gson.fromJson(gson.toJson(list[i]), DataTitle::class.java)
            if (projectBaseList[i]!! >=1){
                datr.isSelect= false
              var value=  when(projectBaseList[i]!!){
                    1->getString(i)
                    2->getStrings(i)
                    3->getStrings2(i)
                  else ->""
                }
                datr.values=value
            }else{
                datr.isSelect= true
            }
            list[i]=datr
        }
    }
    private fun getString(int: Int):String{
        return  when(int){
            0->"进水压力状态错误"
            1->"回水压力状态错误"
            2->"出媒压力状态错误"
            3->"始流温度状态错误"
            4->"回流温度状态错误"
            5->"冷却器温度状态错误"
            6->"流量状态错误"
            7->"三相电L1相错误"
            8->"三相电L2相错误"
            9->"三相电L3相错误"
            10->"填充水状态错误"
            11->"24V电压状态错误"
            12->"水泵状态错误"
            13->"加热超时"
            14->"冷却超时"
            15->"回媒压力状态错误"
            else -> {""}
        }
    }
    private fun getStrings(int: Int):String{
        return  when(int){
            0->"进水压力达到最大"
            1->"回水压力达到最大"
            2->"出媒压力达到最大"
            3->"参考温度过高"
            4->"回流温度达到最大"
            5->"冷却器温度达到最大"
            6->"流量信号异常"
            7->"L1相电流达到最大"
            8->"L2相电流达到最大"
            9->"L3相电流达到最大"
            15->"回媒压力达到最大"
            else -> {""}
        }
    }
    private fun getStrings2(int: Int):String{
        return  when(int){
            0->"进水压力达到最小"
            1->"回水压力达到最小"
            2->"出媒压力达到最小"
            3->"参考温度过低"
            4->"出回流温差大"
            5->"冷却器温度达到最小"
            6->"流量达到最小"
            7->"L1相电流达到最小"
            8->"L2相电流达到最小"
            9->"L3相电流达到最小"
            15->"回媒压力达到最小"
            else -> {""}
        }
    }
}