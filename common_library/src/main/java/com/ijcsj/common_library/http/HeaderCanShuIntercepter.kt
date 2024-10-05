package com.ijcsj.common_library.http

import android.annotation.SuppressLint
import android.text.TextUtils
import com.ijcsj.common_library.util.StringGenerator
import com.ijcsj.service_library.login.LoginServiceProvider
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderCanShuIntercepter : Interceptor {
    @SuppressLint("SuspiciousIndentation")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val baseUrl = request.header("base_url")
        if (baseUrl.isNullOrEmpty()) {
            val ts=System.currentTimeMillis().toString()
            val ct="cv2gQJbmSHybY4dr"
            val st="cZYq5KgX41O3o0ZK"
            val rs= StringGenerator.generateRandomString(16, 32)
            val ri=  MD5(ts,rs ,ct ,st,rs)
            //
          val logDengLuInPingZeng = LoginServiceProvider.getBoardingPass()
            return if (TextUtils.isEmpty(logDengLuInPingZeng)){
                 chain.proceed(
                    request.newBuilder()
                        .addHeader("ts",ts)
                        .addHeader("ct", ct)
                        .addHeader("st", st)
                        .addHeader("rs", rs)
                        .addHeader("ri", ri)
                        .addHeader("vk", "cn5AbGRnoCL3geniKr54cZfidHX9NDcl")
                        .build())
            }else{
                 chain.proceed(
                    request.newBuilder()
                        .addHeader("ts",ts)
                        .addHeader("ct", ct)
                        .addHeader("st", st)
                        .addHeader("rs", rs)
                        .addHeader("ri", ri)
                        .addHeader("tk", logDengLuInPingZeng!!)
                        .addHeader("vk", "cn5AbGRnoCL3geniKr54cZfidHX9NDcl")
                        .build())
            }
        } else {
            val newBaseUrl = baseUrl.toHttpUrl()
            return chain.proceed(
                request.newBuilder()
                    .removeHeader("base_url")
                    .url(newBaseUrl)
                    .build()
            )
        }
    }

   fun MD5( s1:String, s2:String, s3:String, s4:String, s5:String):String{
     return  StringGenerator.md5(s1+s2+s3+s4+s5);
   }


}