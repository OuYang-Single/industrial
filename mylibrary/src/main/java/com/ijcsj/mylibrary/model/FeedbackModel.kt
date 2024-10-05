package com.ijcsj.mylibrary.model

import android.os.Build
import androidx.databinding.ObservableArrayList
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.DeviceBean
import com.ijcsj.common_library.bean.FileUpload
import com.ijcsj.common_library.bean.HelpBean
import com.ijcsj.common_library.bean.IPage
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.common_library.util.DeviceInfoUtils
import com.ijcsj.mylibrary.adapter.FeedbackAdapter
import com.ijcsj.mylibrary.api.ApiRepository
import com.ijcsj.mylibrary.bean.FeedbackBean
import com.ijcsj.mylibrary.bean.Retroaction
import com.ijcsj.ui_library.utils.AppGlobals

class FeedbackModel (private val repository: ApiRepository) : BaseModel() {

    fun getTitleList(): ObservableArrayList<FeedbackBean> {
         val titleList = ObservableArrayList<FeedbackBean>()
        titleList.add(  FeedbackBean("卡顿，资源占用高或崩溃",true))
        titleList.add(  FeedbackBean("文件无法播放",false))
        titleList.add(  FeedbackBean("文件使用浏览器",false))
        titleList.add(  FeedbackBean("边下边播异常",false))
        titleList.add(  FeedbackBean("没有想要的功能",false))
        titleList.add(  FeedbackBean("画面、声音、字幕异常",false))
        titleList.add(  FeedbackBean("其他建议",false))
        return titleList;
    }

    suspend   fun uploadFile(compressPath: String):  ApiResult<FileUpload> {
        return   repository.uploadFile(compressPath);
    }
    suspend   fun submit(compressPath: Retroaction):  ApiResult<String> {
        return   repository.submit(compressPath);
    }




}