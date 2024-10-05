package com.ijcsj.mylibrary.model

import com.ijcsj.common_library.bean.AboutBean
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.HelpBean
import com.ijcsj.common_library.bean.RealStatus
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.mylibrary.api.ApiRepository

class MyModel(private val repository: ApiRepository) : BaseModel() {

    suspend   fun getHelpUrl(): ApiResult<HelpBean> {
        return   repository.getHelpUrl();
    }
    suspend   fun getAbout(): ApiResult<AboutBean> {
        return   repository.getAbout();
    }

    suspend   fun logOut(): ApiResult<String> {
        return   repository.logOut();
    }
    suspend   fun tximInit(): ApiResult<String> {
        return   repository.tximInit();
    }

    suspend   fun  getRealStatus(): ApiResult<RealStatus>  {
        return   repository.getRealStatus();
    }
}