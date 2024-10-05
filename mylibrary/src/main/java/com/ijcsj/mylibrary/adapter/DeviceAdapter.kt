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
import com.ijcsj.ui_library.utils.AppGlobals
import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder

class DeviceAdapter (): SimpleAdapter<ItemDeviceBinding, DeviceBean>() {
    var  clickCommand: BindingCommand<BindingAction>?=null
        set(value) {
            field = value
        }


    override fun onBindViewHolder(
        holder: ViewHolder<ItemDeviceBinding>,
        position: Int,
        binding: ItemDeviceBinding,
        data: DeviceBean?,
        checkIndex: Int
    ) {
         binding.deviceBean=data;
        data?.let {
            binding.tvItemDeviceName.text=data.deviceName
            binding.tvItemDeviceTime.text="${data.lasterLoginTime} · Android端"
            binding.tvItemDeviceDz.text="江西省南昌市（${data.lasterLoginIp}）"
            binding.imgItemDeviceUrl.setImageResource(R.mipmap.android_bg)
            if (data.deviceServerUuid== DeviceInfoUtils.getUniqueId(AppGlobals.get())){
                binding.shapeTextView.visibility= View.VISIBLE
                binding.stItemDevice.visibility= View.GONE
            }else{
                binding.shapeTextView.visibility= View.GONE
                binding.stItemDevice.visibility= View.VISIBLE
            }
        }
        ViewAdapter.onClickCommand(binding.stItemDevice,clickCommand)

    }

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["device_url"])
        public  fun ImageView.loadCircle(circleUrl: DeviceBean?) {
           /* if (TextUtils.isEmpty(circleUrl)){
                this.setImageResource(R.mipmap.android_bg)
            }else{
                this.setImageResource(R.mipmap.ic_add)
            }*/
        }
    }
}