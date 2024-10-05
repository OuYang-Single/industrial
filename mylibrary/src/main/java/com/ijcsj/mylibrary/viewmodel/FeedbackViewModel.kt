package com.ijcsj.mylibrary.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.FileUpload
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.GlideEngine
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.mylibrary.adapter.FeedbackAdapter
import com.ijcsj.mylibrary.adapter.GlideDisplayer
import com.ijcsj.mylibrary.bean.FeedbackBean
import com.ijcsj.mylibrary.bean.Retroaction
import com.ijcsj.mylibrary.model.FeedbackModel
import com.ijcsj.service_library.bean.ApifoxModel
import com.ijcsj.service_library.login.LoginServiceProvider
import com.ijcsj.ui_library.widget.ngv.DefaultNgvAdapter
import com.kongzue.dialog.v3.WaitDialog
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.engine.CompressFileEngine
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.luck.picture.lib.utils.SandboxTransformUtils
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import top.zibin.luban.Luban
import top.zibin.luban.OnNewCompressListener
import java.io.File
import java.util.Locale
import java.util.stream.Collectors

class FeedbackViewModel  (override val model:FeedbackModel) : MvmBaseViewModel<IBaseView, FeedbackModel>(){
    var data: ApifoxModel?=null;
    var retroaction= Retroaction()
    var adapter= FeedbackAdapter()
    private val _datum = MutableLiveData<ObservableArrayList<FeedbackBean>>()
    val datum: LiveData<ObservableArrayList<FeedbackBean>> get() = _datum
    private val _submitBean = MutableLiveData<ApiResult<String>>()
    val submitBean: LiveData<ApiResult<String>> get() = _submitBean
    private val _imgBean = MutableLiveData<ApiResult<FileUpload>>()
    val imgBean: LiveData<ApiResult<FileUpload>> get() = _imgBean
    var fileUploadList= ObservableArrayList<String>()


    var mAdapter = DefaultNgvAdapter(100, GlideDisplayer())


    var page:Int=0
    public override fun initModel() {
        adapter.clickCommand=onClickDownloadCourseInfo
        _datum.postValue(model.getTitleList())
        data= LoginServiceProvider.getUserProfile()
        getDeviceList()
    }
    var onClickDownloadCourseInfo=  BindingCommand<BindingAction>{ view->
        datum.value?.let {
         var isChar=  it.stream()
                .filter { person -> person.isChar }
                .collect(Collectors.toList())
            var  isCharNem=gson.fromJson( gson.toJson(isChar[0]), FeedbackBean::class.java)
            isCharNem.isChar=false
            val index: Int = it.indexOf(isChar[0])
            it[index] = isCharNem
            var selectData= it[view.tag as Int]
            var  selectDataNew =gson.fromJson( gson.toJson(selectData), FeedbackBean::class.java)
            selectDataNew.isChar=true
            it[view.tag as Int] = selectDataNew
        }
    }

    var onSubmitonClick=  BindingCommand<BindingAction>{ view->
        WaitDialog.show(view.context as AppCompatActivity, "提交中...");
        val sucessFileKey= ObservableArrayList<String>()
        for (i in mAdapter.dataList.indices) {
            sucessFileKey.add(mAdapter.dataList[i].fileKey)
        }
      val title=  datum.value?.stream()
           ?.filter { person -> person.isChar }
            ?.collect(Collectors.toList())
        retroaction.adviseTitle=title?.get(0)?.title
        retroaction.delFileKeys=fileUploadList
        retroaction.sucessFileKey=sucessFileKey
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            _submitBean.postValue(ApiResult(e.message))
        }) {
            _submitBean.postValue( model.submit(retroaction))
        }
    }


    var onImageSelectionClick=  BindingCommand<BindingAction>{ view->
        PictureSelector.create(view.context)
            .openGallery(SelectMimeType.ofImage())
            .setImageEngine(GlideEngine.createGlideEngine())
            .setMaxSelectNum(1)
            .setCompressEngine(CompressFileEngine { context, source, call ->
                Luban.with(context).load(source).ignoreBy(100)
                    .setCompressListener(object : OnNewCompressListener {

                        override fun onStart() {

                        }

                        override fun onSuccess(source: String?, compressFile: File?) {
                            call?.onCallback(source, compressFile?.absolutePath)
                        }

                        override fun onError(source: String?, e: Throwable?) {
                            call?.onCallback(source, null)
                        }
                    }).launch();
            }).setSandboxFileEngine { context, srcPath, mineType, call ->
                if (call != null) {
                    var sandboxPath =
                        SandboxTransformUtils.copyPathToSandbox(context, srcPath, mineType)
                    call.onCallback(srcPath, sandboxPath);
                }
            }.forResult(object : OnResultCallbackListener<LocalMedia> {
                override fun onResult(result: ArrayList<LocalMedia>) {
                    if (result.size>0){
                        WaitDialog.show(view.context as AppCompatActivity, "正在上传...");
                        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
                            _imgBean.postValue(ApiResult(e.message))
                        }) {
                            _imgBean.postValue( model.uploadFile(result[0].compressPath!!))
                        }
                    }else{

                    }
                }
                override fun onCancel() {}
            })
    }


    public fun getDeviceList(){
      /*  viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            _datum.postValue(ApiResult(e.message))
        }) {
            data?.let {
                _datum.postValue( model.getDeviceList(it.userInfo.id.toString(),page))
            }
        }*/
    }
}