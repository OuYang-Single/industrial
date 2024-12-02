package com.cn.main_library.adapter

import android.content.res.ColorStateList
import com.cn.main_library.base.ProjectBase
import com.cn.main_library.base.ProjectsBase
import com.cn.main_library.databinding.ItemProjectBinding
import com.cn.main_library.databinding.ItemProjectSBinding
import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder

class ProjectsAdapter (): SimpleAdapter<ItemProjectSBinding, ProjectsBase>() {

    override fun onBindViewHolder(
        holder: ViewHolder<ItemProjectSBinding>,
        position: Int,
        binding: ItemProjectSBinding,
        data: ProjectsBase?,
        checkIndex: Int
    ) {

        binding.projectBase=data
    }
}