package com.cn.main_library.adapter

import android.content.res.ColorStateList
import com.cn.main_library.base.ProjectBase
import com.cn.main_library.databinding.ItemProjectBinding
import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder

class ProjectAdapter (): SimpleAdapter<ItemProjectBinding, ProjectBase>() {

    override fun onBindViewHolder(
        holder: ViewHolder<ItemProjectBinding>,
        position: Int,
        binding: ItemProjectBinding,
        data: ProjectBase?,
        checkIndex: Int
    ) {

        binding.projectBase=data
    }
}