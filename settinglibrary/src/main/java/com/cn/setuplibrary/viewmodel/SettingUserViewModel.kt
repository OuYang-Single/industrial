package com.cn.setuplibrary.viewmodel

import android.content.Context
import android.text.TextUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.cn.setuplibrary.adapter.StyleAdapter
import com.cn.setuplibrary.bean.PasswordBean
import com.cn.setuplibrary.bean.SettingUserBean
import com.cn.setuplibrary.model.SettingUserModel
import com.cn.setuplibrary.popup.StylePopup
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.BacklightController
import com.ijcsj.common_library.util.Hexs
import com.ijcsj.common_library.util.a
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.orhanobut.logger.Logger

class SettingUserViewModel  (override val model: SettingUserModel,var settingUserBean: SettingUserBean,var password: PasswordBean) : MvmBaseViewModel<IBaseView, SettingUserModel>(){
    var basePopupView: BasePopupView?=null

    public override fun initModel() {
     var type=   ShuJuMMkV.getInstances()?.getString(a.WORKING_MODE, 0.toString())
        when(type){
            4.toString()->{
                settingUserBean.startMode= "本地启动"
                settingUserBean.burial="关"
                settingUserBean.drain="关"
            }
            2.toString()->{
                settingUserBean.startMode= "本地启动"
                settingUserBean.burial="开"
                settingUserBean.drain="关"
            }
            3.toString()->{
                settingUserBean.startMode= "本地启动"
                settingUserBean.burial="开"
                settingUserBean.drain="关"
            }
            else->{
                settingUserBean.startMode= "本地启动"
                settingUserBean.burial="关"
                settingUserBean.drain="关"
            }
        }

    }

    var onStartModeClick=  BindingCommand<BindingAction>{
        var isUserLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.USER_LOG_ON,false)
        if (isUserLogOn==true){
            var list= mutableListOf<String>()
            list.add("远程启动")
            list.add("本地启动")
            basePopupView=XPopup.Builder(it.context)
                .enableDrag(false)
                .autoDismiss(false)
                .dismissOnTouchOutside(false)
                .positionByWindowCenter(true)
                .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                    StyleAdapter.OnClickListeners{
                    override fun onClick(string: String?,int: Int) {
                        var bytes2=ByteArray(8)
                        if (int==0){
                            bytes2[0]= a.from10To2sd(4)
                        }else{
                            bytes2[0]= a.from10To2sd(4)
                        }
                        ShuJuMMkV.getInstances()?.putString(a.WORKING_MODE,  bytes2[0].toString())
                        var d1=  ShuJuMMkV.getInstances()?.getString(a.SETTING_TEMPERATURE,"1200")
                        var d2=  ShuJuMMkV.getInstances()?.getString(a.FILLING_TIME,"4")
                        var d3=  ShuJuMMkV.getInstances()?.getString(a.PUMP_ON_TIME,"0")
                        var d4=  ShuJuMMkV.getInstances()?.getString(a.IP_ADDRESS,"0")
                        var d5=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TEMPERATURE,"60")
                        var d6=  ShuJuMMkV.getInstances()?.getString(a.DELAY_PUMP_START_TIME,"10")
                        var d11=  Hexs.  hex2LowHighByte(d1!!.toLong())
                        bytes2[1]=d11[0]
                        bytes2[2]=d11[1]
                        bytes2[3]=  (d2!!.toInt()and 0xff).toByte()
                        bytes2[4]= (d3!!.toInt()and 0xff).toByte()
                        bytes2[5]= (d4!!.toInt() and 0xff).toByte()
                        bytes2[6]= (d5!!.toInt()and 0xff).toByte()
                        bytes2[7]= (d6!!.toInt() and 0xff ).toByte()
                        var ta=  Socketcan.CanWrite(Socketcan.fd,Socketcan.CAN_103,bytes2)
                        Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                        if (ta>0){
                            ShuJuMMkV.getInstances()?.putString(a.WORKING_MODE,  bytes2[0].toString())
                            settingUserBean.startMode=string
                            settingUserBean.burial="关"
                            settingUserBean.drain="关"
                        }else{
                            _message.postValue("发送失败，请重试")
                        }
                        basePopupView?.dismiss()
                    }
                }))).show()
        }else{
            logOn(it.context)
        }
    }
    var onBurialClick=  BindingCommand<BindingAction>{
        var isUserLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.USER_LOG_ON,false)
        if (isUserLogOn==true){
            var list= mutableListOf<String>()
            list.add("开")
            list.add("关")
            basePopupView=XPopup.Builder(it.context)
                .enableDrag(false)
                .autoDismiss(false)
                .dismissOnTouchOutside(false)
                .positionByWindowCenter(true)
                .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                    StyleAdapter.OnClickListeners{
                    override fun onClick(string: String?,int: Int) {
                        var bytes2=ByteArray(8)
                        if (int==0){
                            bytes2[0]= a.from10To2sd(2)
                        }else{
                            if (settingUserBean.burial=="关"){
                                bytes2[0]=  a.from10To2sd( ShuJuMMkV.getInstances()?.getString(a.WORKING_MODE, 5.toString())!!.toInt())
                            }else{
                                bytes2[0]= a.from10To2sd(5)
                            }
                        }

                        var d1=  ShuJuMMkV.getInstances()?.getString(a.SETTING_TEMPERATURE,"1200")
                        var d2=  ShuJuMMkV.getInstances()?.getString(a.FILLING_TIME,"4")
                        var d3=  ShuJuMMkV.getInstances()?.getString(a.PUMP_ON_TIME,"0")
                        var d4=  ShuJuMMkV.getInstances()?.getString(a.IP_ADDRESS,"0")
                        var d5=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TEMPERATURE,"60")
                        var d6=  ShuJuMMkV.getInstances()?.getString(a.DELAY_PUMP_START_TIME,"10")
                        var d11=  Hexs.  hex2LowHighByte(d1!!.toLong())
                        bytes2[1]=d11[0]
                        bytes2[2]=d11[1]
                        bytes2[3]=  (d2!!.toInt()and 0xff).toByte()
                        bytes2[4]= (d3!!.toInt()and 0xff).toByte()
                        bytes2[5]= (d4!!.toInt() and 0xff).toByte()
                        bytes2[6]= (d5!!.toInt()and 0xff).toByte()
                        bytes2[7]= (d6!!.toInt() and 0xff ).toByte()
                        var ta=  Socketcan.CanWrite(Socketcan.fd,Socketcan.CAN_103,bytes2)
                        Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                        if (ta>0){
                            if (settingUserBean.burial=="关"){

                            }else{
                                ShuJuMMkV.getInstances()?.putString(a.WORKING_MODE,  bytes2[0].toString())
                            }
                            settingUserBean.startMode=""
                            settingUserBean.burial=string
                            settingUserBean.drain="关"
                        }else{
                            _message.postValue("发送失败，请重试")
                        }
                        basePopupView?.dismiss()
                    }
                }))).show()
        }else{
            logOn(it.context)
        }
    }
    var onForcedClick=  BindingCommand<BindingAction>{
        var isUserLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.USER_LOG_ON,false)
        if (isUserLogOn==true){
            var list= mutableListOf<String>()
            list.add("开")
            list.add("关")
            basePopupView=XPopup.Builder(it.context)
                .enableDrag(false)
                .autoDismiss(false)
                .dismissOnTouchOutside(false)
                .positionByWindowCenter(true)
                .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                    StyleAdapter.OnClickListeners{
                    override fun onClick(string: String?,int: Int) {
                        var bytes2=ByteArray(8)
                        if (int==0){
                            bytes2[0]= a.from10To2sd(3)
                        }else{

                            if (settingUserBean.drain=="关"){
                                bytes2[0]=  a.from10To2sd( ShuJuMMkV.getInstances()?.getString(a.WORKING_MODE, 5.toString())!!.toInt())
                            }else{
                                bytes2[0]= a.from10To2sd(5)
                            }
                        }
                        ShuJuMMkV.getInstances()?.putString(a.WORKING_MODE,  bytes2[0].toString())
                        var d1=  ShuJuMMkV.getInstances()?.getString(a.SETTING_TEMPERATURE,"1200")
                        var d2=  ShuJuMMkV.getInstances()?.getString(a.FILLING_TIME,"4")
                        var d3=  ShuJuMMkV.getInstances()?.getString(a.PUMP_ON_TIME,"0")
                        var d4=  ShuJuMMkV.getInstances()?.getString(a.IP_ADDRESS,"0")
                        var d5=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TEMPERATURE,"60")
                        var d6=  ShuJuMMkV.getInstances()?.getString(a.DELAY_PUMP_START_TIME,"10")
                        var d11=  Hexs.  hex2LowHighByte(d1!!.toLong())
                        bytes2[1]=d11[0]
                        bytes2[2]=d11[1]
                        bytes2[3]=  (d2!!.toInt()and 0xff).toByte()
                        bytes2[4]= (d3!!.toInt()and 0xff).toByte()
                        bytes2[5]= (d4!!.toInt() and 0xff).toByte()
                        bytes2[6]= (d5!!.toInt()and 0xff).toByte()
                        bytes2[7]= (d6!!.toInt() and 0xff ).toByte()
                        var ta=  Socketcan.CanWrite(Socketcan.fd,Socketcan.CAN_103,bytes2)
                        Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
                        if (ta>0){
                            if (settingUserBean.drain=="关"){
                              //  bytes2[0]=  a.from10To2sd( ShuJuMMkV.getInstances()?.getString(a.WORKING_MODE, 5.toString())!!.toInt())
                            }else{
                                ShuJuMMkV.getInstances()?.putString(a.WORKING_MODE,  bytes2[0].toString())
                            }

                            settingUserBean.startMode=""
                            settingUserBean.burial="关"
                            settingUserBean.drain=string
                        }else{
                            _message.postValue("发送失败，请重试")
                        }
                        basePopupView?.dismiss()
                    }
                }))).show()
        }else{
            logOn(it.context)
        }
    }

    var onTimeClick=  BindingCommand<BindingAction>{
        var isUserLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.USER_LOG_ON,false)
        if (isUserLogOn==true){
            var list= mutableListOf<String>()
            list.add("15秒")
            list.add("30秒")
            list.add("1分钟")
            list.add("2分钟")
            list.add("5分钟")
            list.add("10分钟")
            list.add("30分钟")
            list.add("永不休眠")
            basePopupView=XPopup.Builder(it.context)
                .enableDrag(false)
                .autoDismiss(false)
                .dismissOnTouchOutside(false)
                .positionByWindowCenter(true)
                .asCustom(StylePopup(it.context,list, StyleAdapter(object :
                    StyleAdapter.OnClickListeners{
                    override fun onClick(string: String?,int: Int) {
                        var time=when(int){
                            0->{15000}
                            1->{30000}
                            2->{60000}
                            3->{120000}
                            4->{300000}
                            5->{600000}
                            6->{1800000}
                            else->{
                                2147483647
                            }
                        }
                        BacklightController.setScreenOffTimeout(it.context.contentResolver,time)
                        initTimeOut(BacklightController.getScreenOffTimeout(it.context.contentResolver))
                        basePopupView?.dismiss()
                    }
                }))).show()
        }else{
            logOn(it.context)
        }
    }
    var onPasswordClick=  BindingCommand<BindingAction>{
        var isUserLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.USER_LOG_ON,false)
        if (isUserLogOn==true){
            settingUserBean.isPassword=true;
        }else{
            logOn(it.context)
        }
    }
    fun logOn(context:Context){
        ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",0).navigation()
    }

    fun initTimeOut(screenOffTimeout: Int) {
       var timeOut= when(screenOffTimeout){
            15000->{"15秒"}
            30000->{"30秒"}
            60000->{"1分钟"}
            120000->{"2分钟"}
            300000->{"5分钟"}
            600000->{"10分钟"}
            1800000->{"30分钟"}
            else->{
                "永不休眠"
            }
        }
        settingUserBean.timeOut=timeOut
    }


    var onClickStartMode =  BindingCommand<BindingAction>{
        if (TextUtils.isEmpty(password.password)){
            _message.postValue("密码不能为空")
            return@BindingCommand
        }
        if (password.passwordConfirm==password.password){
            ShuJuMMkV.getInstances()?.putString(a.USER_LOG_ON_PASSWORD,password.passwordConfirm)
            ShuJuMMkV.getInstances()?.putBoolean(a.USER_LOG_ON,false)
            _message.postValue("密码修改成功")
            password.passwordConfirm=""
            password.password=""
            settingUserBean.isPassword=false;
        }else{
            _message.postValue("两次密码不一致，请重试")
        }
    }
}