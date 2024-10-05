package com.cn.datalibrary.adapter


import com.cn.datalibrary.databinding.ItemDataTitleBinding
import com.cn.datalibrary.databinding.ItemDataValueBinding
import com.ijcsj.common_library.bean.DataTitle
import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder

class DataValueAdapter   (): SimpleAdapter<ItemDataValueBinding, DataTitle>() {

    override fun onBindViewHolder(
        holder: ViewHolder<ItemDataValueBinding>,
        position: Int,
        binding: ItemDataValueBinding,
        data: DataTitle?,
        checkIndex: Int
    ) {

        binding.dataTitle=data
    }
}