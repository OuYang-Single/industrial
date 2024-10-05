package com.ijcsj.inlet_library.adapter

import com.ijcsj.inlet_library.R
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class SimpleAdapter : BaseBannerAdapter<Int>() {

    override fun bindData(holder: BaseViewHolder<Int>, data: Int?, position: Int, pageSize: Int) {
        if (data != null) {
            holder.setImageResource(R.id.img_item_custom,data)
        }
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_custom_view;
    }
}
