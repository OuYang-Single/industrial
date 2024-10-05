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
import com.ijcsj.login_library.viewmodel.LogInViewModel
import com.ijcsj.service_library.login.LoginServiceProvider
import com.ijcsj.ui_library.anko.countDownCoroutines
import com.ijcsj.ui_library.utils.AppGlobals
import com.ijcsj.ui_library.utils.ContextExt.drawable
import com.ijcsj.ui_library.utils.immersive
import com.kongzue.dialog.v3.WaitDialog
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel


@Route(path = "/LogIn/LogInActivity")
class LogInActivity  : MvvmBaseActivity<ActivityLoginBinding, LogInViewModel>(),
    View.OnFocusChangeListener, (AMapLocation) -> Unit {
   override val viewModel by viewModel<LogInViewModel>()
    override val bindingVariable: Int get() = 0

    private var mCountdownJob: Job? = null
    override val layoutId: Int get() =R.layout.activity_login
    private var spannableString: SpannableString? = null
    override fun onBinding() {
        isLogout=false
        LiveDataBus.get().with("logout", Boolean::class.java ).postValue(false)
        viewDataBinding?.viewModel=viewModel
    }
    private   var REQUEST_MANAGE_FILES_ACCESS = 2;
    //申请所有文件访问权限
    public fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //判断是否有管理外部存储的权限
            if (!Environment.isExternalStorageManager()) {
              var   intent  =  Intent(ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + packageName));
                startActivityForResult(intent, REQUEST_MANAGE_FILES_ACCESS);
            } else {

            }
        } else {

        }
    }
    override fun onAgainCreate(savedInstanceState: Bundle?) {
        immersive(darkMode = false)
        // 请求文件访问权限的请求码，可以是任意整数值

        XXPermissions.with(this)
            // 申请单个权限
            .permission(Permission.ACCESS_COARSE_LOCATION,Permission.READ_PHONE_STATE,Permission.READ_PHONE_NUMBERS,ACCESS_FINE_LOCATION ,ACCESS_COARSE_LOCATION)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                  initLocation();
                 requestPermission()
                }
                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {

                }
            })
        viewDataBinding?.etPhone?.onFocusChangeListener =this
        viewDataBinding?.edCode?.onFocusChangeListener =this
        viewDataBinding?.edPassword?.onFocusChangeListener =this
        spannableString = SpannableString("我已阅读并同意《用户协议》和《隐私协议》")
     /*   spannableString?.setSpan(object: ClickableSpan(){
            override fun onClick(widget: View) {
                ARouter.getInstance().build("/h5/home")
                    .withString("url", "https://file.harem.vip/profile/file/privacy.html")
                    .withString("imgUrl","隐私政策").navigation()
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = this@LogInActivity.getColor(R.color.privacy_color)
                //取消默认的下划线
                ds.isUnderlineText = false
            }
        },7,13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString?.setSpan(object: ClickableSpan(){
            override fun onClick(widget: View) {
                ARouter.getInstance().build("/h5/home")
                    .withString("url", "https://file.harem.vip/profile/file/user.html")
                    .withString("imgUrl","用户协议").navigation()
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = this@LogInActivity.getColor(R.color.privacy_color)
                //取消默认的下划线
                ds.isUnderlineText = false
            }
        },14,19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)*/
        //然后把spannableString放进到Text中
        viewDataBinding?.tvPrivacy?.text = spannableString
        //中途遇到点击后字体显示高亮，取消高亮
        viewDataBinding?.tvPrivacy?.highlightColor = (Color.parseColor("#00000000"))
        //最后设置可点击，必须实现，否则只能显示样式，无法实现点击效果
        viewDataBinding?.tvPrivacy?.movementMethod = LinkMovementMethod.getInstance()
        viewModel.code.observe(this){
            it?.let {
                count()
            }
        }
        viewModel.sendCode.observe(this){ result->
            result?.let {
                result.doOnSuccessWithValue {
                    result.msg?.let { it1 -> toastUtils(it1) }
                    viewModel.logInBean.captchaKey=it;
                }
                result.doOnFailure { code, s ->
                    s?.let {
                        toastUtils(s)
                    }
                }
            }
        }
        viewModel.message.observe(this) {
            toastUtils(it)
        }

        viewModel.passwordDisplay.observe(this){
            viewDataBinding?.let {t->
                t.edPassword.inputType = if (it){
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                }else{
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                };
                t.edPassword.setSelection(t.edPassword.text.length);
            }
        }

        viewModel. userData.observe(this){ result->
            result?.let {
                result.doOnSuccessWithValue {cdat->
                    LoginServiceProvider.login(cdat)
                    viewModel.getAbout()
                    AppGlobals.get()?.let {
                        XXPermissions.with(this)
                            // 申请单个权限
                            .permission(Permission.READ_PHONE_STATE)
                            .permission(Permission.READ_PHONE_NUMBERS)
                            // 申请多个权限
                            .request(object : OnPermissionCallback {
                                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                                    viewModel.bindDevice(cdat.userInfo.id)
                                }
                                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                                    ARouter.getInstance().build(Constant.ACTIVITY_MAIN_PATH).navigation()
                                    WaitDialog.dismiss()
                                    finish()
                                }
                            }) }

                }
                result.doOnFailure { code, s ->
                    WaitDialog.dismiss()
                    s?.let {
                        toastUtils(s)
                    }
                }
            }

        viewModel.bindDevice.observe(this){
            ARouter.getInstance().build(Constant.ACTIVITY_MAIN_PATH).navigation()
            WaitDialog.dismiss()
            finish()
        }
    }
    }

    private fun initLocation(){
        var mLocationClient: AMapLocationClient? = null
        val mLocationListener = AMapLocationListener(this)
        mLocationClient = AMapLocationClient(applicationContext)
        var mLocationOption: AMapLocationClientOption? = null
        mLocationOption = AMapLocationClientOption()
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        mLocationClient.setLocationListener(mLocationListener)
//给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();

    }
    private fun count() {

        mCountdownJob = countDownCoroutines(60, lifecycleScope,
            onTick = { second ->
                viewModel.logInBean.translateHint= String.format(getString(R.string.send_interval), second)
                viewModel.logInBean.isTranslateTextColor= true
            }, onStart = null, onFinish = {
                viewModel.logInBean.translateHint=getString(R.string.get_yzm)
                viewModel.logInBean.isTranslateTextColor= false
            })
    }

    override fun onFocusChange(p0: View?, p1: Boolean) {
        var  parent=   p0?.parent as ShapeLinearLayout
        if (p1) {
            parent. shapeDrawableBuilder
                .setStrokeColor(parent.context.getColor(R.color.f44336))
                .intoBackground();
        } else {
            if (p0.id==R.id.et_phone ){
                viewModel.getPwdKey()
            }
            parent. shapeDrawableBuilder
                .setStrokeColor(parent.context.getColor(R.color.transparent))
                .intoBackground();
        }
    }


    override fun invoke(amapLocation: AMapLocation) {
        Log.w("","")

        if (amapLocation != null) {
            if (amapLocation.errorCode == 0) {
//可在其中解析amapLocation获取相应内容。
                viewModel.setLocation=       amapLocation.country+amapLocation.province+ amapLocation.city;//城市信息
            }else {

            }
        }

    }

}