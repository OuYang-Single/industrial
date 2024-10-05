package com.ijcsj.common_library.bean

data class  IPage<T>(val current: Int, val hitCount: Boolean?,var pages:Int, val records: T?, var searchCount: Boolean, var size: Int ,var total: Int ) {
}