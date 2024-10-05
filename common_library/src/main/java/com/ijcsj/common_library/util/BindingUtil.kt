package com.ijcsj.common_library.util

import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import io.reactivex.functions.Consumer

import com.jakewharton.rxbinding3.view.*

import java.util.concurrent.TimeUnit
import com.jakewharton.rxbinding4.view.*

class BindingUtil {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["src_url"])
        public  fun ImageView.ScaleType(url: String) {

        }

    }

}