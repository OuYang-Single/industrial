package com.ijcsj.login_library.api

import com.ijcsj.common_library.bean.AboutBean
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.DeviceInit
import com.ijcsj.common_library.bean.HelpBean
import com.ijcsj.login_library.bean.EncryptionData
import com.ijcsj.login_library.bean.LoginParam
import com.ijcsj.login_library.bean.LoginTranslate
import com.ijcsj.service_library.bean.ApifoxModel
import io.reactivex.Observable
import retrofit2.http.*


/**
 * @description:
 * @author:  79120
 * @date :   2021/11/19 9:07
 */
interface RequestService {
  /*  *//**
     * 登录
     *//*
    @POST("loginByAccount")
    suspend fun loginPassword(@Body map: LoginParam): ApiResult<UserData>



    @GET("tbAd/getAdSdk")
    suspend fun getAdSdk(  @Query("packageID") pageNum: String?,): ApiResult<AdSdk>*/

  /* 发送验证码*/
    @POST("/sys/loginPhoneSms")
    suspend fun sendCaptcha(@Body map: Map<String, String>): ApiResult<String>

   /*验证码登录*/
    @POST("/sys/loginPhone")
    suspend fun loginTranslate(@Body map: LoginTranslate): ApiResult<ApifoxModel>

    /*密码登录*/
    @POST("/sys/login")
    suspend fun loginPassword(@Body map: LoginParam): ApiResult<ApifoxModel>

    @GET("/sys/getPwdKey")
    suspend fun getPwdKey( @Query("userName") userName: String): ApiResult<EncryptionData>

    @POST("/biz/deviceInfo/bindDevice")
    suspend fun bindDevice( @Body userName: DeviceInit): ApiResult<String>

    @POST("/sys/sysConfig/getConfigByApp")
    suspend fun getHelpUrl(@Body map: Map<String, String>): ApiResult<HelpBean>
    @POST("/sys/sysConfig/aboutAppToApp")
    suspend fun getAbout(): ApiResult<AboutBean>


}