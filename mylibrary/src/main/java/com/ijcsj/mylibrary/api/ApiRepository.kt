package com.ijcsj.mylibrary.api

import androidx.databinding.ObservableArrayList
import com.google.gson.Gson
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
import com.ijcsj.common_library.http.IRepository
import com.ijcsj.mylibrary.bean.LittleGooseAbout
import com.ijcsj.mylibrary.bean.Retroaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

/**
 * @description:
 * @author:  79120
 * @date :   2021/11/22 9:27
 */
class ApiRepository(private val apiService: RequestService) : IRepository {

    suspend fun getDeviceList(userId: String,page:Int): ApiResult<IPage<ObservableArrayList<DeviceBean>>>{
        val map = hashMapOf("userId" to userId)
        map["order"]="ASC"
        map["page"]= page.toString()
        map["size"]="10"
        return withContext(Dispatchers.IO) {
            apiService.getDeviceList(map)
        }
    }

    suspend fun  uploadFile(compressPath: String):  ApiResult<FileUpload> {
        val requestFile: RequestBody =
            File(compressPath).asRequestBody("image/png".toMediaTypeOrNull())
        val body: MultipartBody.Part = MultipartBody.Part.createFormData("file", "", requestFile)
        return withContext(Dispatchers.IO) {
            apiService.uploadFile(body)
        }
    }
    suspend fun  uploadSfz(compressPath: String,type: Int):  ApiResult<CardInfo> {
        val requestFile: RequestBody =
            File(compressPath).asRequestBody("image/png".toMediaTypeOrNull())
        // 创建一个用于描述图片的RequestBody
        // 创建一个用于描述图片的RequestBody
        val descriptionBody = RequestBody.create("text/plain".toMediaTypeOrNull(), ""+type)

        val body: MultipartBody.Part = MultipartBody.Part.createFormData("file", "", requestFile)
        return withContext(Dispatchers.IO) {
            apiService.uploadSfz(body,type)
        }
    }
    suspend fun  submit(compressPath: Retroaction):  ApiResult<String> {
        return withContext(Dispatchers.IO) {
            apiService.submit(compressPath)
        }
    }

    suspend fun  getHelpUrl():  ApiResult<HelpBean> {
        val map = hashMapOf("typeCode" to "app_help_center")
        return withContext(Dispatchers.IO) {
            apiService.getHelpUrl(map)
        }
    }

    suspend fun  getAbout():  ApiResult<AboutBean> {
        return withContext(Dispatchers.IO) {
            apiService.getAbout()
        }
    }

    suspend fun  aboutNiudunToApp():  ApiResult<LittleGooseAbout> {
        return withContext(Dispatchers.IO) {
            apiService.aboutNiudunToApp()
        }
    }

    suspend fun  logOut():  ApiResult<String> {
        return withContext(Dispatchers.IO) {
            apiService.logOut()
        }
    }
    suspend fun  tximInit():  ApiResult<String> {
        return withContext(Dispatchers.IO) {
            apiService.tximInit()
        }
    }

    suspend fun  getIdentify(image:String,mGson:Gson): IdentifyEntity {
        val map: MutableMap<String, Any> = HashMap()
        val maps: Map<String, Any> = HashMap()

        val strings = arrayOfNulls<String>(1)
        strings[0] = "011"
        map["GroupIds"] = strings
        map["Image"] = image
        var map1: Map<String?, String?>? = null
        val data: String = mGson.toJson(map)
        val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
        val body = RequestBody.create(JSON, data)

        return withContext(Dispatchers.IO) {
            apiService.getIdentify(body,map1?.get("Authorization"),map1?.get("X-TC-Timestamp"),"SearchFaces")
        }
    }

    suspend fun  getFaceSdkSign(int: Int):  ApiResult<FaceInit> {
        val map = hashMapOf("bizType" to int)
        return withContext(Dispatchers.IO) {
            apiService.getFaceSdkSign(map)
        }
    }

    suspend fun  checkFaceCallback(orderNo: String):  ApiResult<Boolean> {
        val map = hashMapOf("orderNo" to orderNo)
        return withContext(Dispatchers.IO) {
            apiService.checkFaceCallback(map)
        }
    }
    suspend fun   getRealStatus(): ApiResult<RealStatus> {
        return withContext(Dispatchers.IO) {
            apiService.getRealStatus()
        }
    }
}