package com.ijcsj.mylibrary.model

import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.CardInfo
import com.ijcsj.common_library.bean.FaceInit
import com.ijcsj.common_library.bean.FileUpload
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.mylibrary.api.ApiRepository

class RealNameModel (private val repository: ApiRepository) : BaseModel() {

    suspend   fun uploadSfz(compressPath: String,type: Int): ApiResult<CardInfo> {
        return   repository.uploadSfz(compressPath,type);
    }

    suspend   fun getFaceSdkSign(int: Int):  ApiResult<FaceInit>  {
        return   repository.getFaceSdkSign(int);
    }
    suspend   fun checkFaceCallback(orderNo: String): ApiResult<Boolean> {
        return   repository.checkFaceCallback(orderNo);
    }

}