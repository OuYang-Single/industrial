package com.ijcsj.common_library.http

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.service_library.login.LoginServiceProvider
import com.ijcsj.ui_library.utils.AppGlobals
import okhttp3.*
import okio.Buffer
import okio.EOFException
import java.nio.charset.Charset


/**
 * @author : ChenYangQi
 * date   : 2021/1/10 23:50
 * desc   : 判断token过期并自动刷新,刷新成功后重试请求
 */
class HttpInterceptor : Interceptor {
    var  gson= Gson()
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        //判断Token是否过期
        try {
            val responseBody = response.body
            val source = responseBody!!.source()
            source.request(Long.MAX_VALUE)
            val buffer: Buffer = source.buffer
            val mediaType = responseBody!!.contentType()
            if (isPlaintext(buffer)) {
                val charset: Charset = Charset.forName("UTF-8")
                val result: String = buffer.clone().readString(mediaType!!.charset(charset)!!)
                //如果token超时或不存在，则重新获取
                val lists =gson.fromJson(result, ApiResult::class.java)
                if (lists.code==10100006||lists.code==10100003){
                  /*  LoginServiceProvider.logout()
                    ARouter.getInstance().build("/LogIn/LogInActivity").navigation(AppGlobals.get(),callback)*/
                }
            }
        } catch (e:Exception){

        }

        /* var newRequest: Request  =    chain.request().newBuilder()
             .build();*/
        return  response
    }

// 创建一个实现了 NavigationCallback 接口的监听器
   var callback: NavigationCallback = object: NavigationCallback{
    override fun onFound(postcard: Postcard?) {

    }

    override fun onLost(postcard: Postcard?) {

    }

    override fun onArrival(postcard: Postcard?) {
        LiveDataBus.get().with("logout", Boolean::class.java ).postValue(true)
    }

    override fun onInterrupt(postcard: Postcard?) {

    }

};


    @Throws(EOFException::class)
    fun isPlaintext(buffer: Buffer): Boolean {
        return try {
            val prefix = Buffer()
            val byteCount: Long = if (buffer.size < 64) buffer.size else 64
            buffer.copyTo(prefix, 0, byteCount)
            for (i in 0..15) {
                if (prefix.exhausted()) {
                    break
                }
                val codePoint: Int = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false
                }
            }
            true
        } catch (e: EOFException) {
            false // Truncated UTF-8 sequence.
        }
    }

}


