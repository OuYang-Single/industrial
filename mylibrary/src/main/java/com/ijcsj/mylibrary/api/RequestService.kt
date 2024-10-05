package com.ijcsj.mylibrary.api


import androidx.databinding.ObservableArrayList
import com.ijcsj.common_library.bean.AboutBean
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.CardInfo
import com.ijcsj.common_library.bean.DeviceBean
import com.ijcsj.common_library.bean.FaceInit
import com.ijcsj.common_library.bean.FileUpload
import com.ijcsj.common_library.bean.HelpBean
import com.ijcsj.common_library.bean.IPage
import com.ijcsj.common_library.bean.IdentifyEntity
import com.ijcsj.common_library.bean.RealStatus
import com.ijcsj.mylibrary.bean.LittleGooseAbout
import com.ijcsj.mylibrary.bean.Retroaction
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


/**
 */
interface RequestService {
    @POST("/biz/deviceInfo/listByMerchantAndStudentId")
    suspend fun getDeviceList(@Body map: Map<String, String>):ApiResult<IPage<ObservableArrayList<DeviceBean>>>
    @Multipart
    @POST("/sys/common/uploadV2")
    suspend fun uploadFile(  @Part file: MultipartBody.Part ): ApiResult<FileUpload>
    @Multipart
    @POST("/biz/userRealname/uploadSfz")
    suspend fun uploadSfz(  @Part file: MultipartBody.Part,@Query("type") type: Int  ): ApiResult<CardInfo>


    @POST("/biz/userAdvise/add")
    suspend fun submit(@Body compressPath: Retroaction): ApiResult<String>
   @POST("/sys/sysConfig/getConfigByApp")
    suspend fun getHelpUrl(@Body map: Map<String, String>): ApiResult<HelpBean>
    @POST("/sys/sysConfig/aboutAppToApp")
    suspend fun getAbout(): ApiResult<AboutBean>
    @POST("/sys/sysConfig/aboutNiudunToApp")
    suspend fun aboutNiudunToApp(): ApiResult<LittleGooseAbout>

    @POST("/sys/logout")
    suspend fun logOut(): ApiResult<String>
    @GET("/three/txim/init")
    suspend fun tximInit(): ApiResult<String>
    @Headers(
        "Domain-Name: identify",
        "Content-Type:application/json; charset=utf-8",
        "Host:iai.tencentcloudapi.com",
        "X-TC-Version:2018-03-01"
    ) // 加上 Domain-Name header
    @POST("/")
    suspend  fun getIdentify(
        @Body map: RequestBody?,
        @Header("Authorization") Authorization: String?,
        @Header("X-TC-Timestamp") timestamp: String?,
        @Header("X-TC-Action") Region: String?
    ): IdentifyEntity

    @POST("/biz/userRealname/getFaceSdkSign")
    suspend fun getFaceSdkSign(@Body map: Map<String, Int>): ApiResult<FaceInit>

   @POST("/biz/userRealname/checkFaceCallback")
    suspend fun checkFaceCallback(@Body map: Map<String, String>): ApiResult<Boolean>

    @GET("/biz/userRealname/getRealStatus")
    suspend fun getRealStatus(): ApiResult<RealStatus>
}