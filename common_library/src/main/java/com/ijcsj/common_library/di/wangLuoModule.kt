package com.ijcsj.common_library.di

import android.content.Context
import com.ijcsj.common_library.BuildConfig
import com.ijcsj.common_library.http.HeaderCanShuIntercepter
import com.ijcsj.common_library.http.HttpInterceptor
import com.orhanobut.logger.Logger
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val wangLuModule = module {
    fun provideCommonOkHttpClient(context: Context?): OkHttpClient {
        return  RetrofitUrlManager.getInstance().with(OkHttpClient.Builder())
            .addNetworkInterceptor(HttpLoggingInterceptor { message ->
                Logger.d(message)
            }.apply {
                level = if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            })
            // When building OkHttpClient, the OkHttpClient.Builder() is passed to the with() method to initialize the configuration
            // When building OkHttpClient, the OkHttpClient.Builder() is passed to the with() method to initialize the configuration

            .addInterceptor(HttpInterceptor())
            .addInterceptor(HeaderCanShuIntercepter())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }


fun provideRetrofit(client: OkHttpClient): Retrofit {
    RetrofitUrlManager.getInstance().putDomain("identify", "https://iai.tencentcloudapi.com")
    return Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}
factory(named("common")) { provideCommonOkHttpClient(androidContext()) }

single { provideRetrofit(get(named("common"))) }


}

