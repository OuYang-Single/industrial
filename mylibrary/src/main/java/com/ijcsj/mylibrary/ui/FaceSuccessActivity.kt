package com.ijcsj.mylibrary.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ijcsj.common_library.ui.MvvmBaseActivity
import com.ijcsj.my_library.R
import com.ijcsj.my_library.databinding.ActivityFaceBinding
import com.ijcsj.my_library.databinding.ActivityFaceSuccessBinding
import com.ijcsj.mylibrary.viewmodel.RealNameViewModel
import com.ijcsj.ui_library.widget.CommonTitleBar
import com.kongzue.dialogx.dialogs.WaitDialog
import com.tencent.cloud.huiyansdkface.facelight.api.WbCloudFaceContant
import com.tencent.cloud.huiyansdkface.facelight.api.WbCloudFaceVerifySdk
import com.tencent.cloud.huiyansdkface.facelight.api.listeners.WbCloudFaceVerifyLoginListener
import com.tencent.cloud.huiyansdkface.facelight.api.result.WbFaceError
import com.tencent.cloud.huiyansdkface.facelight.process.FaceVerifyStatus
import org.koin.androidx.viewmodel.ext.android.viewModel


@Route(path = "/my/FaceSuccessActivity")
class FaceSuccessActivity : MvvmBaseActivity<ActivityFaceSuccessBinding, RealNameViewModel>() {
    override val viewModel by viewModel<RealNameViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.activity_face_success

    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }

    override fun onAgainCreate(savedInstanceState: Bundle?) {
        viewDataBinding?.topBar?.leftTextView?.visibility=View.VISIBLE

    }

    override fun topBarAc(): CommonTitleBar? {
        return viewDataBinding?.topBar;
    }
}