package com.ijcsj.inlet_library.model

import com.ijcsj.common_library.bean.AboutBean
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.HelpBean
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.inlet_library.api.ApiRepository
import com.ijcsj.ui_library.R

class InletModel(private val repository: ApiRepository) : BaseModel() {
    public fun getIntroduceData():List<Int>{
        var  infoLists = mutableListOf<Int>()
        infoLists.add(R.mipmap.ic_bg_inlet0)
        infoLists.add(R.mipmap.ic_bg_inlet1)
        infoLists.add(R.mipmap.ic_bg_inlet2)
        return infoLists
    }

    suspend   fun getAbout(): ApiResult<AboutBean> {
        return   repository.getAbout();
    }
}