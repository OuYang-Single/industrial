package com.ijcsj.mylibrary.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ijcsj.common_library.bean.DeviceBean
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.util.DeviceInfoUtils
import com.ijcsj.common_library.viewadapter.view.ViewAdapter
import com.ijcsj.my_library.R
import com.ijcsj.my_library.databinding.ItemDeviceBinding
import com.ijcsj.my_library.databinding.ItemFeedbackBinding
import com.ijcsj.mylibrary.bean.FeedbackBean
import com.ijcsj.ui_library.utils.AppGlobals
import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder

class FeedbackAdapter (): SimpleAdapter<ItemFeedbackBinding, FeedbackBean>() {

    var  clickCommand: BindingCommand<BindingAction>?=null
        set(value) {
            field = value
        }

    override fun onBindViewHolder(
        holder: ViewHolder<ItemFeedbackBinding>,
        position: Int,
        binding: ItemFeedbackBinding,
        data: FeedbackBean?,
        checkIndex: Int
    ) {
        binding.deviceBean=data
        binding.root.tag=position
        ViewAdapter.onClickCommand( binding.root,clickCommand)
    }


}