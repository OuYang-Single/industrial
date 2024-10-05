package com.ijcsj.login_library.api


import com.ijcsj.common_library.bean.AboutBean
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.DeviceInit
import com.ijcsj.common_library.bean.HelpBean
import com.ijcsj.common_library.http.IRepository
import com.ijcsj.login_library.bean.EncryptionData
import com.ijcsj.login_library.bean.LoginParam
import com.ijcsj.login_library.bean.LoginTranslate
import com.ijcsj.service_library.bean.ApifoxModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @description:
 * @author:  79120
 * @date :   2021/11/22 9:27
 */
class ApiRepository(private val apiService: RequestService) : IRepository {
    suspend fun sendCaptcha(phone: String): ApiResult<String> {
        val map = hashMapOf("phone" to phone)
        return withContext(Dispatchers.IO) {
            apiService.sendCaptcha(map)
        }
    }

    suspend fun loginTranslate(loginParam: LoginTranslate): ApiResult<ApifoxModel> {
        return withContext(Dispatchers.IO) {
            apiService.loginTranslate(loginParam)
        }
    }
    suspend fun loginPassword(loginParam: LoginParam): ApiResult<ApifoxModel> {
        return withContext(Dispatchers.IO) {
            apiService.loginPassword(loginParam)
        }
    }
    suspend fun getPwdKey(name: String): ApiResult<EncryptionData> {
        return withContext(Dispatchers.IO) {
            apiService.getPwdKey(name)
        }
    }

    suspend fun bindDevice(name: DeviceInit): ApiResult<String> {
        return withContext(Dispatchers.IO) {
            apiService.bindDevice(name)
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

    /*


        suspend fun getAdSdk(packageID: String): ApiResult<AdSdk> {
            return withContext(Dispatchers.IO) {
                apiService.getAdSdk(packageID)
            }
        }*/
}