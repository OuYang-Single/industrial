package com.cn.main_library.adapter

import com.cn.main_library.base.ProjectBase
import com.cn.main_library.databinding.ItemPhoneMainBinding
import com.ijcsj.common_library.bean.DataTitle
import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder

class PhoneMainAdapter (): SimpleAdapter<ItemPhoneMainBinding, ProjectBase>() {

    override fun onBindViewHolder(
        holder: ViewHolder<ItemPhoneMainBinding>,
        position: Int,
        binding: ItemPhoneMainBinding,
        data: ProjectBase?,
        checkIndex: Int
    ) {

        binding.projectBase=data
    }
}