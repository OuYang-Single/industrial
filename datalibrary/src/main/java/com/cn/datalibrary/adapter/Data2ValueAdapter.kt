package com.cn.datalibrary.adapter


import android.graphics.Color
import com.cn.datalibrary.databinding.ItemDataTitleBinding
import com.cn.datalibrary.databinding.ItemDataValueBinding
import com.ijcsj.common_library.bean.DataTitle
import pw.xiaohaozi.adapter_plus.adapter.SimpleAdapter
import pw.xiaohaozi.adapter_plus.holder.ViewHolder

class Data2ValueAdapter   (): SimpleAdapter<ItemDataValueBinding, DataTitle>() {

    override fun onBindViewHolder(
        holder: ViewHolder<ItemDataValueBinding>,
        position: Int,
        binding: ItemDataValueBinding,
        data: DataTitle?,
        checkIndex: Int
    ) {
        binding.dataTitle=data
        if (data?.isSelect==true){
            binding.shapeRelat.shapeDrawableBuilder
                .setStrokeColor(Color.parseColor("#1B56A5"))
                // 注意：最后需要调用一下 intoBackground 方法才能生效
                .intoBackground();
        }else{
            binding.shapeRelat.shapeDrawableBuilder
                .setStrokeColor(Color.parseColor("#F44336"))
                // 注意：最后需要调用一下 intoBackground 方法才能生效
                .intoBackground();

        }

    }
}