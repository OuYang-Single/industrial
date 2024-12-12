package com.cn.setuplibrary.viewmodel

import com.alibaba.android.arouter.launcher.ARouter
import com.cn.setuplibrary.adapter.StyleAdapter
import com.cn.setuplibrary.bean.DebuggingSettingsBean
import com.cn.setuplibrary.model.ManufacturerDebuggingModel
import com.cn.setuplibrary.model.ManufacturerParametersModel
import com.cn.setuplibrary.popup.StylePopup
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.a
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.orhanobut.logger.Logger

class ManufacturerDebuggingViewModel (override val model: ManufacturerDebuggingModel,var bean: DebuggingSettingsBean) : MvmBaseViewModel<IBaseView, ManufacturerDebuggingModel>(){
    var basePopupView: BasePopupView?=null;
    public override fun initModel() {
        var d1=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_1,"0")
        if (d1?.toInt()==1){
            bean.inletValve="开启"
        }else{
            bean.inletValve="关闭"
        }
        var d2=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_2,"0")
        if (d2?.toInt()==1){
            bean.waterOutletValve="开启"
        }else{
            bean.waterOutletValve="关闭"
        }
        var d3=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_3,"0")
        if (d3?.toInt()==1){
            bean.waterPumpValve="开启"
        }else{
            bean.waterPumpValve="关闭"
        }
        var d4=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_4,"0")
        if (d4?.toInt()==1){
            bean.waterReplenishmentPumpValve="开启"
        }else{
            bean.waterReplenishmentPumpValve="关闭"
        }
        var d5=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_5,"0")
        if (d5?.toInt()==1){
            bean.turnOnHeating="开启"
        }else{
            bean.turnOnHeating="关闭"
        }
        var d6=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_6,"0")
        if (d6?.toInt()==1){
            bean.blowAirOn="开启"
        }else{
            bean.blowAirOn="关闭"
        }
        var d7=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_7,"0")
        if (d7?.toInt()==1){
            bean.openTheColdEndValve="开启"
        }else{
            bean.openTheColdEndValve="关闭"
        }
        var d8=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_8,"0")
        bean.openTheColdEndProportionalValve=d8
        var d9=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_9,"0")
        bean.openTheProportionalValveAtTheHotEnd=d9
    }

    var   onClickInletValve=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.MANUFACTOR_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",2).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        list.add("关闭")
        list.add("开启")
        basePopupView= XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0=int
                    var d1=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_2,"0")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_3,"0")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_4,"0")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_5,"0")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_6,"0")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_7,"0")
                    var d7=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_8,"0")
                    var d8=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_9,"0")
                    var da1=  "0$d6$d5$d4$d3$d2$d1$d0".toInt(2)
                    bytes2[0]=(da1 and 0xff).toByte()
                    bytes2[1]=0x00
                    bytes2[2]=0x00
                    bytes2[3]= 0x00
                    bytes2[4]= 0x00
                    bytes2[5]= 0x00
                    bytes2[6]= (d7!!.toInt()and 0xff).toByte()
                    bytes2[7]= (d8!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd, Socketcan.CAN_104,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        bean.inletValve=string
                        ShuJuMMkV.getInstances()?.putString(a.INLETVALVE_1,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()

    }
    var   onClickWaterOutletValve=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.MANUFACTOR_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",2).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        list.add("关闭")
        list.add("开启")
        basePopupView= XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_1,"0")
                    var d1= int
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_3,"0")
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_4,"0")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_5,"0")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_6,"0")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_7,"0")
                    var d7=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_8,"0")
                    var d8=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_9,"0")
                    var da1=  "0$d6$d5$d4$d3$d2$d1$d0".toInt(2)
                    bytes2[0]=(da1 and 0xff).toByte()
                    bytes2[1]=0x00
                    bytes2[2]=0x00
                    bytes2[3]= 0x00
                    bytes2[4]= 0x00
                    bytes2[5]= 0x00
                    bytes2[6]= (d7!!.toInt()and 0xff).toByte()
                    bytes2[7]= (d8!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd, Socketcan.CAN_104,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        bean.waterOutletValve=string
                        ShuJuMMkV.getInstances()?.putString(a.INLETVALVE_2,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }

    var   onClickWaterPumpValve=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.MANUFACTOR_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",2).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        list.add("关闭")
        list.add("开启")
        basePopupView= XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_1,"0")
                    var d1= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_2,"0")
                    var d2=  int
                    var d3=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_4,"0")
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_5,"0")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_6,"0")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_7,"0")
                    var d7=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_8,"0")
                    var d8=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_9,"0")
                    var da1=  "0$d6$d5$d4$d3$d2$d1$d0".toInt(2)
                    bytes2[0]=(da1 and 0xff).toByte()
                    bytes2[1]=0x00
                    bytes2[2]=0x00
                    bytes2[3]= 0x00
                    bytes2[4]= 0x00
                    bytes2[5]= 0x00
                    bytes2[6]= (d7!!.toInt()and 0xff).toByte()
                    bytes2[7]= (d8!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd, Socketcan.CAN_104,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        bean.waterPumpValve=string
                        ShuJuMMkV.getInstances()?.putString(a.INLETVALVE_3,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var   onClickWaterReplenishmentPumpValve=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.MANUFACTOR_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",2).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        list.add("关闭")
        list.add("开启")
        basePopupView= XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_1,"0")
                    var d1= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_2,"0")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_3,"0")
                    var d3=  int
                    var d4=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_5,"0")
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_6,"0")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_7,"0")
                    var d7=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_8,"0")
                    var d8=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_9,"0")
                    var da1=  "0$d6$d5$d4$d3$d2$d1$d0".toInt(2)
                    bytes2[0]=(da1 and 0xff).toByte()
                    bytes2[1]=0x00
                    bytes2[2]=0x00
                    bytes2[3]= 0x00
                    bytes2[4]= 0x00
                    bytes2[5]= 0x00
                    bytes2[6]= (d7!!.toInt()and 0xff).toByte()
                    bytes2[7]= (d8!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd, Socketcan.CAN_104,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        bean.waterReplenishmentPumpValve=string
                        ShuJuMMkV.getInstances()?.putString(a.INLETVALVE_4,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var   onClickTurnOnHeating=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.MANUFACTOR_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",2).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        list.add("关闭")
        list.add("开启")
        basePopupView= XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_1,"0")
                    var d1= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_2,"0")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_3,"0")
                    var d3=   ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_4,"0")
                    var d4=  int
                    var d5=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_6,"0")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_7,"0")
                    var d7=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_8,"0")
                    var d8=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_9,"0")
                    var da1=  "0$d6$d5$d4$d3$d2$d1$d0".toInt(2)
                    bytes2[0]=(da1 and 0xff).toByte()
                    bytes2[1]=0x00
                    bytes2[2]=0x00
                    bytes2[3]= 0x00
                    bytes2[4]= 0x00
                    bytes2[5]= 0x00
                    bytes2[6]= (d7!!.toInt()and 0xff).toByte()
                    bytes2[7]= (d8!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd, Socketcan.CAN_104,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        bean.turnOnHeating=string
                        ShuJuMMkV.getInstances()?.putString(a.INLETVALVE_5,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var   onClickBlowAirOn=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.MANUFACTOR_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",2).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        list.add("关闭")
        list.add("开启")
        basePopupView= XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_1,"0")
                    var d1= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_2,"0")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_3,"0")
                    var d3=   ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_4,"0")
                    var d4=   ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_5,"0")
                    var d5=  int
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_7,"0")
                    var d7=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_8,"0")
                    var d8=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_9,"0")
                    var da1=  "0$d6$d5$d4$d3$d2$d1$d0".toInt(2)
                    bytes2[0]=(da1 and 0xff).toByte()
                    bytes2[1]=0x00
                    bytes2[2]=0x00
                    bytes2[3]= 0x00
                    bytes2[4]= 0x00
                    bytes2[5]= 0x00
                    bytes2[6]= (d7!!.toInt()and 0xff).toByte()
                    bytes2[7]= (d8!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd, Socketcan.CAN_104,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        bean.blowAirOn=string
                        ShuJuMMkV.getInstances()?.putString(a.INLETVALVE_6,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var   onClickOpenTheColdEndValve=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.MANUFACTOR_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",2).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        list.add("关闭")
        list.add("开启")
        basePopupView= XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_1,"0")
                    var d1= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_2,"0")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_3,"0")
                    var d3=   ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_4,"0")
                    var d4=   ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_5,"0")
                    var d5=   ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_6,"0")
                    var d6=  int
                    var d7=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_8,"0")
                    var d8=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_9,"0")
                    var da1=  "0$d6$d5$d4$d3$d2$d1$d0".toInt(2)
                    bytes2[0]=(da1 and 0xff).toByte()
                    bytes2[1]=0x00
                    bytes2[2]=0x00
                    bytes2[3]= 0x00
                    bytes2[4]= 0x00
                    bytes2[5]= 0x00
                    bytes2[6]= (d7!!.toInt()and 0xff).toByte()
                    bytes2[7]= (d8!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd, Socketcan.CAN_104,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        bean.openTheColdEndValve=string
                        ShuJuMMkV.getInstances()?.putString(a.INLETVALVE_7,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
    var   onClickOpenTheColdEndProportionalValve=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.MANUFACTOR_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",2).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        list.add("开启")
        list.add("关闭")
        basePopupView= XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_1,"0")
                    var d1= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_2,"0")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_3,"0")
                    var d3=   ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_4,"0")
                    var d4=   ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_5,"0")
                    var d5=   ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_6,"0")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_7,"0")

                    var d7=  if (int==0){
                        100
                    }else{
                        0
                    }
                    var d8=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_9,"0")
                    var da1=  "0$d6$d5$d4$d3$d2$d1$d0".toInt(2)
                    bytes2[0]=(da1 and 0xff).toByte()
                    bytes2[1]=0x00
                    bytes2[2]=0x00
                    bytes2[3]= 0x00
                    bytes2[4]= 0x00
                    bytes2[5]= 0x00
                    bytes2[6]= (d7!!.toInt()and 0xff).toByte()
                    bytes2[7]= (d8!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd, Socketcan.CAN_104,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        bean.openTheColdEndProportionalValve=string
                        ShuJuMMkV.getInstances()?.putString(a.INLETVALVE_8,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }

    var   onClickOpenTheProportionalValveAtTheHotEnd=  BindingCommand<BindingAction>{
        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.MANUFACTOR_LOG_ON,false)
        if (isEngineeringLogOn==false){
            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",2).navigation()
            return@BindingCommand
        }
        var list= mutableListOf<String>()
        for (i in 0 until 101) {
            list.add("$i ")
        }
        basePopupView= XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                StyleAdapter.OnClickListeners{
                override fun onClick(string: String?,int: Int) {
                    var bytes2=ByteArray(8)
                    var d0= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_1,"0")
                    var d1= ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_2,"0")
                    var d2=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_3,"0")
                    var d3=   ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_4,"0")
                    var d4=   ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_5,"0")
                    var d5=   ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_6,"0")
                    var d6=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_7,"0")
                    var d7=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_8,"0")
                    var d8= int
                    var da1=  "0$d6$d5$d4$d3$d2$d1$d0".toInt(2)
                    bytes2[0]=(da1 and 0xff).toByte()
                    bytes2[1]=0x00
                    bytes2[2]=0x00
                    bytes2[3]= 0x00
                    bytes2[4]= 0x00
                    bytes2[5]= 0x00
                    bytes2[6]= (d7!!.toInt()and 0xff).toByte()
                    bytes2[7]= (d8!!.toInt()and 0xff).toByte()

                    var ta=  Socketcan.CanWrites(Socketcan.fd, Socketcan.CAN_104,bytes2)
                    Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                    if (ta>0){
                        bean.openTheProportionalValveAtTheHotEnd=string
                        ShuJuMMkV.getInstances()?.putString(a.INLETVALVE_9,  int.toString())
                    }else{
                        _message.postValue("发送失败，请重试")
                    }
                    basePopupView?.dismiss()
                }
            }))).show()
    }
}