package com.ijcsj.mylibrary.model

import android.os.Build
import androidx.databinding.ObservableArrayList
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.DeviceBean
import com.ijcsj.common_library.bean.IPage
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.common_library.util.DeviceInfoUtils
import com.ijcsj.mylibrary.api.ApiRepository
import com.ijcsj.ui_library.utils.AppGlobals

class DeviceModel (private val repository: ApiRepository) : BaseModel() {
    suspend   fun getDeviceList(userId: String,page:Int): ApiResult<IPage<ObservableArrayList<DeviceBean>>>? {
        return   repository.getDeviceList(userId,page);
    }

    fun getDevice(): DeviceBean {
      var deviceBean=  DeviceBean()
        deviceBean.deviceBrand= DeviceInfoUtils.getDeviceBrand()
        deviceBean.deviceModel= DeviceInfoUtils.getDeviceModel()
        deviceBean.deviceModelName= Build.MODEL;
        deviceBean.deviceName=android.os.Build.MODEL;
        deviceBean.deviceServerUuid= DeviceInfoUtils.getUniqueId(AppGlobals.get());
        deviceBean.deviceStatus=0
        deviceBean.deviceStatusBtnText=""
        deviceBean.deviceSystemName=   DeviceInfoUtils.getSystemVersion()
        deviceBean.deviceSystemVersionName= android.os.Build.VERSION.SDK_INT.toString();
        deviceBean.lasterLoginIp= DeviceInfoUtils.getMobileIPAddress(AppGlobals.get())
        deviceBean.lasterLoginLocation=""
        deviceBean.lasterLoginIp= DeviceInfoUtils.getMobileIPAddress(AppGlobals.get())
        deviceBean.lasterLoginTime= DeviceInfoUtils.getTime()
      return deviceBean
    }


}