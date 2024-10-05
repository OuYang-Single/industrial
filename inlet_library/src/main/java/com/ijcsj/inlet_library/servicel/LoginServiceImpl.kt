package com.ijcsj.inlet_library.servicel


import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.template.IProvider
import com.ijcsj.service_library.bean.ApifoxModel
import com.ijcsj.service_library.login.ILoginService

@Route(path = "/login/service")
class LoginServiceImpl : ILoginService, IProvider {


    override fun login(userData: ApifoxModel) {
        AccountManager.login(userData)
    }

    override fun isLogin(): Boolean {
        return AccountManager.isLogin()
    }


    override fun logout() {
        AccountManager.logout()
    }

    override fun getUserProfile(): ApifoxModel? {
         return AccountManager.getUserProfile()
    }


    override fun getBoardingPass(): String? {
       return AccountManager.getBoardingPass()
    }

    override fun init(context: Context?) {

    }

}