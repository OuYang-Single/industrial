package com.cn.datalibrary.viewmodel


import androidx.databinding.ObservableList
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cn.datalibrary.adapter.DataTitleAdapter
import com.cn.datalibrary.model.BataMainVideoModel
import com.ijcsj.common_library.bean.DataTitle
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel

class BataMainViewModel (override val model: BataMainVideoModel,var adapter: DataTitleAdapter) : MvmBaseViewModel<IBaseView, BataMainVideoModel>() {
    private val _dataTitleList = MutableLiveData<ObservableList<DataTitle>>()
    val dataTitleList: LiveData<ObservableList<DataTitle>> get() = _dataTitleList

    private val _dataFragmentList = MutableLiveData<ArrayList<Fragment>>()
    val dataFragmentList: LiveData<ArrayList<Fragment>> get() = _dataFragmentList

    private val _currentItem = MutableLiveData<Int>()
    val currentItem: LiveData<Int> get() = _currentItem
    public override fun initModel() {
        _dataTitleList.postValue( model.initData())
        _dataFragmentList.postValue( model.initDataFragment())
        adapter.setOnItemClickListener { v, vdb, position ->
            var data:DataTitle?=null
            var positions=0;
            for (i in 0 until adapter.datas.size) {
               adapter.datas[i] as  DataTitle
               if (adapter.datas[i] .isSelect){
                   data=adapter.datas[i]
                   positions=i;
               }
            }
            data?.isSelect=false;
            data?.let {
               (adapter.datas as ObservableList<DataTitle>)[positions] = it
            }
          var dataPositon=  (adapter.datas as ObservableList<DataTitle>)[position]
            dataPositon.isSelect=true
            (adapter.datas as ObservableList<DataTitle>)[position] = dataPositon
            _currentItem.postValue(position)

        }
    }

}