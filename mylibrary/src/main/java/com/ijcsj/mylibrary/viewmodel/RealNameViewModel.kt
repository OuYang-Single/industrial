package com.ijcsj.mylibrary.viewmodel

import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.ijcsj.common_library.bean.AboutBean
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.bean.CardInfo
import com.ijcsj.common_library.bean.FaceInit
import com.ijcsj.common_library.bean.FileUpload
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.Constant
import com.ijcsj.common_library.util.GlideEngine
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.mylibrary.model.AboutModel
import com.ijcsj.mylibrary.model.RealNameModel
import com.ijcsj.ui_library.utils.AppGlobals
import com.kongzue.dialogx.dialogs.WaitDialog
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.engine.CompressFileEngine
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.luck.picture.lib.utils.SandboxTransformUtils
import com.tencent.cloud.huiyansdkface.facelight.api.WbCloudFaceVerifySdk
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import top.zibin.luban.Luban
import top.zibin.luban.OnNewCompressListener
import java.io.File

class RealNameViewModel (override val model: RealNameModel) : MvmBaseViewModel<IBaseView, RealNameModel>(){
   var cardInt= CardInfo()
    var forceRealname=false;
    private val _imgBean = MutableLiveData<ApiResult<CardInfo>>()
    val imgBean: LiveData<ApiResult<CardInfo>> get() = _imgBean

   private val _faceInit = MutableLiveData<ApiResult<FaceInit>>()
    val faceInit: LiveData<ApiResult<FaceInit>> get() = _faceInit
    private val _checkFace = MutableLiveData<ApiResult<Boolean>>()
    val checkFace: LiveData<ApiResult<Boolean>> get() = _checkFace


    public override fun initModel() {


    }
    /*获取人脸识别SDK参数*/
    fun getFaceSdkSign(){
        WaitDialog.show("正在加载中...");
        AppGlobals.get()?.let {
            viewModelScope.launch(CoroutineExceptionHandler { _, e ->
                _faceInit.postValue(ApiResult(e.message))
            }) {
                var  result=  model.getFaceSdkSign(1)
                _faceInit.postValue(result)
            }
        }
    }

    fun checkFaceCallback(orderNo: String) {
        AppGlobals.get()?.let {
            viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            }) {
                AppGlobals.get()?.let {
                    viewModelScope.launch(CoroutineExceptionHandler { _, e ->
                        _checkFace.postValue(ApiResult(e.message))
                    }) {
                        var  result=  model.checkFaceCallback(orderNo)
                        _checkFace.postValue(result)
                    }
                }
            }
        }
    }

    var onUploadFrontClick=  BindingCommand<BindingAction>{
        PictureSelector.create(it.context)
            .openGallery(SelectMimeType.ofImage())
            .setImageEngine(GlideEngine.createGlideEngine())
            .setMaxSelectNum(1)
            .setCompressEngine(CompressFileEngine { context, source, call ->
                Luban.with(context).load(source).ignoreBy(800)
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
                        WaitDialog.show( "正在上传...");
                        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
                            _imgBean.postValue(ApiResult(e.message))
                        }) {
                           var resultd=  model.uploadSfz(result[0].compressPath!!,1)
                            _imgBean.postValue(resultd)
                            resultd.doOnSuccessWithValue {
                                cardInt.fileJust=result[0].compressPath!!
                                cardInt.name=it.name
                                cardInt.idNum=it.idNum
                            }
                        }
                    }else{

                    }
                }
                override fun onCancel() {}
            })
    }

    var onUploadBackClick=  BindingCommand<BindingAction>{
        PictureSelector.create(it.context)
            .openGallery(SelectMimeType.ofImage())
            .setImageEngine(GlideEngine.createGlideEngine())
            .setMaxSelectNum(1)
            .setCompressEngine(CompressFileEngine { context, source, call ->
                Luban.with(context).load(source).ignoreBy(800)
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
                        WaitDialog.show(it.context as AppCompatActivity, "正在上传...");
                        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
                            _imgBean.postValue(ApiResult(e.message))
                        }) {
                            var resultd=  model.uploadSfz(result[0].compressPath!!,2)
                            _imgBean.postValue(resultd)
                            resultd.doOnSuccessWithValue {
                                cardInt.fileBack=result[0].compressPath!!
                            }
                        }
                    }else{

                    }
                }
                override fun onCancel() {}
            })
    }

    var onFaceClick=  BindingCommand<BindingAction>{
        if (!TextUtils.isEmpty(cardInt.fileJust)&&!TextUtils.isEmpty(cardInt.fileBack)){
            ARouter.getInstance().build(Constant.ACTIVITY_FACE_PATH).withBoolean("forceRealname",true).withString("name",cardInt.name).withString("idNum",cardInt.idNum).navigation()

            pageView?.closure(null)
        }else{
          getPageView()?.showFailure("请先完成身份证")
        }

    }

    var onFacesClick=  BindingCommand<BindingAction>{
        getFaceSdkSign()
    }

    var onFacesSuccessClick=  BindingCommand<BindingAction>{
       pageView?.closure(null)
    }
}