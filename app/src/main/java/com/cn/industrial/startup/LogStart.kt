package com.cn.industrial.startup

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.ijcsj.ui_library.BuildConfig
import com.lzy.ninegrid.NineGridView
import com.lzy.ninegrid.NineGridView.ImageLoader
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.rousetime.android_startup.AndroidStartup


class LogStart : AndroidStartup<String>() {
    override fun create(context: Context): String? {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true) // 是否显示线程信息，默认为ture
            .methodCount(1) // 显示的方法行数，默认为2
            .tag("ATHLETE") // 每个日志的全局标记。默认PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, @Nullable tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
        NineGridView.setImageLoader(PicassoImageLoader())

        /** Picasso 加载 */
        /** Picasso 加载  */

        Logger.w("LogStart");
        return LogStart::class.java.simpleName
    }

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = true
    class PicassoImageLoader : ImageLoader {
        override fun onDisplayImage(context: Context, imageView: ImageView, url: String) {
            Glide.with(context)
                .load(url)
                .into(imageView)
        }

        override fun getCacheImage(url: String): Bitmap? {
            return null
        }
    }

}