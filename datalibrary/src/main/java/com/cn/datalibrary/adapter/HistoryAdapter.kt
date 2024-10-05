package com.cn.datalibrary.adapter

import com.cn.datalibrary.R
import com.cn.datalibrary.databinding.ItemDataValueBinding
import com.cn.datalibrary.databinding.ItemHistoryBinding
import com.ijcsj.common_library.bean.HistoryBase
import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder

class HistoryAdapter(): SimpleAdapter<ItemHistoryBinding, HistoryBase>() {
    var currentPage:Int=0
    override fun onBindViewHolder(
        holder: ViewHolder<ItemHistoryBinding>,
        position: Int,
        binding: ItemHistoryBinding,
        data: HistoryBase?,
        checkIndex: Int
    ) {
        data?.number= ((currentPage-1)*10+position+1).toString()
        binding.dataTitle=data
    }
}