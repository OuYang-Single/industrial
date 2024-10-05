package com.ijcsj.login_library.model

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import com.ijcsj.common_library.bean.AboutBean
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.DeviceInit
import com.ijcsj.common_library.bean.HelpBean
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.common_library.util.DeviceInfoUtils
import com.ijcsj.common_library.util.SDCardUtils
import com.ijcsj.login_library.api.ApiRepository
import com.ijcsj.login_library.bean.EncryptionData
import com.ijcsj.login_library.bean.LoginParam
import com.ijcsj.login_library.bean.LoginTranslate
import com.ijcsj.service_library.bean.ApifoxModel
import com.ijcsj.ui_library.utils.AppGlobals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale





class LogInModel(private val repository: ApiRepository) : BaseModel() {


    suspend fun sendCaptcha(tel: String): ApiResult<String> {
        return   repository.sendCaptcha(tel);
    }
    suspend fun loginTranslate(apifoxModel: LoginTranslate): ApiResult<ApifoxModel> {
        return   repository.loginTranslate(apifoxModel);
    }

    suspend fun loginPassword(apifoxModel: LoginParam): ApiResult<ApifoxModel> {
        return   repository.loginPassword(apifoxModel);
    }

    suspend fun getPwdKey(name: String): ApiResult<EncryptionData> {
        return   repository.getPwdKey(name);
    }

    suspend fun bindDevice(deviceInit: DeviceInit): ApiResult<String> {
        return   repository.bindDevice(deviceInit);
    }
    suspend   fun getHelpUrl(): ApiResult<HelpBean> {
        return   repository.getHelpUrl();
    }
    suspend   fun getAbout(): ApiResult<AboutBean> {
        return   repository.getAbout();
    }
    @SuppressLint("MissingPermission", "NewApi", "HardwareIds")
    fun getDeviceInit(userId:Long,setLocation: String?) :DeviceInit{
      var deviceInit=  DeviceInit()
        deviceInit.deviceBios= DeviceInfoUtils.getDeviceBoard()
        deviceInit.deviceBrand=DeviceInfoUtils.getDeviceBrand()
        deviceInit.deviceCpu=DeviceInfoUtils.getCPUModel()
        deviceInit.deviceCpuSn=DeviceInfoUtils.getCpuSerialNumber(AppGlobals.get())
        deviceInit.deviceDiskSpace= SDCardUtils.getTotalInternalMemorySize(AppGlobals.get())
        deviceInit.deviceImei= DeviceInfoUtils.getIMEI(AppGlobals.get())
        deviceInit.deviceInnerVersion= DeviceInfoUtils.getDeviceAndroidVersion()
        deviceInit.deviceLanguage= DeviceInfoUtils.getDeviceDefaultLanguage()
        deviceInit.deviceMacAddress= DeviceInfoUtils.getCpuSerialNumber(AppGlobals.get())

        deviceInit.deviceMemory= DeviceInfoUtils.getCpuSerialNumber(AppGlobals.get())

        deviceInit.deviceMemorySpace=SDCardUtils.getTotalMemory(AppGlobals.get())
        deviceInit.deviceModel= DeviceInfoUtils.getDeviceModel()
        deviceInit.deviceModelName= Build.MODEL;
        deviceInit.deviceName=  android.os.Build.MODEL;

        deviceInit.deviceServerUuid=  DeviceInfoUtils.getUniqueId(AppGlobals.get());
        deviceInit.deviceSim= DeviceInfoUtils.getIMEI(AppGlobals.get())
        deviceInit.deviceSn=  DeviceInfoUtils.getDeviceSerialNumber();
        deviceInit.deviceStatus=  0
        deviceInit.deviceSystemInstallTime=   DeviceInfoUtils.getInstallTime(AppGlobals.get())
        deviceInit.deviceSystemName=   DeviceInfoUtils.getDeviceSerial()
        deviceInit.deviceSystemVersion=   DeviceInfoUtils.getSystemVersion()
        deviceInit.deviceSystemVersionName= android.os.Build.VERSION.SDK_INT.toString();
        deviceInit.deviceUuid= Settings.Secure.getString(AppGlobals.get()?.contentResolver, Settings.Secure.ANDROID_ID);
        deviceInit.lasterLoginIp= DeviceInfoUtils.getMobileIPAddress(AppGlobals.get())
        deviceInit.lasterLoginTime= DeviceInfoUtils.getTime()
        val current = Locale.getDefault()
        val countryCode = current.country
        deviceInit.loginTime= DeviceInfoUtils.getTime()
        deviceInit.locationCountry= countryCode
        deviceInit.loginIp= DeviceInfoUtils.getMobileIPAddress(AppGlobals.get())
        deviceInit.screenHeight= DeviceInfoUtils.getDeviceHeight(AppGlobals.get()).toString()
        deviceInit.screenWidth= DeviceInfoUtils.getDeviceWidth(AppGlobals.get()).toString()
        deviceInit.userId= userId
        deviceInit.id= userId
        deviceInit.lasterLoginLocation= setLocation
        deviceInit.loginLocation= setLocation

        if (deviceInit.lasterLoginIp==null){
            deviceInit.lasterLoginIp=DeviceInfoUtils.getWiFiIPAddress(AppGlobals.get())
        }
        if (deviceInit.loginIp==null){
            deviceInit.loginIp=DeviceInfoUtils.getWiFiIPAddress(AppGlobals.get())
        }
        return deviceInit
    }

}