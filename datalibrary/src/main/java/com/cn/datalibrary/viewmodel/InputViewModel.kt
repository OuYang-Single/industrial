package com.cn.datalibrary.viewmodel

import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cn.datalibrary.adapter.Data2ValueAdapter
import com.cn.datalibrary.adapter.DataValueAdapter
import com.cn.datalibrary.model.InputVideoModel
import com.cn.datalibrary.model.OutputVideoModel
import com.cn.datalibrary.popup.StylePopup
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.bean.DataTitle
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.a
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.lxj.xpopup.XPopup
import com.orhanobut.logger.Logger

class InputViewModel  (override val model: InputVideoModel, public var adapter: Data2ValueAdapter) : MvmBaseViewModel<IBaseView, InputVideoModel>() {
    private val _dataTitleList = MutableLiveData<ObservableList<DataTitle>>()
    val dataTitleList: LiveData<ObservableList<DataTitle>> get() = _dataTitleList
    var list:ObservableList<DataTitle>?=null
    public override fun initModel() {
        _dataTitleList.postValue(model.initData())
        adapter.setOnItemClickListener { v, vdb, position ->
           if (list?.get(position)?.isSelect == false){
               XPopup.Builder(v.context)
                   .enableDrag(false)
                   .autoDismiss(false)
                   .dismissOnTouchOutside(false)
                   .positionByWindowCenter(true)
                   .asCustom(StylePopup(v.context,list?.get(position)?.values!!)).show()
           }
        }
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