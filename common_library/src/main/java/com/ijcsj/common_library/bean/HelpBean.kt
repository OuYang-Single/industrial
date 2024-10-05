package com.ijcsj.common_library.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data  class HelpBean(var helper_url:String): Parcelable, Serializable {
}