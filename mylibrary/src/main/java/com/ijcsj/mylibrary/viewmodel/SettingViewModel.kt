package com.ijcsj.mylibrary.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.DeviceBean
import com.ijcsj.common_library.bean.IPage
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.Constant
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.mylibrary.adapter.DeviceAdapter
import com.ijcsj.mylibrary.model.DeviceModel
import com.ijcsj.mylibrary.model.SettingModel
import com.ijcsj.service_library.bean.ApifoxModel
import com.ijcsj.service_library.login.LoginServiceProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SettingViewModel (override val model: SettingModel) : MvmBaseViewModel<IBaseView, SettingModel>(){


    public override fun initModel() {

    }
    var onClickDownloadCourseInfo=  BindingCommand<BindingAction>{


    }
    public fun getDeviceList(){

    }

    fun sbSettingSizeSystem(): Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.SB_SETTING_SIZE_SYSTEM) == true
    }

    fun setSbSettingSizeSystem(boolean: Boolean) {
          ShuJuMMkV.instance?.putBoolean(Constant.SB_SETTING_SIZE_SYSTEM,boolean)

    }

    fun sbSettingCustomizedPlayback(): Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.SB_SETTING_CUSTOMIZED_PLAYBACK) == true
    }

    fun setSbSettingCustomizedPlayback(checked: Boolean) {
        ShuJuMMkV.instance?.putBoolean(Constant.SB_SETTING_CUSTOMIZED_PLAYBACK,checked)
    }

    fun sbSettingLandscapeScreenPlayback(): Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.SB_SETTING_LANDSCAPE_SCREEN_PLAYBACK) == true
    }

    fun setSbSettingLandscapeScreenPlayback(checked: Boolean) {
        ShuJuMMkV.instance?.putBoolean(Constant.SB_SETTING_LANDSCAPE_SCREEN_PLAYBACK,checked)
    }


    fun sbSettingInduction(): Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.SB_SETTING_INDUCTION) == true
    }

    fun setSbSettingInduction(checked: Boolean) {
        ShuJuMMkV.instance?.putBoolean(Constant.SB_SETTING_INDUCTION,checked)
    }

    fun sbSettingBarrageMemory(): Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.SB_SETTING_BARRAGE_MEMORY) == true
    }

    fun setSbSettingBarrageMemory(checked: Boolean) {
        ShuJuMMkV.instance?.putBoolean(Constant.SB_SETTING_BARRAGE_MEMORY,checked)
    }

    fun sbSettingBarrageOperation() : Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.SB_SETTING_BARRAGE_OPERATION) == true
    }

    fun setSbSettingBarrageOperation(checked: Boolean) {
        ShuJuMMkV.instance?.putBoolean(Constant.SB_SETTING_BARRAGE_OPERATION,checked)
    }

    fun sbSettingSmallScreen(): Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.sb_setting_small_screen) == true
    }

    fun setSbSettingSmallScreen(checked: Boolean) {
        ShuJuMMkV.instance?.putBoolean(Constant.sb_setting_small_screen,checked)
    }
}