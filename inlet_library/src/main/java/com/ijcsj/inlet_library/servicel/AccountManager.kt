package com.ijcsj.inlet_library.servicel

import android.text.TextUtils
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.util.Constant.ABOUT_DATA
import com.ijcsj.common_library.util.Constant.HELPER_URL
import com.ijcsj.service_library.bean.ApifoxModel

object AccountManager {
    private val lock = Any()
    private var userProfile: ApifoxModel? = null
    private var boardingPass: String? = null
    private const val KEY_BOARDING_PASS = "boarding_pass"
    @Volatile
    private var isFetching = false

    init {
        val  local= (ShuJuMMkV.instance?.getObjectData(ApifoxModel::class.java));
        if (userProfile == null && local != null) {
            userProfile = local
        }
    }

    fun login(userData: ApifoxModel) {
        ShuJuMMkV.instance?.putAny(ApifoxModel::class.java.name,userData);
        loginSuccess(userData.token.toString())
    }

    internal fun loginSuccess(boardingPass: String) {
        ShuJuMMkV.instance?.putString(KEY_BOARDING_PASS, boardingPass)
        AccountManager.boardingPass = boardingPass

    }

    fun getBoardingPass(): String? {
        if (TextUtils.isEmpty(boardingPass)) {
            boardingPass =   ShuJuMMkV.instance?.getString(KEY_BOARDING_PASS)
        }
        return boardingPass
    }

    fun isLogin(): Boolean {
        return getUserProfile() !=null
    }


    @Synchronized
    fun getUserProfile() : ApifoxModel?{
        if (userProfile !=null){
            return userProfile
        }
        val  local=  (ShuJuMMkV.instance?.getObjectData(ApifoxModel::class.java));
        if ( local != null) {
            userProfile = local
        }
       return userProfile
    }


    fun logout() {
        userProfile =null;
        boardingPass =null;
        ShuJuMMkV.instance?.putString(KEY_BOARDING_PASS, null)
        ShuJuMMkV.instance?.putString(HELPER_URL, null)
        ShuJuMMkV.instance?.putString(ABOUT_DATA, null)
        ShuJuMMkV.instance?.putString(ApifoxModel::class.java.name, null)
    }
}