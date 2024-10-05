package com.ijcsj.mylibrary.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.DeviceBean
import com.ijcsj.common_library.bean.IPage
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.mylibrary.adapter.DeviceAdapter
import com.ijcsj.mylibrary.model.DeviceModel
import com.ijcsj.mylibrary.model.MyModel
import com.ijcsj.service_library.bean.ApifoxModel
import com.ijcsj.service_library.login.LoginServiceProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import pw.xiaohaozi.adapter_plus.data.ViewTyper

class DeviceViewModel (override val model:DeviceModel) : MvmBaseViewModel<IBaseView, DeviceModel>(){
     var data: ApifoxModel?=null;
    private val _datum = MutableLiveData<ApiResult<IPage<ObservableArrayList<DeviceBean>>>>()
    var adapter= DeviceAdapter()
    val datum: LiveData<ApiResult<IPage<ObservableArrayList<DeviceBean>>>>get() = _datum
    public val datums = ObservableArrayList<DeviceBean>()
    var page:Int=0
    public override fun initModel() {
        adapter.clickCommand=onClickDownloadCourseInfo
        data= LoginServiceProvider.getUserProfile()
        getPageView()?.showLoading(false)
        getDeviceList()
    }
    var onClickDownloadCourseInfo=  BindingCommand<BindingAction>{

      /*  viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            _downloadCourseInfo.postValue(ApiResult(e.message))
        }) {
            var data=   model.onClickDownloadCourseInfo(courseInfo.courseInfoId)
            data.data?.courseInfoId=courseInfo.courseInfoId
            _downloadCourseInfo.postValue(data)
        }*/

    }
    public fun getDeviceList(){
         viewModelScope.launch(CoroutineExceptionHandler { _, e ->
             _datum.postValue(ApiResult(e.message))
         }) {
             data?.let {
                 _datum.postValue( model.getDeviceList(it.userInfo.id.toString(),page))
             }
         }
    }
}