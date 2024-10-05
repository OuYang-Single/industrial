package com.cn.datalibrary.viewmodel


import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cn.datalibrary.adapter.DataTitleAdapter
import com.cn.datalibrary.adapter.DataValueAdapter
import com.cn.datalibrary.model.BataMainVideoModel
import com.cn.datalibrary.model.BataVideoModel
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.bean.DataTitle
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel

class BataViewModel (override val model: BataVideoModel ,public var adapter: DataValueAdapter) : MvmBaseViewModel<IBaseView, BataVideoModel>() {
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
}