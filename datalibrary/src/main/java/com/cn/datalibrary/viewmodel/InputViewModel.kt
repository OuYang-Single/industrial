package com.cn.datalibrary.viewmodel

import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cn.datalibrary.adapter.Data2ValueAdapter
import com.cn.datalibrary.adapter.DataValueAdapter
import com.cn.datalibrary.model.InputVideoModel
import com.cn.datalibrary.model.OutputVideoModel
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.bean.DataTitle
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel

class InputViewModel  (override val model: InputVideoModel, public var adapter: Data2ValueAdapter) : MvmBaseViewModel<IBaseView, InputVideoModel>() {
    private val _dataTitleList = MutableLiveData<ObservableList<DataTitle>>()
    val dataTitleList: LiveData<ObservableList<DataTitle>> get() = _dataTitleList
    var list:ObservableList<DataTitle>?=null
    public override fun initModel() {
        _dataTitleList.postValue(model.initData())
    }
    fun setCan099Data(its: CanFrame) {
        list?.let {
            model.setCan099Data(its,it)
        }

    }
    fun setCan100Data(its: CanFrame) {
        list?.let {
            model.setCan100Data(its,it)
        }
    }

    fun setCan101Data(its: CanFrame) {
        list?.let {
            model.setCan101Data(its,it)
        }
    }
}