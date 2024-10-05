package com.ijcsj.service_library.login

import com.ijcsj.service_library.bean.ApifoxModel

interface ILoginService {
    //设置登录数据并发送事件
    fun login( userData: ApifoxModel)
   //当前是否登录
    fun isLogin():Boolean


    /**
     * 退出登录，并发送事件
     */
    fun logout()
    /**
     * 获取登录数据
     */
    fun getUserProfile(): ApifoxModel?

    //获取token
    fun getBoardingPass(): String?
}