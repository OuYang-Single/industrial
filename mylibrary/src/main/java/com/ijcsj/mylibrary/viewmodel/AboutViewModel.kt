package com.ijcsj.mylibrary.viewmodel

import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.ijcsj.common_library.bean.AboutBean
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.CardInfo
import com.ijcsj.common_library.bean.DeviceBean
import com.ijcsj.common_library.bean.IPage
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.ClipboardUtils
import com.ijcsj.common_library.util.Constant
import com.ijcsj.common_library.util.GlideEngine
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.mylibrary.bean.LittleGooseAbout
import com.ijcsj.mylibrary.model.AboutModel
import com.ijcsj.mylibrary.model.MyModel
import com.ijcsj.service_library.bean.ApifoxModel
import com.ijcsj.service_library.login.LoginServiceProvider
import com.kongzue.dialogx.dialogs.WaitDialog
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.luck.picture.lib.utils.SandboxTransformUtils
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import top.zibin.luban.Luban
import top.zibin.luban.OnNewCompressListener
import java.io.File

class AboutViewModel (override val model: AboutModel) : MvmBaseViewModel<IBaseView, AboutModel>(){
    var data: AboutBean?=null;
    private val _about = MutableLiveData<ApiResult<AboutBean>>()
    val about: LiveData<ApiResult<AboutBean>> get() = _about
    private val _littleGooseAbout = MutableLiveData<ApiResult<LittleGooseAbout>>()
    val littleGooseAbout: LiveData<ApiResult<LittleGooseAbout>> get() = _littleGooseAbout
    public override fun initModel() {

    }

    public fun getAbout(){
        pageView?.showLoading(false)
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            _about.postValue(ApiResult(e.message))
        }) {
            _about.postValue( model.getAbout())
        }
    }
    public fun getLittleGooseAbout(){
        pageView?.showLoading(false)
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            _littleGooseAbout.postValue(ApiResult(e.message))
        }) {
            _littleGooseAbout.postValue( model.aboutNiudunToApp())
        }
    }

    var onCopyClick=  BindingCommand<BindingAction>{
        ClipboardUtils.copyTextToClipboard(it.context,data?.btn_value)
        pageView?.showFailure("复制成功")
    }
    var onTxt1Click=  BindingCommand<BindingAction>{
        ARouter.getInstance().build("/h5/home")
            .withString("url",data?.tips1_value).withString("title",data?.tips1_txt).navigation()
    }

    var onTxt2Click=  BindingCommand<BindingAction>{
        ARouter.getInstance().build("/h5/home")
            .withString("url",data?.tips2_value).withString("title",data?.tips2_txt).navigation()
    }

    var onTxt3Click=  BindingCommand<BindingAction>{
        ARouter.getInstance().build("/h5/home")
            .withString("url",data?.tips3_value).withString("title",data?.tips3_txt).navigation()
    }

}