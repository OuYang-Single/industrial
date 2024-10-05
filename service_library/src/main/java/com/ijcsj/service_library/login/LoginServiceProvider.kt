package com.ijcsj.service_library.login

import com.alibaba.android.arouter.launcher.ARouter
import com.ijcsj.service_library.bean.ApifoxModel

object LoginServiceProvider {
    private val iLoginService =
        ARouter.getInstance().build("/login/service").navigation() as? ILoginService

    fun login(userData: ApifoxModel) {
        iLoginService?.login(userData)
    }


    fun logout() {
        iLoginService?.logout()
    }


    fun isLogin(): Boolean {
        return iLoginService?.isLogin() == true
    }

    fun getUserProfile(): ApifoxModel? {
        return iLoginService?.getUserProfile()
    }

    fun getBoardingPass(): String? {
        return iLoginService?.getBoardingPass()
    }
}