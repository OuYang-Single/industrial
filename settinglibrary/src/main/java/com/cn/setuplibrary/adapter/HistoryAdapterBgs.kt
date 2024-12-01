package com.cn.setuplibrary.adapter

import com.ijcsj.common_library.bean.HistoryBase
import com.ijcsj.stUplibrary.R
import com.ijcsj.stUplibrary.databinding.ItemHistorysBinding
import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder

class HistoryAdapterBgs: SimpleAdapter<ItemHistorysBinding, HistoryBase>() {

    override fun onBindViewHolder(
        holder: ViewHolder<ItemHistorysBinding>,
        position: Int,
        binding: ItemHistorysBinding,
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