package com.cn.datalibrary.viewmodel

import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cn.datalibrary.adapter.DataValueAdapter
import com.cn.datalibrary.base.VersionBase
import com.cn.datalibrary.model.OutputVideoModel
import com.cn.datalibrary.model.VersionVideoModel
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel

class VersionViewModel (override val model: VersionVideoModel,public var versionBase: VersionBase) : MvmBaseViewModel<IBaseView, VersionVideoModel>() {

    public override fun initModel() {

    }

    fun setCan109Data(it: CanFrame) {
        versionBase.ver= "Ver."+ it.data[0].toString()+""
    }

}