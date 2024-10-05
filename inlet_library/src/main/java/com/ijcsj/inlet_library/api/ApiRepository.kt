package com.ijcsj.inlet_library.api


import androidx.databinding.ObservableArrayList
import com.ijcsj.common_library.bean.AboutBean
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.DeviceBean
import com.ijcsj.common_library.bean.FaceInit
import com.ijcsj.common_library.bean.HelpBean
import com.ijcsj.common_library.bean.IPage
import com.ijcsj.common_library.bean.RealStatus
import com.ijcsj.common_library.http.IRepository
import com.ijcsj.inlet_library.base.TximBase
import com.ijcsj.inlet_library.base.TximUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @description:
 * @author:  79120
 * @date :   2021/11/22 9:27
 */
class ApiRepository(private val apiService: RequestService) : IRepository {

    suspend fun  getAbout():  ApiResult<AboutBean> {
        return withContext(Dispatchers.IO) {
            apiService.getAbout()
        }
    }

    suspend fun  getUpgradeInfo(versionKey:String):  ApiResult<String> {
        val map = hashMapOf("versionKey" to versionKey)

        return withContext(Dispatchers.IO) {
            apiService.getUpgradeInfo(map)
        }
    }
    suspend fun  tximInit():  ApiResult<TximBase> {
        return withContext(Dispatchers.IO) {
            apiService.tximInit()
        }
    }

    suspend fun  getUserSig():  ApiResult<TximUser> {
        return withContext(Dispatchers.IO) {
            apiService.getUserSig()
        }
    }
    suspend fun  getFaceSdkSign():  ApiResult<FaceInit> {
        val map = hashMapOf("bizType" to 1)
        return withContext(Dispatchers.IO) {
            apiService.getFaceSdkSign(map)
        }
    }
    suspend fun   getRealStatus(): ApiResult<RealStatus> {
        return withContext(Dispatchers.IO) {
            apiService.getRealStatus()
        }
    }
}