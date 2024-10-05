package com.cn.setuplibrary.adapter

import android.view.View
import com.ijcsj.stUplibrary.databinding.ItemStyleBinding

import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder

class StyleAdapter(var RightClick: OnClickListeners): SimpleAdapter<ItemStyleBinding, String>() {
    interface OnClickListeners {
        fun onClick(
            string: String?,
            int: Int
        )
    }
    override fun onBindViewHolder(
        holder: ViewHolder<ItemStyleBinding>,
        position: Int,
        binding: ItemStyleBinding,
        data: String?,
        checkIndex: Int
    ) {
        binding.tvName.text=data
        binding.root.setOnClickListener {
            RightClick.onClick(data,position)
        }
    }
}