package com.ijcsj.common_library.bean

/**
 * @description:
 * @author:  79120
 * @date :   2022/8/29 15:59
 */
data class ApiResult<T>(val success: Boolean, var msg: String?, var code:Int, val data: T?, var ext: Any?) {

    constructor(msg: String?) : this(false, msg ?: "请求出错", 500, null,null)



    fun doOnSuccess(success: (String?, T?) -> Unit) {
        if (this.success && code == 200) {
            success(msg, data)
        }
    }

    fun doOnSuccessWithValue(success: (T) -> Unit) {
        if (this.success && data != null && code == 200) {
            success(data)
        }
    }

    fun doOnSuccessWithMsg(success: (String) -> Unit) {
        if (this.success && msg != null) {
            success(msg!!)
        }
    }

    fun doOnFailure(failure: (Int, String?) -> Unit) {
        if (!this.success||code!=200) {
            if(msg=="timeout"){
                msg="请求超时,请重试"
            }else if(msg=="Failed to connect to /111.180.206.54:2403"){
                msg="当前网络不可用"
            }
            failure(code, msg)
        }
    }

    fun <B> merge(apiResult: ApiResult<B>, setup: T.(B?) -> Unit):ApiResult<T> {
        data?.setup(apiResult.data)
        return this
    }
}