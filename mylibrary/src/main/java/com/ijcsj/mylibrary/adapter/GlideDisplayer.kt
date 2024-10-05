package com.ijcsj.mylibrary.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ijcsj.common_library.bean.FileUpload
import com.ijcsj.common_library.util.CornerTransform
import com.ijcsj.ui_library.anko.dp
import com.ijcsj.ui_library.utils.ScreenUtils
import com.ijcsj.ui_library.widget.ngv.INgvImageLoader

class GlideDisplayer: INgvImageLoader<FileUpload>
{
    override fun load(source: FileUpload, imageView: ImageView, width: Int, height: Int) {

        var cornerTransform  =  CornerTransform(imageView.context, 4.dp.toFloat());
        cornerTransform.setNeedCorner(true, true, true, true);
        var options =  RequestOptions()
            .transform(cornerTransform);
        Glide.with(imageView.context)
            .load(source.fileUrl)
            .apply( options)
            .into(imageView)
    }
}
