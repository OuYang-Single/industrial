package com.ijcsj.common_library.http

import com.ijcsj.common_library.bean.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * @description:
 * @author:  79120
 * @date :   2021/11/25 16:42
 */
interface IRepository {

//    suspend fun <T> apiCall(apiCall: suspend () -> BaseEntity<T>): RequestResult<T> {
//        val response = apiCall()
//        return if (response.code != REQUEST_SUCCESS) {
//            RequestResult.Failure(response.code, response.msg)
//        } else {
//            RequestResult.Success(response.msg, response.result)
//        }
//    }
//
//    fun <T> BaseEntity<T>.convert(): RequestResult<T> {
//        return if (this.code != REQUEST_SUCCESS) {
//            RequestResult.Failure(this.code, this.msg)
//        } else {
//            RequestResult.Success(this.msg, this.result)
//        }
//    }

//    fun <T> ApiResult<ApiData<T>>.convert(): ApiData<T> {
//        return when (this) {
//            is ApiResult.Success -> {
//                this.data
//            }
//            is ApiResult.Failure -> {
//                ApiData(this.errorMsg)
//            }
//        }
//    }
}