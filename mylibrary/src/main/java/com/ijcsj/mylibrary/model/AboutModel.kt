package com.ijcsj.mylibrary.model

import com.ijcsj.common_library.bean.AboutBean
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.HelpBean
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.mylibrary.api.ApiRepository
import com.ijcsj.mylibrary.bean.LittleGooseAbout

class AboutModel(private val repository: ApiRepository) : BaseModel() {
    suspend   fun getAbout(): ApiResult<AboutBean> {
        return   repository.getAbout();
    }

    suspend   fun aboutNiudunToApp(): ApiResult<LittleGooseAbout> {
        return   repository.aboutNiudunToApp();
    }

}