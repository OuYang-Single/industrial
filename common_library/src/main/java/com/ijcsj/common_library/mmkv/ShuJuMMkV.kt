package com.ijcsj.common_library.mmkv

import android.content.Context
import android.text.TextUtils
import com.google.gson.Gson
import com.tencent.mmkv.MMKV

class ShuJuMMkV {
    var gson: Gson? = null
    companion object {

        var instance: ShuJuMMkV? = null
        @JvmOverloads
        fun getInstances(): ShuJuMMkV? {
            if (instance == null) {
                synchronized(ShuJuMMkV::class.java) {
                    if (instance == null) {
                        instance = ShuJuMMkV()
                    }
                }
            }
            return instance
        }
    }

    fun initialize(context: Context) {
        MMKV.initialize(context)
    }
    private fun getShuJuMMkV(): MMKV {
        return  MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null)
    }

    //String类型---------------------------------------------------------------------------------
    fun getString(key: String?): String? {
        return getShuJuMMkV().getString(key, "")
    }

    fun getString(key: String?, defValue: String?): String? {
        return getShuJuMMkV().getString(key, defValue)
    }


    fun putString(key: String?, value: String?) {
        getShuJuMMkV().putString(key, value)
    }

    fun putAny(key: String, value: Any) {
        if (gson==null){
            gson=Gson()
        }
        getShuJuMMkV().putString(key, gson?.toJson(value))
    }

    fun <T : Any> getAny(key: String, classOfT: Class<T>): T? {
        if (gson == null) {
            gson = Gson()
        }
        val data = getShuJuMMkV().getString(key, "")
        return gson?.fromJson(data, classOfT)
    }

    /**
     * 读取数据
     * @param clazz 返回对象   User u = *.loadObjectData(User.class);
     * @return 序列化的对象
     */
    fun <T> getObjectData(clazz: Class<T>): T? {
        if (gson == null) {
            gson = Gson()
        }
        val json: String? = getShuJuMMkV().getString(clazz.name, "")
        return if (!TextUtils.isEmpty(json)) {
            return gson?.fromJson(json, clazz)
        } else {
            null
        }
    }

    fun getBoolean(key: String?): Boolean {
        return getShuJuMMkV().getBoolean(key, false)
    }

    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return getShuJuMMkV().getBoolean(key, defValue)
    }

    fun putBoolean(key: String?, value: Boolean) {
        getShuJuMMkV().putBoolean(key, value)
    }

}