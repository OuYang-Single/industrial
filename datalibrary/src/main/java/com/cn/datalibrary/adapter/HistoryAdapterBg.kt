package com.cn.datalibrary.adapter

import com.cn.datalibrary.R
import com.ijcsj.common_library.bean.HistoryBase
import com.cn.datalibrary.databinding.ItemDataValueBinding
import com.cn.datalibrary.databinding.ItemHistoryBinding
import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder

class HistoryAdapterBg(): SimpleAdapter<ItemHistoryBinding, HistoryBase>() {

    override fun onBindViewHolder(
        holder: ViewHolder<ItemHistoryBinding>,
        position: Int,
        binding: ItemHistoryBinding,
        data: HistoryBase?,
        checkIndex: Int
    ) {
        if (position%2==0){
            binding.llHistory.setBackgroundColor(binding.llHistory.context.getColor(R.color.history_color_s))
        }else{
            binding.llHistory.setBackgroundColor(binding.llHistory.context.getColor(R.color.history_color))

        }
    }
}