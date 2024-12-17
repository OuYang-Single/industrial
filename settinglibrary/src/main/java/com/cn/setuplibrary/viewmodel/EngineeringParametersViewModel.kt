package com.cn.setuplibrary.viewmodel

import android.os.Build
import android.view.View
import android.view.Window
import com.alibaba.android.arouter.launcher.ARouter
import com.cn.setuplibrary.adapter.StyleAdapter
import com.cn.setuplibrary.bean.EngineeringBean
import com.cn.setuplibrary.model.EngineeringParametersModel
import com.cn.setuplibrary.popup.StylePopup
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.AESExample
import com.ijcsj.common_library.util.Hexs
import com.ijcsj.common_library.util.a
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.stUplibrary.R
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.orhanobut.logger.Logger

class EngineeringParametersViewModel  (override val model: EngineeringParametersModel,var engineerIngBean: EngineeringBean) : MvmBaseViewModel<IBaseView, EngineeringParametersModel>(){
    var basePopupView: BasePopupView?=null;
    public override fun initModel() {
        var pidP=  ShuJuMMkV.getInstances()?.getString(a.PID_P,"100")
        var pidI=  ShuJuMMkV.getInstances()?.getString(a.PID_I,"1")
        var pidD=  ShuJuMMkV.getInstances()?.getString(a.PID_D,"10")
        var tempMode=  ShuJuMMkV.getInstances()?.getString(a.TEMP_MODE,"0")?.toInt()
        engineerIngBean.pinP=(pidP!!.toFloat()/100.toFloat()).toString()
        engineerIngBean.pinI=(pidI!!.toFloat()/1000.toFloat()).toString()
        engineerIngBean.pinD=(pidD!!.toFloat()/100.toFloat()).toString()
        if (tempMode==0){
            engineerIngBean.temperatureMode="出媒"
        }else{
            engineerIngBean.temperatureMode="回媒"
        }
        engineerIngBean.fillingTime=ShuJuMMkV.getInstances()?.getString(a.FILLING_TIME,"4")+" min"
        engineerIngBean.pumpJammingTime=ShuJuMMkV.getInstances()?.getString(a.DELAY_PUMP_START_TIME,"10")+" S"
        engineerIngBean.emptyingTime=ShuJuMMkV.getInstances()?.getString(a.PUMP_ON_TIME,"0")+" S"
        engineerIngBean.ipAddress= ShuJuMMkV.getInstances()?.getString(a.IP_ADDRESS,"0")+" "
        engineerIngBean.coolingTemperature=ShuJuMMkV.getInstances()?.getString(a.COOLING_TEMPERATURE,"60")+" °C"
        engineerIngBean.highDeviation= ( ShuJuMMkV.getInstances()?.getString(a.HIGH_DEVIATION,"50")!!.toFloat()/10.toFloat()).toString()+" ℃"
        engineerIngBean.lowDeviation= ( ShuJuMMkV.getInstances()?.getString(a.LOW_DEVIATION,"50")!!.toFloat()/10.toFloat()).toString()+" ℃"
        engineerIngBean.temperatureDifference= ( ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DIFFERENCE,"50")!!.toFloat()/10.toFloat()).toString()+" ℃"
        engineerIngBean.temperatureDeviationTime= ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DEVIATION_TIME,"20")+" min"
        engineerIngBean.heatingTimeOut= ShuJuMMkV.getInstances()?.getString(a.HEATING_TIMEOUT,"120")+" min"
        engineerIngBean.coolingTimeOut= ShuJuMMkV.getInstances()?.getString(a.COOLING_TIMEOUT,"120")+" min"

        engineerIngBean.exhaustPressure= ( ShuJuMMkV.getInstances()?.getString(a.EXHAUST_PRESSURE,"50")!!.toFloat()/10.toFloat()).toString()+" bar"
        engineerIngBean.returnPressureDifference= ( ShuJuMMkV.getInstances()?.getString(a.RETURN_PRESSURE_DIFFERENCE,"50")!!.toFloat()/10.toFloat()).toString()+" bar"
        engineerIngBean.highPressureDeviation= ( ShuJuMMkV.getInstances()?.getString(a.HIGH_PRESSURE_DEVIATION,"130")!!.toFloat()/10.toFloat()).toString()+" bar"
        engineerIngBean.lowPressureDeviation= ( ShuJuMMkV.getInstances()?.getString(a.LOW_PRESSURE_DEVIATION,"20")!!.toFloat()/10.toFloat()).toString()+" bar"
        engineerIngBean.minimumInletPressure= (  ShuJuMMkV.getInstances()?.getString(a.MINIMUM_INLET_PRESSURE,"10")!!.toFloat()/10.toFloat()).toString()+" bar"
        engineerIngBean.maximumReturnWaterPressure= (ShuJuMMkV.getInstances()?.getString(a.MAXIMUM_RETURN_WATER_PRESSURE,"25")!!.toFloat()/10.toFloat()).toString()+" bar"
        engineerIngBean.minimumPumpPressure= ( ShuJuMMkV.getInstances()?.getString(a.MINIMUM_PUMP_PRESSURE,"5")!!.toFloat()/10.toFloat()).toString()+" bar"
        engineerIngBean.minimumFlowValue= (  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_11,"0")!!.toFloat()/10.toFloat()).toString()+" L/min"
        engineerIngBean.minimumTrafficDelayTime= (  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_12,"0")).toString()+" min"

        engineerIngBean.coalCompensation= (ShuJuMMkV.getInstances()?.getString(a.COAL_COMPENSATION,"0")!!.toFloat()/10.toFloat()).toString()+" °C"
        engineerIngBean.coalReturnCompensation= ( ShuJuMMkV.getInstances()?.getString(a.COAL_RETURN_COMPENSATION,"0")!!.toFloat()/10.toFloat()).toString()+" °C"
    }

    var onClickPinP=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
    }

    var onClickStartMode=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        list.add(it.context.getString(R.string.style_fantasy_art))
        list.add(it.context.getString(R.string.style_advertising))
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    bytes2[0]= a.from10To2sd(int)
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DIFFERENCE,"50")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.HIGH_DEVIATION,"50")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.LOW_DEVIATION,"50")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DEVIATION_TIME,"20")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.HEATING_TIMEOUT,"120")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TIMEOUT,"120")
                    bytes2[1]=(d1!!.toInt()and 0xff).toByte()
                    bytes2[2]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (d3!!.toInt()and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d5!!.toInt() and 0xff).toByte()
                    bytes2[6]= (d6!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_105,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.temperatureMode=string
                        ShuJuMMkV.getInstances()?.putString(a.TEMP_MODE,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var onClickFillingTime=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
                list.add("$i min")
        }

        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0=  ShuJuMMkV.getInstances()?.getString(a.WORKING_MODE,"5")
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.SETTING_TEMPERATURE,"1200")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.PUMP_ON_TIME,"0")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.IP_ADDRESS,"0")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TEMPERATURE,"60")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.DELAY_PUMP_START_TIME,"10")
                    var d11=  Hexs.  hex2LowHighByte(d1!!.toLong())
                    bytes2[0]= (d0!!.toInt() and 0xff).toByte()
                    bytes2[1]=d11[0]
                    bytes2[2]=d11[1]
                    bytes2[3]=  (int!!.toInt() and 0xff).toByte()
                    bytes2[4]= (d3!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d4!!.toInt()and 0xff ).toByte()
                    bytes2[6]= (d5!!.toInt()and 0xff).toByte()
                    bytes2[7]= (d6!!.toInt()and 0xff ).toByte()
                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_103,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        ShuJuMMkV.getInstances()?.putString(a.FILLING_TIME,int.toString())
                        engineerIngBean.fillingTime=string
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()


    }

    var onIpAddressClick=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("$i ")
        }

        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0=  ShuJuMMkV.getInstances()?.getString(a.WORKING_MODE,"5")
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.SETTING_TEMPERATURE,"1200")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.PUMP_ON_TIME,"0")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.FILLING_TIME,"4")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TEMPERATURE,"60")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.DELAY_PUMP_START_TIME,"10")
                    var d11=  Hexs.  hex2LowHighByte(d1!!.toLong())
                    bytes2[0]= (d0!!.toInt() and 0xff).toByte()
                    bytes2[1]=d11[0]
                    bytes2[2]=d11[1]
                    bytes2[3]=  (d4!!.toInt() and 0xff).toByte()
                    bytes2[4]= (d3!!.toInt()and 0xff).toByte()
                    bytes2[5]= (int!!.toInt()and 0xff ).toByte()
                    bytes2[6]= (d5!!.toInt()and 0xff).toByte()
                    bytes2[7]= (d6!!.toInt()and 0xff ).toByte()
                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_103,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        ShuJuMMkV.getInstances()?.putString(a.IP_ADDRESS,int.toString())
                        engineerIngBean.ipAddress=string
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }

    var onClickPumpJammingTime=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("$i s")
        }

        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0=  ShuJuMMkV.getInstances()?.getString(a.WORKING_MODE,"5")
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.SETTING_TEMPERATURE,"1200")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.FILLING_TIME,"4")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.PUMP_ON_TIME,"0")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.IP_ADDRESS,"0")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TEMPERATURE,"60")

                    var d11=  Hexs.  hex2LowHighByte(d1!!.toLong())
                    bytes2[0]= (d0!!.toInt() and 0xff).toByte()
                    bytes2[1]=d11[0]
                    bytes2[2]=d11[1]
                    bytes2[3]=  (d2!!.toInt() and 0xff).toByte()
                    bytes2[4]=   (d3!!.toInt() and 0xff).toByte()
                    bytes2[5]= (d4!!.toInt()and 0xff ).toByte()
                    bytes2[6]= (d5!!.toInt()and 0xff).toByte()
                    bytes2[7]= (int!!.toInt()and 0xff ).toByte()
                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_103,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        ShuJuMMkV.getInstances()?.putString(a.DELAY_PUMP_START_TIME,int.toString())
                        engineerIngBean.pumpJammingTime=string
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var onClickEmptyingTime =  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("$i s")
        }

        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0=  ShuJuMMkV.getInstances()?.getString(a.WORKING_MODE,"5")
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.SETTING_TEMPERATURE,"1200")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.FILLING_TIME,"4")

                    var d4=  ShuJuMMkV.getInstances()?.getString(a.IP_ADDRESS,"0")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TEMPERATURE,"60")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.DELAY_PUMP_START_TIME,"10")
                    var d11=  Hexs.  hex2LowHighByte(d1!!.toLong())
                    bytes2[0]= (d0!!.toInt() and 0xff).toByte()
                    bytes2[1]=d11[0]
                    bytes2[2]=d11[1]
                    bytes2[3]=  (d2!!.toInt() and 0xff).toByte()
                    bytes2[4]= (int!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d4!!.toInt()and 0xff ).toByte()
                    bytes2[6]= (d5!!.toInt()and 0xff).toByte()
                    bytes2[7]= (d6!!.toInt()and 0xff ).toByte()
                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_103,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        ShuJuMMkV.getInstances()?.putString(a.PUMP_ON_TIME,int.toString())
                        engineerIngBean.emptyingTime=string
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()


    }
    var onClickCoolingTemperature=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("$i °C")
        }

        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0=  ShuJuMMkV.getInstances()?.getString(a.WORKING_MODE,"5")
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.SETTING_TEMPERATURE,"1200")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.FILLING_TIME,"4")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.PUMP_ON_TIME,"0")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.IP_ADDRESS,"0")

                    var d6=  ShuJuMMkV.getInstances()?.getString(a.DELAY_PUMP_START_TIME,"10")
                    var d11=  Hexs.  hex2LowHighByte(d1!!.toLong())
                    bytes2[0]= (d0!!.toInt() and 0xff).toByte()
                    bytes2[1]=d11[0]
                    bytes2[2]=d11[1]
                    bytes2[3]=  (d2!!.toInt() and 0xff).toByte()
                    bytes2[4]= (d3!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d4!!.toInt()and 0xff ).toByte()
                    bytes2[6]= (int!!.toInt()and 0xff).toByte()
                    bytes2[7]= (d6!!.toInt()and 0xff ).toByte()
                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_103,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        ShuJuMMkV.getInstances()?.putString(a.COOLING_TEMPERATURE,int.toString())
                        engineerIngBean.coolingTemperature=string
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()


    }

    var   onClickHighDeviation=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i.toFloat()/10.toFloat()).toString() } ℃")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.TEMP_MODE,"0")
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DIFFERENCE,"50")

                    var d3=  ShuJuMMkV.getInstances()?.getString(a.LOW_DEVIATION,"50")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DEVIATION_TIME,"20")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.HEATING_TIMEOUT,"120")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TIMEOUT,"120")
                    bytes2[0]=(d0!!.toInt()and 0xff).toByte()
                    bytes2[1]=(d1!!.toInt()and 0xff).toByte()
                    bytes2[2]=(int!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (d3!!.toInt()and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d5!!.toInt() and 0xff).toByte()
                    bytes2[6]= (d6!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_105,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.highDeviation=string
                        ShuJuMMkV.getInstances()?.putString(a.HIGH_DEVIATION,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var  onClickLowDeviation=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i.toFloat()/10.toFloat()).toString() } ℃")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.TEMP_MODE,"0")
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DIFFERENCE,"50")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.HIGH_DEVIATION,"50")

                    var d4=  ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DEVIATION_TIME,"20")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.HEATING_TIMEOUT,"120")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TIMEOUT,"120")
                    bytes2[0]=(d0!!.toInt()and 0xff).toByte()
                    bytes2[1]=(d1!!.toInt()and 0xff).toByte()
                    bytes2[2]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (int!!.toInt()and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d5!!.toInt() and 0xff).toByte()
                    bytes2[6]= (d6!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_105,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.lowDeviation=string
                        ShuJuMMkV.getInstances()?.putString(a.LOW_DEVIATION,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }

    var  onClickTemperatureDifferencVlue=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i.toFloat()/10.toFloat()).toString() } ℃")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.TEMP_MODE,"0")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.HIGH_DEVIATION,"50")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.LOW_DEVIATION,"50")

                    var d4=  ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DEVIATION_TIME,"20")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.HEATING_TIMEOUT,"120")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TIMEOUT,"120")
                    bytes2[0]=(d0!!.toInt()and 0xff).toByte()
                    bytes2[1]=(int!!.toInt()and 0xff).toByte()
                    bytes2[2]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (d3!!.toInt()and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d5!!.toInt() and 0xff).toByte()
                    bytes2[6]= (d6!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_105,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.temperatureDifference=string
                        ShuJuMMkV.getInstances()?.putString(a.TEMPERATURE_DIFFERENCE,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var  onClickTemperatureDeviationTime=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i).toString() } min")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.TEMP_MODE,"0")
                   var d1=  ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DIFFERENCE,"50")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.HIGH_DEVIATION,"50")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.LOW_DEVIATION,"50")


                    var d5=  ShuJuMMkV.getInstances()?.getString(a.HEATING_TIMEOUT,"120")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TIMEOUT,"120")
                    bytes2[0]=(d0!!.toInt()and 0xff).toByte()
                    bytes2[1]=(d1!!.toInt()and 0xff).toByte()
                    bytes2[2]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (d3!!.toInt()and 0xff).toByte()
                    bytes2[4]= (int!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d5!!.toInt() and 0xff).toByte()
                    bytes2[6]= (d6!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_105,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.temperatureDeviationTime=string
                        ShuJuMMkV.getInstances()?.putString(a.TEMPERATURE_DEVIATION_TIME,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }


    var  onClickHeatingTimeOut=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i).toString() } min")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.TEMP_MODE,"0")
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DIFFERENCE,"50")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.HIGH_DEVIATION,"50")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.LOW_DEVIATION,"50")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DEVIATION_TIME,"20")



                    var d6=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TIMEOUT,"120")
                    bytes2[0]=(d0!!.toInt()and 0xff).toByte()
                    bytes2[1]=(d1!!.toInt()and 0xff).toByte()
                    bytes2[2]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (d3!!.toInt()and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt()and 0xff).toByte()
                    bytes2[5]= (int!!.toInt() and 0xff).toByte()
                    bytes2[6]= (d6!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_105,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.heatingTimeOut=string
                        ShuJuMMkV.getInstances()?.putString(a.HEATING_TIMEOUT,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var  onClickCoolingTimeOut=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i).toString() } min")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.TEMP_MODE,"0")
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DIFFERENCE,"50")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.HIGH_DEVIATION,"50")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.LOW_DEVIATION,"50")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DEVIATION_TIME,"20")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.HEATING_TIMEOUT,"120")
                    bytes2[0]=(d0!!.toInt()and 0xff).toByte()
                    bytes2[1]=(d1!!.toInt()and 0xff).toByte()
                    bytes2[2]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (d3!!.toInt()and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d5!!.toInt() and 0xff).toByte()
                    bytes2[6]= (int!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_105,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.coolingTimeOut=string
                        ShuJuMMkV.getInstances()?.putString(a.COOLING_TIMEOUT,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var  onClickExhaustPressure=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i.toFloat()/10.toFloat()).toString() } bar")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.RETURN_PRESSURE_DIFFERENCE,"50")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.HIGH_PRESSURE_DEVIATION,"130")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.LOW_PRESSURE_DEVIATION,"20")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.MINIMUM_INLET_PRESSURE,"10")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.MAXIMUM_RETURN_WATER_PRESSURE,"25")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.MINIMUM_PUMP_PRESSURE,"5")
                    bytes2[0]=(int!!.toInt()and 0xff).toByte()
                    bytes2[1]=(d1!!.toInt()and 0xff).toByte()
                    bytes2[2]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (d3!!.toInt()and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d5!!.toInt() and 0xff).toByte()
                    bytes2[6]= (d6!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_106,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.exhaustPressure=string
                        ShuJuMMkV.getInstances()?.putString(a.EXHAUST_PRESSURE,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var  onClickReturnPressureDifference=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i.toFloat()/10.toFloat()).toString() } bar")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.EXHAUST_PRESSURE,"50")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.HIGH_PRESSURE_DEVIATION,"130")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.LOW_PRESSURE_DEVIATION,"20")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.MINIMUM_INLET_PRESSURE,"10")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.MAXIMUM_RETURN_WATER_PRESSURE,"25")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.MINIMUM_PUMP_PRESSURE,"5")
                    bytes2[0]=(d1!!.toInt()and 0xff).toByte()
                    bytes2[1]=(int!!.toInt()and 0xff).toByte()
                    bytes2[2]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (d3!!.toInt()and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d5!!.toInt() and 0xff).toByte()
                    bytes2[6]= (d6!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_106,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.returnPressureDifference=string
                        ShuJuMMkV.getInstances()?.putString(a. RETURN_PRESSURE_DIFFERENCE,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var  onClickHighPressureDeviation=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i.toFloat()/10.toFloat()).toString() } bar")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.EXHAUST_PRESSURE,"50")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.RETURN_PRESSURE_DIFFERENCE,"50")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.LOW_PRESSURE_DEVIATION,"20")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.MINIMUM_INLET_PRESSURE,"10")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.MAXIMUM_RETURN_WATER_PRESSURE,"25")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.MINIMUM_PUMP_PRESSURE,"5")
                    bytes2[0]=(d1!!.toInt()and 0xff).toByte()
                    bytes2[1]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[2]=(int!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (d3!!.toInt()and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d5!!.toInt() and 0xff).toByte()
                    bytes2[6]= (d6!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_106,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.highPressureDeviation=string
                        ShuJuMMkV.getInstances()?.putString(a. HIGH_PRESSURE_DEVIATION,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var  onClickLowPressureDeviation=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i.toFloat()/10.toFloat()).toString() } bar")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.EXHAUST_PRESSURE,"50")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.RETURN_PRESSURE_DIFFERENCE,"50")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.HIGH_PRESSURE_DEVIATION,"130")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.MINIMUM_INLET_PRESSURE,"10")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.MAXIMUM_RETURN_WATER_PRESSURE,"25")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.MINIMUM_PUMP_PRESSURE,"5")
                    bytes2[0]=(d1!!.toInt()and 0xff).toByte()
                    bytes2[1]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[2]=(d3!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (int!!.toInt()and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d5!!.toInt() and 0xff).toByte()
                    bytes2[6]= (d6!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_106,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.lowPressureDeviation=string
                        ShuJuMMkV.getInstances()?.putString(a. LOW_PRESSURE_DEVIATION,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var  onClickMinimumInletPressure=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i.toFloat()/10.toFloat()).toString() } bar")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.EXHAUST_PRESSURE,"50")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.RETURN_PRESSURE_DIFFERENCE,"50")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.HIGH_PRESSURE_DEVIATION,"130")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.LOW_PRESSURE_DEVIATION,"20")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.MAXIMUM_RETURN_WATER_PRESSURE,"25")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.MINIMUM_PUMP_PRESSURE,"5")
                    bytes2[0]=(d1!!.toInt()and 0xff).toByte()
                    bytes2[1]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[2]=(d3!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (d4!!.toInt()and 0xff).toByte()
                    bytes2[4]= (int!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d5!!.toInt() and 0xff).toByte()
                    bytes2[6]= (d6!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_106,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.minimumInletPressure=string
                        ShuJuMMkV.getInstances()?.putString(a. MINIMUM_INLET_PRESSURE,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var  onClickMaximumReturnWaterPressure=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i.toFloat()/10.toFloat()).toString() } bar")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.EXHAUST_PRESSURE,"50")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.RETURN_PRESSURE_DIFFERENCE,"50")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.HIGH_PRESSURE_DEVIATION,"130")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.LOW_PRESSURE_DEVIATION,"20")
                    var d5=    ShuJuMMkV.getInstances()?.getString(a.MINIMUM_INLET_PRESSURE,"10")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.MINIMUM_PUMP_PRESSURE,"5")
                    bytes2[0]=(d1!!.toInt()and 0xff).toByte()
                    bytes2[1]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[2]=(d3!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (d4!!.toInt()and 0xff).toByte()
                    bytes2[4]= (d5!!.toInt()and 0xff).toByte()
                    bytes2[5]= (int!!.toInt() and 0xff).toByte()
                    bytes2[6]= (d6!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_106,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.maximumReturnWaterPressure=string
                        ShuJuMMkV.getInstances()?.putString(a. MAXIMUM_RETURN_WATER_PRESSURE,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var  onClickMinimumPumpPressure=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i.toFloat()/10.toFloat()).toString() } bar")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.EXHAUST_PRESSURE,"50")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.RETURN_PRESSURE_DIFFERENCE,"50")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.HIGH_PRESSURE_DEVIATION,"130")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.LOW_PRESSURE_DEVIATION,"20")
                    var d5=    ShuJuMMkV.getInstances()?.getString(a.MINIMUM_INLET_PRESSURE,"10")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.MAXIMUM_RETURN_WATER_PRESSURE,"25")
                    bytes2[0]=(d1!!.toInt()and 0xff).toByte()
                    bytes2[1]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[2]=(d3!!.toInt()and 0xff).toByte()
                    bytes2[3]=  (d4!!.toInt()and 0xff).toByte()
                    bytes2[4]= (d5!!.toInt()and 0xff).toByte()
                    bytes2[5]= (d6!!.toInt() and 0xff).toByte()
                    bytes2[6]= (int.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_106,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.minimumPumpPressure=string
                        ShuJuMMkV.getInstances()?.putString(a.  MINIMUM_PUMP_PRESSURE,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var  onClickMinimumFlowValue=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i.toFloat()/10.toFloat()).toString() } L/min")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_12,"0")

                    bytes2[0]=(int.toInt()and 0xff).toByte()
                    bytes2[1]=(d2!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_107,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.minimumFlowValue=string
                        ShuJuMMkV.getInstances()?.putString(a.  INLETVALVE_11,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var  onClickMinimumTrafficDelayTime=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 256) {
            list.add("${(i.toFloat()).toString() } min")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_11,"0")

                    bytes2[0]=(d2!!.toInt()and 0xff).toByte()
                    bytes2[1]=(int.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_107,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.minimumTrafficDelayTime=string
                        ShuJuMMkV.getInstances()?.putString(a.  INLETVALVE_12,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }

    var  onClickCoalCompensation=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        var lists= mutableListOf<Int>()
        for (i in -127 until  0) {
            list.add("${(i.toFloat()/10.toFloat()).toString() } °C")
            lists.add(i)
        }
        for (i in 0 until 128) {
            lists.add(i)
            list.add("${(i.toFloat()/10.toFloat()).toString() } °C")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0=    ShuJuMMkV.getInstances()?.getString(a.PID_P,"100")
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.PID_I,"1")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.PID_D,"10")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.COAL_RETURN_COMPENSATION,"0")
                    bytes2[0]= (d0!!.toInt() and 0xff).toByte()
                    bytes2[1]= (d1!!.toInt() and 0xff).toByte()
                    bytes2[2]= (d2!!.toInt() and 0xff).toByte()

                    bytes2[3]= (    lists[int]!!.toInt() and 0xff).toByte()
                    bytes2[4]= (d4!!.toInt() and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_108,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.coalCompensation=string
                        ShuJuMMkV.getInstances()?.putString(a.COAL_COMPENSATION,      lists[int].toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }

    var  onClickCoalReturnCompensation=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        var lists= mutableListOf<Int>()
        for (i in -127 until  0) {
            list.add("${(i.toFloat()/10.toFloat()).toString() } °C")
            lists.add(i)
        }
        for (i in 0 until 128) {
            lists.add(i)
            list.add("${(i.toFloat()/10.toFloat()).toString() } °C")
        }
        basePopupView=XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0=    ShuJuMMkV.getInstances()?.getString(a.PID_P,"100")
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.PID_I,"1")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.PID_D,"10")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.COAL_COMPENSATION,"0")
                    bytes2[0]= (d0!!.toInt() and 0xff).toByte()
                    bytes2[1]= (d1!!.toInt() and 0xff).toByte()
                    bytes2[2]= (d2!!.toInt() and 0xff).toByte()
                    bytes2[3]= (d3!!.toInt() and 0xff).toByte()
                    bytes2[4]= (lists[int]!!.toInt() and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_108,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        engineerIngBean.coalReturnCompensation=string
                        ShuJuMMkV.getInstances()?.putString(a.COAL_RETURN_COMPENSATION,  lists[int].toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }

}