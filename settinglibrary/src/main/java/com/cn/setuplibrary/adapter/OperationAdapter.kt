package com.cn.setuplibrary.adapter

import android.annotation.SuppressLint
import com.ijcsj.common_library.bean.DatasBase
import com.ijcsj.stUplibrary.databinding.ItemDataTitleSettingBinding
import com.ijcsj.stUplibrary.databinding.ItemOperationBinding
import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder

class OperationAdapter (): SimpleAdapter<ItemOperationBinding, DatasBase>() {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: ViewHolder<ItemOperationBinding>,
        position: Int,
        binding: ItemOperationBinding,
        data: DatasBase?,
        checkIndex: Int
    ) {

        binding.dataTitle=data
        if (data?.canId?.length!! >2){
            binding.tvCanId.text="0x${data.canId}"
        }else{
            binding.tvCanId.text="0x0${data.canId}"
        }

        binding.tvTitle.text=if (data?.isType == true){
            "接收"
        }else{
            "发送"
        }
    }
}