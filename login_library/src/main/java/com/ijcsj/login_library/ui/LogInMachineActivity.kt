package com.ijcsj.login_library.ui

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.Permission.ACCESS_COARSE_LOCATION
import com.hjq.permissions.Permission.ACCESS_FINE_LOCATION
import com.hjq.permissions.XXPermissions
import com.hjq.shape.layout.ShapeLinearLayout
import com.ijcsj.common_library.ui.MvvmBaseActivity
import com.ijcsj.common_library.util.Constant
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.login_library.R
import com.ijcsj.login_library.databinding.ActivityLoginBinding
import com.ijcsj.login_library.databinding.ActivityLoginMachineBinding
import com.ijcsj.login_library.viewmodel.LogInViewModel
import com.ijcsj.service_library.login.LoginServiceProvider
import com.ijcsj.ui_library.anko.countDownCoroutines
import com.ijcsj.ui_library.utils.AppGlobals
import com.ijcsj.ui_library.utils.ContextExt.drawable
import com.ijcsj.ui_library.utils.immersive
import com.kongzue.dialog.v3.WaitDialog
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel


@Route(path = "/LogIn/Machine/LogInActivity")
class LogInMachineActivity  : MvvmBaseActivity<ActivityLoginMachineBinding, LogInViewModel>(),
    View.OnFocusChangeListener, (AMapLocation) -> Unit {
   override val viewModel by viewModel<LogInViewModel>()
    override val bindingVariable: Int get() = 0

    private var mCountdownJob: Job? = null
    override val layoutId: Int get() =R.layout.activity_login_machine
    private var spannableString: SpannableString? = null
    override fun onBinding() {
        isLogout=false
        viewDataBinding?.viewModel=viewModel
    }


    override fun onAgainCreate(savedInstanceState: Bundle?) {
        immersive(darkMode = false)
     var type=   intent.getIntExtra("type",0)
        viewModel.type=type
        if (type==0){
           viewDataBinding?.loginRg?.text="用户登录"
        }else if (type==1){
            viewDataBinding?.loginRg?.text="工程账号登录"
        }else if (type==2){
            viewDataBinding?.loginRg?.text="厂家账号登录"
        }
        viewModel.message.observe(this) {
            toastUtils(it)
        }

    }



    override fun onFocusChange(p0: View?, p1: Boolean) {

    }


    override fun invoke(amapLocation: AMapLocation) {
        Log.w("","")



    }

}