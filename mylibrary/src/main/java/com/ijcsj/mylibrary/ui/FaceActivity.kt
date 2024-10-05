package com.ijcsj.mylibrary.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ijcsj.common_library.ui.MvvmBaseActivity
import com.ijcsj.common_library.util.Constant
import com.ijcsj.my_library.R
import com.ijcsj.my_library.databinding.ActivityFaceBinding
import com.ijcsj.mylibrary.viewmodel.RealNameViewModel
import com.ijcsj.ui_library.widget.CommonTitleBar
import com.kongzue.dialogx.dialogs.WaitDialog
import com.tencent.cloud.huiyansdkface.facelight.api.WbCloudFaceContant
import com.tencent.cloud.huiyansdkface.facelight.api.WbCloudFaceVerifySdk
import com.tencent.cloud.huiyansdkface.facelight.api.listeners.WbCloudFaceVerifyLoginListener
import com.tencent.cloud.huiyansdkface.facelight.api.result.WbFaceError
import com.tencent.cloud.huiyansdkface.facelight.process.FaceVerifyStatus
import org.koin.androidx.viewmodel.ext.android.viewModel


@Route(path = "/my/FaceActivity")
class FaceActivity : MvvmBaseActivity<ActivityFaceBinding, RealNameViewModel>() {
    override val viewModel by viewModel<RealNameViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.activity_face
    var forceRealname:Boolean=false;
    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }

    override fun onAgainCreate(savedInstanceState: Bundle?) {
        forceRealname=  intent.getBooleanExtra("forceRealname",false)
        viewModel.cardInt.name=     intent.getStringExtra("name")
        viewModel.cardInt.idNum=     intent.getStringExtra("idNum")

        viewDataBinding?.topBar?.leftTextView?.visibility=if (forceRealname) View.GONE else View.VISIBLE

        viewModel.faceInit.observe(this){result->
            WaitDialog.dismiss()
            result.doOnSuccessWithValue {
                val data=Bundle()
               var inputData= WbCloudFaceVerifySdk.InputData(
                   it.faceId,
                   it.agreementNo,
                   it.openApiAppId,
                   it.openApiAppVersion,
                   it.openApiNonce,
                   it.openApiUserId,
                   it.sign,
                   FaceVerifyStatus.Mode.GRADE,
                   it.keyLicence)

                data.putSerializable(WbCloudFaceContant.INPUT_DATA, inputData);
                WbCloudFaceVerifySdk.getInstance().initSdk(this,data,object :
                    WbCloudFaceVerifyLoginListener{
                    override fun onLoginSuccess() {
                        //登录成功，拉起 sdk 页面,由 FaceVerifyResultListener   返回刷脸结果
                        //登录成功，拉起 sdk 页面,由 FaceVerifyResultListener   返回刷脸结果
                        WbCloudFaceVerifySdk.getInstance().startWbFaceVerifySdk(
                            this@FaceActivity
                        ) { result ->
                            if (result != null) {
                                if (result.isSuccess) {
                                   viewModel.checkFaceCallback(result.orderNo)
                                } else {
                                    toastUtils("认证失败，请重试")
                                }
                            }
                            WbCloudFaceVerifySdk.getInstance().release()
                        }

                    }
                    override fun onLoginFailed(p0: WbFaceError?) {
                        toastUtils("认证失败，请重试")
                        WbCloudFaceVerifySdk.getInstance().release();
                    }
                })

            }
            result.doOnFailure { code, s ->
                if (TextUtils.isEmpty(s)){
                    toastUtils("请求失败")
                }else{
                    toastUtils(s!!)
                }
            }
        }
        viewModel.checkFace.observe(this){ result->
            result.doOnSuccessWithValue {
                ARouter.getInstance().build(Constant.ACTIVITY_FACE_SUCCESS_PATH).navigation()
                finish()
            }
            result.doOnFailure { code, s ->
                if (TextUtils.isEmpty(s)){
                    toastUtils("认证失败，请重试")
                }else{
                    toastUtils(s!!)
                }
            }
        }

    }
    @Deprecated("Deprecated in Java")
    @SuppressLint("MissingSuperCall")
    override fun  onBackPressed( ) {
        if (!forceRealname) {
            finish()
        }
    }
    override fun topBarAc(): CommonTitleBar? {
        return viewDataBinding?.topBar;
    }
}