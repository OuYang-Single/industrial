package com.ijcsj.mylibrary.viewmodel

import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.ijcsj.common_library.bean.AboutBean
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.popup.FailPopup
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.Constant
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.mylibrary.model.MyModel
import com.ijcsj.mylibrary.popup.LogOffPopup
import com.ijcsj.common_library.popup.NotRealPopup
import com.ijcsj.common_library.popup.SuccessRealPopup
import com.ijcsj.common_library.util.Constant.ACTIVITY_SETTING_PATH
import com.ijcsj.service_library.bean.ApifoxModel
import com.ijcsj.service_library.login.LoginServiceProvider
import com.kongzue.dialogx.dialogs.WaitDialog
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MyViewModel(override val model: MyModel) : MvmBaseViewModel<IBaseView, MyModel>(){
    var data: ApifoxModel?=null;

    public override fun initModel() {
        data= LoginServiceProvider.getUserProfile()
        var aboutBean= ShuJuMMkV.instance?.getAny(Constant.ABOUT_DATA, AboutBean::class.java)
        if (aboutBean==null){
            viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            }) {
                var  result=  model.getAbout()
                result.doOnSuccessWithValue {
                    ShuJuMMkV.instance?.putAny(Constant.ABOUT_DATA,it)
                }
            }
        }
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        }) {
            var  result=  model.tximInit()

        }
    }

    var onDeviceClick=  BindingCommand<BindingAction>{
        ARouter.getInstance().build(Constant.ACTIVITY_DEVICE_PATH).navigation()

    }
    var onDeviceClicks=  BindingCommand<BindingAction>{
        ARouter.getInstance().build("/h5/home")
            .withString("url","file:///android_asset/editor.html").withString("title","dff").navigation()
    }
    var onAboutClick=  BindingCommand<BindingAction>{
        ARouter.getInstance().build(Constant.ACTIVITY_ABOUT_PATH).navigation()
    }

    var onAboutLittleGooseClick=  BindingCommand<BindingAction>{
        ARouter.getInstance().build(Constant.ACTIVITY_ABOUT_LITTLE_GOOSE_PATH).navigation()
    }

    var onLogOutClick=  BindingCommand<BindingAction>{
        XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .positionByWindowCenter(false)
            .asCustom(LogOffPopup(it.context) { v ->
                WaitDialog.show("退出中...");
                viewModelScope.launch(CoroutineExceptionHandler { _, e ->
                    getPageView()?.showFailure("退出失败，请重试")
                }) {
                    WaitDialog.dismiss();
                    var  result=  model.logOut()
                    result.doOnSuccessWithMsg {
                        LoginServiceProvider.logout()
                        getPageView()?.showFailure(result.msg)
                        getPageView()?.closure(null)
                        getPageView()?.showFailure("退出成功")
                        ARouter.getInstance().build("/LogIn/LogInActivity").navigation()
                    }
                    result.doOnFailure { code, s ->
                        getPageView()?.showFailure("退出失败，请重试")
                    }
                }
            }).show()
    }

    var onFeedbackKClick=  BindingCommand<BindingAction>{
        ARouter.getInstance().build(Constant.ACTIVITY_FEEDBACK_PATH).navigation()
    }
    var onHelpClick=  BindingCommand<BindingAction>{
      var helpUrl=  ShuJuMMkV.instance?.getString(Constant.HELPER_URL)
       if ( TextUtils.isEmpty(helpUrl)){
           WaitDialog.show("刷新中...");
           viewModelScope.launch(CoroutineExceptionHandler { _, e ->
               WaitDialog.dismiss();
           }) {
               WaitDialog.dismiss();
              var  result=  model.getHelpUrl()
               result.doOnSuccessWithValue {
                   ShuJuMMkV.instance?.putString(Constant.HELPER_URL,it.helper_url)
                   ARouter.getInstance().build("/h5/home")
                       .withString("url",it.helper_url).withString("title","帮助中心").navigation()
               }
               result.doOnFailure { code, s ->
                   getPageView()?.showFailure("刷新失败，请重试")
               }
           }
       }else{
           ARouter.getInstance().build("/h5/home")
               .withString("url",helpUrl).withString("title","帮助中心").navigation()
       }
    }

    var onSettingClick=  BindingCommand<BindingAction>{
        ARouter.getInstance().build(ACTIVITY_SETTING_PATH).navigation()
    }

    var onRealClick=  BindingCommand<BindingAction>{view->
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        }) {
            var  result=  model.getRealStatus()
            result.doOnSuccessWithValue {
                var dialog: BasePopupView?=null;
                if (it.status==0||it.status==5||it.status==4){
                    dialog=  NotRealPopup(view.context,it)
                }else if (it.status==3){
                    _message.postValue("实名超过次数，禁止实名，请联系客服")
                    return@doOnSuccessWithValue
                }else if (it.status==1){
                    dialog=  SuccessRealPopup(view.context,it)
                }else if (it.status==2){
                    dialog=  FailPopup(view.context,it)
                }
                if (dialog==null){
                    return@doOnSuccessWithValue
                }
                 XPopup.Builder(view.context)
                    .enableDrag(false)
                    .autoDismiss(false)
                    .dismissOnTouchOutside(false)
                    .dismissOnBackPressed(false)
                    .positionByWindowCenter(true)
                    .asCustom(dialog)
                    .show()
            }
        }

    }


}