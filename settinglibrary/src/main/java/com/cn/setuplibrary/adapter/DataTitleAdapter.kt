package com.cn.setuplibrary.adapter

import com.ijcsj.common_library.bean.DataTitle
import com.ijcsj.stUplibrary.databinding.ItemDataTitleSettingBinding
import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder
class DataTitleAdapter  (): SimpleAdapter<ItemDataTitleSettingBinding, DataTitle>() {

    override fun onBindViewHolder(
        holder: ViewHolder<ItemDataTitleSettingBinding>,
        position: Int,
        binding: ItemDataTitleSettingBinding,
        data: DataTitle?,
        checkIndex: Int
    ) {

        binding.dataTitle=data
    }
}