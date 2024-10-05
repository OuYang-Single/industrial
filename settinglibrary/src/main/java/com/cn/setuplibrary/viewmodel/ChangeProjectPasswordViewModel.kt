package com.cn.setuplibrary.viewmodel

import android.app.Activity
import android.text.TextUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.cn.setuplibrary.adapter.StyleAdapter
import com.cn.setuplibrary.bean.PasswordBean
import com.cn.setuplibrary.model.ChangeProjectPasswordModel
import com.cn.setuplibrary.model.EngineeringParametersModel
import com.cn.setuplibrary.popup.StylePopup
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.Hexs
import com.ijcsj.common_library.util.a
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.lxj.xpopup.XPopup
import com.orhanobut.logger.Logger

class ChangeProjectPasswordViewModel  (override val model: ChangeProjectPasswordModel,var password: PasswordBean) : MvmBaseViewModel<IBaseView, ChangeProjectPasswordModel>(){
    public override fun initModel() {

    }
    var onClickStartMode =  BindingCommand<BindingAction>{
        if (TextUtils.isEmpty(password.password)){
            _message.postValue("密码不能为空")
            return@BindingCommand
        }
        if (password.passwordConfirm==password.password){
            ShuJuMMkV.getInstances()?.putString(a.ENGINEERING_LOG_ON_PASSWORD,password.passwordConfirm)
            ShuJuMMkV.getInstances()?.putBoolean(a.ENGINEERING_LOG_ON,false)
            _message.postValue("密码修改成功")
            password.passwordConfirm=""
            password.password=""
        }else{
          _message.postValue("两次密码不一致，请重试")
        }
    }
    var onClickStartModed =  BindingCommand<BindingAction>{
        if (TextUtils.isEmpty(password.password)){
            _message.postValue("密码不能为空")
            return@BindingCommand
        }
        if (password.passwordConfirm==password.password){
            ShuJuMMkV.getInstances()?.putString(a.MANUFACTOR_LOG_ON_PASSWORD,password.passwordConfirm)
            ShuJuMMkV.getInstances()?.putBoolean(a.MANUFACTOR_LOG_ON,false)
            _message.postValue("密码修改成功")
            password.passwordConfirm=""
            password.password=""
        }else{
            _message.postValue("两次密码不一致，请重试")
        }
    }
}