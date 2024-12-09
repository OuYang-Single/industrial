package com.cn.datalibrary.model

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.cn.datalibrary.api.ApiRepository
import com.google.gson.Gson
import com.ijcsj.common_library.bean.DataTitle
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.util.Hexs

class OutputVideoModel  (private val repository: ApiRepository,var gson: Gson) : BaseModel() {

    fun initData(): ObservableList<DataTitle> {
        val projectBaseList: ObservableList<DataTitle> = ObservableArrayList()
        var projectBase1= DataTitle("泵运行 :","",false)
        var projectBase2= DataTitle("冷却阀 :",false)
        var projectBase3= DataTitle("加热器 :","",false)
        var projectBase4= DataTitle("增压泵 :",false)
        var projectBase5= DataTitle("进水阀 :",false)
        var projectBase6= DataTitle("出气阀 :",false)
        var projectBase7= DataTitle("排水阀 :",false)
        var projectBase8= DataTitle("故障报警 :",false)

        projectBaseList.add(projectBase1)
        projectBaseList.add(projectBase2)
        projectBaseList.add(projectBase3)
        projectBaseList.add(projectBase4)
        projectBaseList.add(projectBase5)
        projectBaseList.add(projectBase6)
        projectBaseList.add(projectBase7)
        projectBaseList.add(projectBase8)

        return projectBaseList
    }

    fun setCan102Data(its: CanFrame, list: MutableList<DataTitle>){
        var data1=  if (Hexs.getBitByByte( its.data[0],0)==1){ "开" } else{ "关" }
        var data2=  if (Hexs.getBitByByte( its.data[0],1)==1){ "开" } else{ "关" }
        var data3=  if (Hexs.getBitByByte( its.data[0],2)==1){ "开" } else{ "关" }
        var data4=  if (Hexs.getBitByByte( its.data[0],3)==1){ "开" } else{ "关" }
        var data5=  if (Hexs.getBitByByte( its.data[0],4)==1){ "开" } else{ "关" }
        var data6=  if (Hexs.getBitByByte( its.data[0],5)==1){ "开" } else{ "关" }
        var data7=  if (Hexs.getBitByByte( its.data[0],6)==1){ "开" } else{ "关" }

        var  lsit1=gson.fromJson(gson.toJson(list[0]), DataTitle::class.java)
        var  lsit2=gson.fromJson(gson.toJson(list[1]), DataTitle::class.java)
        var  lsit3=gson.fromJson(gson.toJson(list[2]), DataTitle::class.java)
        var  lsit4=gson.fromJson(gson.toJson(list[3]), DataTitle::class.java)
        var  lsit5=gson.fromJson(gson.toJson(list[4]), DataTitle::class.java)
        var  lsit6=gson.fromJson(gson.toJson(list[5]), DataTitle::class.java)
        var  lsit7=gson.fromJson(gson.toJson(list[6]), DataTitle::class.java)

        if (lsit1.value!=data1){
            lsit1.value=data1
            list[0]=lsit1
        }
      if (lsit2.value!=data2){
            lsit2.value=data2
            list[1]=lsit2
        }
        if (lsit3.value!=data3){
            lsit3.value=data3
            list[2]=lsit3
        }
        if (lsit4.value!=data4){
            lsit4.value=data4
            list[3]=lsit4
        }
        if (lsit5.value!=data5){
            lsit5.value=data5
            list[4]=lsit5
        }
        if (lsit6.value!=data6){
            lsit6.value=data6
            list[5]=lsit6
        }
        if (lsit7.value!=data7){
            lsit7.value=data7
            list[6]=lsit7
        }
    }

    fun setCan101Data(it: CanFrame, list: ObservableList<DataTitle>) {
        var data1=  Hexs. getBitByByte(it.data[0],0,1)
        var data2=  Hexs. getBitByByte(it.data[0],2,3)
        var data3=  Hexs. getBitByByte(it.data[0],4,5)
        var data4=  Hexs. getBitByByte(it.data[0],6,7)
        var data5=  Hexs. getBitByByte(it.data[1],0,1)
        var data6=  Hexs. getBitByByte(it.data[1],2,3)
        var data7=  Hexs. getBitByByte(it.data[1],4,5)
        var data8=  Hexs. getBitByByte(it.data[1],6,7)
        var data9=  Hexs. getBitByByte(it.data[2],0,1)
        var data10=  Hexs. getBitByByte(it.data[2],2,3)
        var data11=  Hexs. getBitByByte(it.data[2],4,5)
        var data12=  Hexs. getBitByByte(it.data[2],6,7)
        var data13=  Hexs. getBitByByte(it.data[3],0,1)
        var data14=  Hexs. getBitByByte(it.data[3],2)
        var data15=  Hexs. getBitByByte(it.data[3],3)
        var data16=  Hexs. getBitByByte(it.data[3],6,7)
        val data: ObservableList<Int> = ObservableArrayList()
        data.add(data1)
        data.add(data2)
        data.add(data3)
        data.add(data4)
        data.add(data5)
        data.add(data6)
        data.add(data7)
        data.add(data8)
        data.add(data9)
        data.add(data10)
        data.add(data11)
        data.add(data12)
        data.add(data13)
        data.add(data14)
        data.add(data15)
        data.add(data16)
        var valueData=false;
        for (i in 0 until data.size) {
            if (data[i]==1){
                valueData=true
                break
            }
        }
        var  lsit8=gson.fromJson(gson.toJson(list[7]), DataTitle::class.java)

        lsit8.value=if (valueData){"报警"}else{""}
        list[7]=lsit8
    }

}