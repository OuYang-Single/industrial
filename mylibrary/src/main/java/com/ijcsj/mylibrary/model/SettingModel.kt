package com.ijcsj.mylibrary.model

import com.ijcsj.common_library.bean.AboutBean
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.HelpBean
import com.ijcsj.common_library.bean.RealStatus
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.mylibrary.api.ApiRepository

class SettingModel (private val repository: ApiRepository) : BaseModel() {

    suspend   fun getHelpUrl(): ApiResult<HelpBean> {
        return   repository.getHelpUrl();
    }

}