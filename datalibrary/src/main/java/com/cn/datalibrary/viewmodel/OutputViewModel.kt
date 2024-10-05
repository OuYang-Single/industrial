package com.cn.datalibrary.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cn.datalibrary.adapter.Data1ValueAdapter
import com.cn.datalibrary.adapter.DataValueAdapter
import com.cn.datalibrary.model.BataVideoModel
import com.cn.datalibrary.model.OutputVideoModel
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.bean.DataTitle
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.Hexs
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel

class OutputViewModel (override val model: OutputVideoModel, public var adapter: Data1ValueAdapter) : MvmBaseViewModel<IBaseView, OutputVideoModel>() {
    private val _dataTitleList = MutableLiveData<ObservableList<DataTitle>>()
    val dataTitleList: LiveData<ObservableList<DataTitle>> get() = _dataTitleList
    var list:ObservableList<DataTitle>?=null
    public override fun initModel() {
        _dataTitleList.postValue(model.initData())
    }

    fun setCan102Data(its: CanFrame){
        list?.let {
            model.setCan102Data(its,it)
        }
    }
    fun setCan101Data(its: CanFrame) {
        list?.let {
            model.setCan101Data(its,it)
        }
    }
}