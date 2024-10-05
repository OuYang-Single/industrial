package com.ijcsj.login_library.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginParam(
    var captchaKey: String? = null,
    var loginKey: String? = null,
    var password: String? = null,
    var username: String? = null
) : Parcelable