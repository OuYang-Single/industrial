package com.cn.datalibrary.adapter


import com.cn.datalibrary.databinding.ItemDataTitleBinding
import com.ijcsj.common_library.bean.DataTitle
import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder

class DataTitleAdapter  (): SimpleAdapter<ItemDataTitleBinding, DataTitle>() {

    override fun onBindViewHolder(
        holder: ViewHolder<ItemDataTitleBinding>,
        position: Int,
        binding: ItemDataTitleBinding,
        data: DataTitle?,
        checkIndex: Int
    ) {

        binding.dataTitle=data
    }
}