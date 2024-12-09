package com.ijcsj.inlet_library.model

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.BackFlowBase
import com.ijcsj.common_library.bean.BackFlowBaseDatabase
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.bean.FaceInit
import com.ijcsj.common_library.bean.HistoryBase
import com.ijcsj.common_library.bean.HistoryBaseDatabase
import com.ijcsj.common_library.bean.RealStatus
import com.ijcsj.common_library.bean.SetUpBaseDatabase
import com.ijcsj.common_library.bean.SetUpBean
import com.ijcsj.common_library.bean.TemperatureBase
import com.ijcsj.common_library.bean.TemperatureBaseDatabase
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.common_library.util.DateUtil
import com.ijcsj.common_library.util.Hexs
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.common_library.util.a
import com.ijcsj.inlet_library.api.ApiRepository
import com.ijcsj.inlet_library.base.TximBase
import com.ijcsj.inlet_library.base.TximUser
import com.ijcsj.ui_library.utils.AppGlobals
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

class MainModel(private val repository: ApiRepository): BaseModel() {
    suspend   fun getUpgradeInfo( versionKey:String): ApiResult<String> {
        return   repository.getUpgradeInfo(versionKey);
    }

    suspend   fun tximInit():  ApiResult<TximBase>  {
        return   repository.tximInit();
    }

    suspend   fun getUserSig():  ApiResult<TximUser>  {
        return   repository.getUserSig();
    }
    suspend   fun getFaceSdkSign():  ApiResult<FaceInit>  {
        return   repository.getFaceSdkSign();
    }

    suspend   fun  getRealStatus(): ApiResult<RealStatus>  {
        return   repository.getRealStatus();
    }

    suspend  fun addFormData(canFrame: CanFrame){
        withContext(Dispatchers.IO) {
            AppGlobals.get()?.let {
                if (canFrame.can_id!=256){
                    return@let
                }
                var te=   TemperatureBase()
                te.dateTime=  Date(System.currentTimeMillis())
                te.time=DateUtil.formatTime( Date(System.currentTimeMillis()),"YYYY-MM-dd HH")
                te.temperature=   ( Hexs.pinJie2ByteToInt(canFrame.data[2],canFrame.data[1]).toFloat()/(10).toFloat()).toInt()

                var ted=   BackFlowBase()
                ted.dateTime=  Date(System.currentTimeMillis())
                ted.time=DateUtil.formatTime( Date(System.currentTimeMillis()),"YYYY-MM-dd HH")
                ted.temperature=   ( Hexs.pinJie2ByteToInt(canFrame.data[4],canFrame.data[3]).toFloat()/(10).toFloat()).toInt()

                var tedd=   SetUpBean()
                tedd.dateTime=  Date(System.currentTimeMillis())
                tedd.time=DateUtil.formatTime( Date(System.currentTimeMillis()),"YYYY-MM-dd HH")
                var d=  ShuJuMMkV.getInstances()?.getString(a.SETTING_TEMPERATURE,"1200")
                tedd.temperature =d!!.toInt()

                var setUpBean=  SetUpBaseDatabase.getDatabase(it).backFlowBaseDao().getUsersWithName( tedd.time)
                if (setUpBean==null){
                    Logger.w("添加成功1"+ te.temperature)
                    var s=SetUpBaseDatabase.getDatabase(it).backFlowBaseDao().getAllData()
                    if (s.size>=48){
                        SetUpBaseDatabase.getDatabase(it).backFlowBaseDao().delete(s[0])
                    }
                    SetUpBaseDatabase.getDatabase(it).backFlowBaseDao().insert(tedd)
                }

                var backFlowBase=  BackFlowBaseDatabase.getDatabase(it).backFlowBaseDao().getUsersWithName( ted.time)
                if (backFlowBase==null){
                    Logger.w("添加成功2"+ te.temperature)
                    var s=BackFlowBaseDatabase.getDatabase(it).backFlowBaseDao().getAllData()
                    if (s.size>=48){
                        BackFlowBaseDatabase.getDatabase(it).backFlowBaseDao().delete(s[0])
                    }
                    BackFlowBaseDatabase.getDatabase(it).backFlowBaseDao().insert(ted)
                }
                var temperatureBase=  TemperatureBaseDatabase.getDatabase(it).temperatureBaseDao().getUsersWithName( te.time)
                if (temperatureBase==null){
                    Logger.w("添加成功3"+ te.temperature)
                    var s=TemperatureBaseDatabase.getDatabase(it).temperatureBaseDao().getAllData()
                    if (s.size>=48){
                        TemperatureBaseDatabase.getDatabase(it).temperatureBaseDao().delete(s[0])
                        LiveDataBus.get().with("temperature_base", Boolean::class.java ).postValue(true)
                    }
                    TemperatureBaseDatabase.getDatabase(it).temperatureBaseDao().insert(te)
                }

            }
        }
    }

    val projectBaseList: ArrayList<Int?> = ArrayList()
    var headers: HashMap<String, String> = HashMap()
    var headerd: HashMap<String, String> = HashMap()

    suspend  fun addHistoryBase(canFrame: CanFrame,list:ArrayList<String>):Int {
      return  withContext(Dispatchers.IO) {
          var ints=0
            AppGlobals.get()?.let {
                if (canFrame.can_id!=257){
                    return@let
                }
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
                    if (projectBaseList[i]==1){
                        list.add(getString(i))
                        ints++;
                    }else if (projectBaseList[i]==2){
                        list.add(getStrings(i))
                        ints++;
                    }else if (projectBaseList[i]==3){
                        list.add(getStrings2(i))
                        ints++;
                    }
                    headerd[i.toString()]=projectBaseList[i].toString()
                }
                for (i in 0 until projectBaseList.size) {
                    if (projectBaseList[i]==1){
                        updateHistoryBase(it,getStrings(i))
                        updateHistoryBase(it,getStrings2(i))
                      var a1=   ShuJuMMkV.getInstances()?.getString("addHistoryBase$i")
                      var value=    headers[i.toString()]
                       if (value!=null){
                           if (value!=projectBaseList[i].toString()){
                               HistoryBaseDatabase.getDatabase(it).historyBaseDao().insertAll(   HistoryBase("1",getString(i),DateUtil.formatTime( Date(System.currentTimeMillis()),"YYYY-MM-dd HH"),"未处理",Date(System.currentTimeMillis())))
                           }
                       }else{
                           Log.w("CAN_101", "addHistoryBase$i  $a1  ${projectBaseList[i]}")
                           if (!TextUtils.isEmpty(a1)){
                               if (a1!=projectBaseList[i].toString()){
                                   Log.w("CAN_101", "addHistoryBase$i a1!=projectBaseList[i].toString() $a1")
                                   HistoryBaseDatabase.getDatabase(it).historyBaseDao().insertAll(   HistoryBase("1",getString(i),DateUtil.formatTime( Date(System.currentTimeMillis()),"YYYY-MM-dd HH"),"未处理",Date(System.currentTimeMillis())))
                               }
                           }else{
                               Log.w("CAN_101", "addHistoryBase$i -----a1 $a1")
                               HistoryBaseDatabase.getDatabase(it).historyBaseDao().insertAll(   HistoryBase("1",getString(i),DateUtil.formatTime( Date(System.currentTimeMillis()),"YYYY-MM-dd HH"),"未处理",Date(System.currentTimeMillis())))
                           }
                     }
                    }else if (projectBaseList[i]==2){
                        updateHistoryBase(it,getString(i))
                        updateHistoryBase(it,getStrings2(i))
                        var a1=   ShuJuMMkV.getInstances()?.getString("addHistoryBase$i")
                        var value=    headers[i.toString()]
                        if (value!=null){
                            if (value!=projectBaseList[i].toString()){
                                HistoryBaseDatabase.getDatabase(it).historyBaseDao().insertAll(   HistoryBase("1",getStrings(i),DateUtil.formatTime( Date(System.currentTimeMillis()),"YYYY-MM-dd HH"),"未处理",Date(System.currentTimeMillis())))
                            }
                        }else{
                            Log.w("CAN_101", "addHistoryBase$i  $a1  ${projectBaseList[i]}")
                            if (!TextUtils.isEmpty(a1)){
                                if (a1!=projectBaseList[i].toString()){
                                    Log.w("CAN_101", "addHistoryBase$i a1!=projectBaseList[i].toString() $a1")
                                    HistoryBaseDatabase.getDatabase(it).historyBaseDao().insertAll(   HistoryBase("1",getStrings(i),DateUtil.formatTime( Date(System.currentTimeMillis()),"YYYY-MM-dd HH"),"未处理",Date(System.currentTimeMillis())))
                                }
                            }else{
                                Log.w("CAN_101", "addHistoryBase$i -----a1 $a1")
                                HistoryBaseDatabase.getDatabase(it).historyBaseDao().insertAll(   HistoryBase("1",getStrings(i),DateUtil.formatTime( Date(System.currentTimeMillis()),"YYYY-MM-dd HH"),"未处理",Date(System.currentTimeMillis())))
                            }
                        }
                    }else if (projectBaseList[i]==3){
                        updateHistoryBase(it,getString(i))
                        updateHistoryBase(it,getStrings(i))
                        var a1=   ShuJuMMkV.getInstances()?.getString("addHistoryBase$i")
                        var value=    headers[i.toString()]
                        if (value!=null){
                            if (value!=projectBaseList[i].toString()){
                                HistoryBaseDatabase.getDatabase(it).historyBaseDao().insertAll(   HistoryBase("1",getStrings2(i),DateUtil.formatTime( Date(System.currentTimeMillis()),"YYYY-MM-dd HH"),"未处理",Date(System.currentTimeMillis())))
                            }
                        }else{
                            Log.w("CAN_101", "addHistoryBase$i  $a1  ${projectBaseList[i]}")
                            if (!TextUtils.isEmpty(a1)){
                                if (a1!=projectBaseList[i].toString()){
                                    Log.w("CAN_101", "addHistoryBase$i a1!=projectBaseList[i].toString() $a1")
                                    HistoryBaseDatabase.getDatabase(it).historyBaseDao().insertAll(   HistoryBase("1",getStrings2(i),DateUtil.formatTime( Date(System.currentTimeMillis()),"YYYY-MM-dd HH"),"未处理",Date(System.currentTimeMillis())))
                                }
                            }else{
                                Log.w("CAN_101", "addHistoryBase$i -----a1 $a1")
                                HistoryBaseDatabase.getDatabase(it).historyBaseDao().insertAll(   HistoryBase("1",getStrings2(i),DateUtil.formatTime( Date(System.currentTimeMillis()),"YYYY-MM-dd HH"),"未处理",Date(System.currentTimeMillis())))
                            }
                        }
                    }else{
                        updateHistoryBase(it,getString(i))
                        updateHistoryBase(it,getStrings(i))
                        updateHistoryBase(it,getStrings2(i))
                    }

                    ShuJuMMkV.getInstances()?.putString("addHistoryBase$i", projectBaseList[i].toString())
                  //  headers[i.toString()] = projectBaseList[i].toString()
                }

            }
          ints
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
     private fun  updateHistoryBase(context: Context, last_reason:String) {
        var dateList1=   HistoryBaseDatabase.getDatabase(context).historyBaseDao().getHistoryBaseWithSameData(last_reason,"未处理")
        if (dateList1.size>0){
            dateList1[0].state="已处理"
            HistoryBaseDatabase.getDatabase(context).historyBaseDao().updateHistoryBase(dateList1[0])
        }
    }
}