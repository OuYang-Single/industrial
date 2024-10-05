package com.ijcsj.inlet_library.api


import androidx.databinding.ObservableArrayList
import com.ijcsj.common_library.bean.AboutBean
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.DeviceBean
import com.ijcsj.common_library.bean.FaceInit
import com.ijcsj.common_library.bean.HelpBean
import com.ijcsj.common_library.bean.RealStatus
import com.ijcsj.inlet_library.base.TximBase
import com.ijcsj.inlet_library.base.TximUser
import retrofit2.http.*


/**
 */
interface RequestService {
    @POST("/sys/sysConfig/aboutAppToApp")
    suspend fun getAbout(): ApiResult<AboutBean>

    @POST("/sys/softwareVersion/upgradeInfo")
    suspend fun getUpgradeInfo(@Body map: Map<String, String>): ApiResult<String>
   /* {"appid":1400600351,"secretKey":"b55ad9df653ad2ee838aff3cc4cc1bf0114bcdebdd40b0f3cca65a6075f956ad"}*/
    @GET("/three/txim/init")
    suspend fun tximInit(): ApiResult<TximBase>
    @GET("/sys/user/getUserSig")
    suspend fun getUserSig(): ApiResult<TximUser>

    @POST("/biz/userRealname/getFaceSdkSign")
    suspend fun getFaceSdkSign(@Body map: Map<String, Int>): ApiResult<FaceInit>

    @GET("/biz/userRealname/getRealStatus")
    suspend fun getRealStatus(): ApiResult<RealStatus>
}