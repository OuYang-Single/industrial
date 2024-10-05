package com.ijcsj.login_library.viewmodel

import android.app.Activity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hjq.shape.layout.ShapeLinearLayout
import com.hjq.shape.view.ShapeTextView
import com.ijcsj.common_library.bean.ApiResult
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.Constant
import com.ijcsj.common_library.util.Hexs.validatePhoneNumber
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.login_library.R
import com.ijcsj.login_library.bean.LoginBean
import com.ijcsj.login_library.bean.LoginsBean
import com.ijcsj.login_library.model.LogInModel
import com.ijcsj.service_library.bean.ApifoxModel
import com.ijcsj.ui_library.anko.dp
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import com.kongzue.dialog.v3.WaitDialog
import com.ijcsj.common_library.util.a
class LogInViewModel  (override val model: LogInModel,var logInBean: LoginBean,var logInBeans: LoginsBean) : MvmBaseViewModel<IBaseView, LogInModel>() ,
    TextWatcher {

        var type=0
    var setLocation: String?=null
    private val _passwordDisplay = MutableLiveData<Boolean>()
    val passwordDisplay: LiveData<Boolean> get() = _passwordDisplay
    private val _code = MutableLiveData<String>()
    val code: LiveData<String> get() = _code
    private val _introduceDataList = MutableLiveData<List<Int>>()

    private val _sendCode = MutableLiveData<ApiResult<String>>()
    val sendCode: LiveData<ApiResult<String>> get() = _sendCode
    val introduceDataList: LiveData<List<Int>> get() = _introduceDataList
    private val _userData = MutableLiveData<ApiResult<ApifoxModel>>()
    val userData: LiveData<ApiResult<ApifoxModel>> get() = _userData


    private val _bindDevice = MutableLiveData<ApiResult<String>>()
    val bindDevice: LiveData<ApiResult<String>> get() = _bindDevice
    public override fun initModel() {

    }

    var onCodeClick=  BindingCommand<BindingAction>{
        Logger.w("onCodeClick");
        logInBean.isType=false
    }
    var onPasswordClick=  BindingCommand<BindingAction>{
        Logger.w("onPasswordClick");
        logInBean.isType=true
    }

    var onSendCodeClick=  BindingCommand<BindingAction>{
        Logger.w("onCodeClick");
        if (!logInBean.isTranslateTextColor){
            if (validatePhoneNumber(logInBean.phone)){
                _code.postValue("1")
                viewModelScope.launch(CoroutineExceptionHandler { _, e ->
                    _sendCode.postValue(ApiResult(e.message))
                }) {
                    _sendCode.postValue( model.sendCaptcha(logInBean.phone))
                }
            }else{
                _message.postValue("手机号格式错误")
            }
        }
    }
    var onClickPassword=  BindingCommand<BindingAction>{
        logInBean.isPasswordDisplay=!logInBean.isPasswordDisplay
        _passwordDisplay.postValue( logInBean.isPasswordDisplay)
    }
    var onClickMachineLogin=  BindingCommand<BindingAction>{
        if (TextUtils.isEmpty(logInBeans.password)){
            _message.postValue("请输入密码")
            return@BindingCommand
        }
        var userLogOnPassword="";
        if (type==0){
             userLogOnPassword=  ShuJuMMkV.getInstances()?.getString(a.USER_LOG_ON_PASSWORD,"123")!!
        }else if (type==1){
            userLogOnPassword=  ShuJuMMkV.getInstances()?.getString(a.ENGINEERING_LOG_ON_PASSWORD,"123")!!
        }else if (type==2){
            userLogOnPassword=  ShuJuMMkV.getInstances()?.getString(a.MANUFACTOR_LOG_ON_PASSWORD,"123")!!
        }

        if (logInBeans.password==userLogOnPassword){
            if (type==0){
                ShuJuMMkV.getInstances()?.putBoolean(a.USER_LOG_ON,true)
            }else if (type==1){
                ShuJuMMkV.getInstances()?.putBoolean(a.ENGINEERING_LOG_ON,true)
            }else if (type==2){
                ShuJuMMkV.getInstances()?.putBoolean(a.MANUFACTOR_LOG_ON,true)
            }

            ( (it.context) as Activity).finish()
            _message.postValue("登录成功")
        }else{
            _message.postValue("密码错误，请重试")
        }

    }
    var onClickLogin=  BindingCommand<BindingAction>{
        if(logInBean.isPrivacy){
            if (logInBean.isType){
                if (TextUtils.isEmpty(logInBean.password)){
                    _message.postValue("请输入密码")
                    return@BindingCommand
                }
               WaitDialog.show(it.context as AppCompatActivity, "正在登录...");
                viewModelScope.launch(CoroutineExceptionHandler { _, e ->
                    _userData.postValue(ApiResult(e.message))
                }) {
                    val userData= model.loginPassword(logInBean.loginParam)
                    _userData.postValue(userData)
                }
            }else{
                if (!validatePhoneNumber(logInBean.phone)){
                    _message.postValue("手机号格式错误")
                    return@BindingCommand
                }
                if (TextUtils.isEmpty(logInBean.translate)){
                    _message.postValue("请输入验证码")
                    return@BindingCommand
                }
               WaitDialog.show(it.context as AppCompatActivity, "正在登录...");
                viewModelScope.launch(CoroutineExceptionHandler { _, e ->
                    _userData.postValue(ApiResult(e.message))
                }) {
                    val userData= model.loginTranslate(logInBean.apifoxModel)
                    _userData.postValue(userData)
                }
            }
        }else{
            _message.postValue("请勾选用户协议")
        }
    }
    var onClickPrivacy=  BindingCommand<BindingAction>{
        logInBean.isPrivacy=!logInBean.isPrivacy
    }
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {

    }

    fun getPwdKey(){
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->

        }) {
            var data=  model.getPwdKey(logInBean.phone)
            data.doOnSuccessWithValue {
                Logger.d("  ${it.ik}")
                logInBean.encryptionData = it;
            }
        }
    }

    fun getAbout(){
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        }) {
            var  result=  model.getHelpUrl()
            result.doOnSuccessWithValue {
                ShuJuMMkV.instance?.putString(Constant.HELPER_URL,it.helper_url)
            }
            var  about=  model.getAbout()
            about.doOnSuccessWithValue {
                ShuJuMMkV.instance?.putAny(Constant.ABOUT_DATA,it)
            }
        }
    }

    fun getInit(){
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        }) {
            var  result=  model.getHelpUrl()
            result.doOnSuccessWithValue {
                ShuJuMMkV.instance?.putString(Constant.HELPER_URL,it.helper_url)
            }
            var  about=  model.getAbout()
            about.doOnSuccessWithValue {
                ShuJuMMkV.instance?.putAny(Constant.ABOUT_DATA,it)
            }
        }
    }

    fun bindDevice(userId:Long ){
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            _bindDevice.postValue(ApiResult(e.message))
        }) {
            var data=  model.bindDevice( model.getDeviceInit(userId,setLocation))
            _bindDevice.postValue(data)
            Logger.d("  ${data}")
        }


    }

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["selected_password"])
        public  fun onSelectedPassword(layout: ShapeLinearLayout,logInBean: Boolean) {
            if (logInBean){
                layout.shapeDrawableBuilder
                    .setSolidGradientColors(layout.context.getColor(R.color.ffffff),layout.context.getColor(R.color.ffffff))
                    . setRadius(20f.dp,20f.dp,0f.dp,0f.dp)
                    .intoBackground();
            }else{
                layout.shapeDrawableBuilder
                    .setSolidGradientColors(layout.context.getColor(R.color.f44336),layout.context.getColor(R.color.f44336))
                    . setRadius(20f.dp,20f.dp,20f.dp,0f.dp)
                    .intoBackground();
            }
        }
        @JvmStatic
        @BindingAdapter(value = ["selected_code"])
        public  fun onSelectedCode(layout: ShapeLinearLayout,logInBean: Boolean) {
            if (logInBean){
                layout.shapeDrawableBuilder
                    .setSolidGradientColors(layout.context.getColor(R.color.f44336),layout.context.getColor(R.color.f44336))
                    . setRadius(20f.dp,0f.dp,0f.dp,20f.dp)
                    .intoBackground();
            }else{
                layout.shapeDrawableBuilder
                    .setSolidGradientColors(layout.context.getColor(R.color.ffffff),layout.context.getColor(R.color.ffffff))
                    . setRadius(20f.dp,20f.dp,0f.dp,0f.dp)
                    .intoBackground();
            }
        }
        @JvmStatic
        @BindingAdapter(value = ["selected_code_s"])
        public  fun onSelectedCodes(layout: ShapeLinearLayout,logInBean: Boolean) {
            if (logInBean){
                layout.shapeDrawableBuilder
                    .setSolidGradientColors(layout.context.getColor(R.color.f44336),layout.context.getColor(R.color.f44336))
                    .intoBackground();
            }else{
                layout.shapeDrawableBuilder
                    .setSolidGradientColors(layout.context.getColor(R.color.ffffff),layout.context.getColor(R.color.ffffff))
                    .intoBackground();
            }
        }
        @JvmStatic
        @BindingAdapter(value = ["selected_password_s"])
        public  fun onSelectedPasswordS(layout: ShapeLinearLayout,logInBean: Boolean) {
            if (logInBean){
                layout.shapeDrawableBuilder
                    .setSolidGradientColors(layout.context.getColor(R.color.ffffff),layout.context.getColor(R.color.ffffff))
                    .intoBackground();
            }else{
                layout.shapeDrawableBuilder
                    .setSolidGradientColors(layout.context.getColor(R.color.f44336),layout.context.getColor(R.color.f44336))
                    .intoBackground();
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["selected_send_code"])
        public  fun onSelectedSendCode(layout: ShapeTextView, logInBean: Boolean) {
            if (logInBean){
                layout.shapeDrawableBuilder
                    .setSolidGradientColors(layout.context.getColor(R.color.clorl_c),layout.context.getColor(R.color.clorl_c))
                    .intoBackground();
            }else{
                layout.shapeDrawableBuilder
                    .setSolidGradientColors(layout.context.getColor(R.color.ffffff),layout.context.getColor(R.color.ffffff))
                    .intoBackground();
            }
        }
    }
}