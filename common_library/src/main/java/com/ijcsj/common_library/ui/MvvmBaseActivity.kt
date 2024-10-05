package com.ijcsj.common_library.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.ijcsj.common_library.BuildConfig
import com.ijcsj.common_library.util.Constant
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.common_library.viewmodel.IMvvmBaseViewModel
import com.ijcsj.ui_library.anko.countDownCoroutines
import com.ijcsj.ui_library.stateview.StateView
import com.ijcsj.ui_library.utils.AppGlobals
import com.ijcsj.ui_library.utils.ToastUtils
import com.ijcsj.ui_library.utils.immersive
import com.ijcsj.ui_library.widget.CommonTitleBar
import com.kongzue.dialogx.dialogs.WaitDialog


/**
 * 应用模块: activity
 *
 *
 * 类描述: activity抽象基类
 *
 *
 */
abstract class MvvmBaseActivity<V : ViewDataBinding?, VM : IMvvmBaseViewModel<IBaseView>?> : AppCompatActivity(), IBaseView,
    CommonTitleBar.OnTitleBarListener {
    abstract val viewModel: VM
     var viewDataBinding: V? = null
    var tap: CommonTitleBar?=null
    var isLogout=true;
    var mStateView: StateView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immersive(darkMode = false)
        Log.w("MvvmBaseActivity", "MvvmBaseActivity  viewDataBinding  $viewModel")
    /*    if (!BuildConfig.DEBUG){
             window.addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }*/
        AppGlobals.get()?.let {
            if ("com.cn.phoneapp"== it.packageName)  {
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    // 在 Android 11（API 级别 30）及以上版本中，可以直接使用 Window 的 setDecorFitsSystemWindows 方法
                    val window: Window = getWindow()
                    window.setDecorFitsSystemWindows(false)
                } else {
                    // 在 Android 10（API 级别 29）中，可以尝试使用以下方法
                    val decorView: View = getWindow().getDecorView()
                    val uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // 隐藏导航栏
                            or View.SYSTEM_UI_FLAG_IMMERSIVE // 全屏模式
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_FULLSCREEN) // 隐藏状态栏
                    decorView.systemUiVisibility = uiOptions
                }
            }
        }


        initViewModel()
        performDataBinding()
        onBinding()
        onAgainCreate(savedInstanceState)
        tap= topBarAc()
        tap?.setListener(this)

        if (devOptions(this)==1){

        }
        LiveDataBus.get().with("logout", Boolean::class.java ).observe(this){
            if (it&&isLogout){
                finish()
            }
        }
    }



    fun devOptions(context: Context): Int {
        return Settings.Secure.getInt(
            context.contentResolver,
            Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
            0
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if (null != viewModel && viewModel!!.isUiAttach) {
            viewModel?.detachUi()
        }
    }

    private fun performDataBinding() {
        if (layoutId!=-1){
            viewDataBinding = DataBindingUtil.setContentView<V>(this,layoutId)
        }else{
            viewDataBinding = getView()?.let { DataBindingUtil.bind(it) }
        }
        if (viewDataBinding!=null){
            if (bindingVariable > 0) {
                viewDataBinding?.setVariable(bindingVariable, viewModel)
            }
            viewDataBinding?.executePendingBindings()
        }
    }



    private fun initViewModel() {
         viewModel?.attachUi(this)
    }

    override fun showContent() {
        WaitDialog.dismiss()
        mStateView?.showContent()
    }
    override fun showLoading(isPage:Boolean) {
        if (isPage){
            mStateView?.showContent()
        }else{
            WaitDialog.show("加载中...");
        }
    }
    override fun showEmpty() {
        WaitDialog.dismiss()
        mStateView?.showEmpty()
    }

    override fun showRetry() {
        WaitDialog.dismiss()
        mStateView?.showRetry()
    }
     override fun LeftClick(v:View?, action:Int) {
       finish()
    }
    override fun rightClick(v:View? ,  action:Int) {

    }
    override fun showFailure(message: String) {
        toastUtils(message)
    }

    /**
     * 获取参数Variable
     */
    protected abstract val bindingVariable: Int

    @get:LayoutRes
    protected abstract val layoutId: Int

     open fun getView():View? {
        return null;
    }


    /**
     * 失败重试,重新加载事件
     */
    protected abstract fun onBinding()
    protected abstract fun onAgainCreate(savedInstanceState: Bundle?)
    override fun topBarAc(): CommonTitleBar? {
      return null;
    }

    override fun closure(intent: Intent?) {
        if (intent!=null){
            setResult(RESULT_OK, intent)
        }
        finish()
    }
    fun toastUtils(it :String){
        ToastUtils.makeText(this,it).show()
    }

    override fun onClicked(v: View?, action: Int, extra: String?) {
        when (action) {
            CommonTitleBar. ACTION_LEFT_BUTTON-> {
                LeftClick(v, action)
            }

            CommonTitleBar.ACTION_LEFT_TEXT -> {
                LeftClick(v, action)
            }

            CommonTitleBar.ACTION_RIGHT_BUTTON -> {
                rightClick(v, action)
            }
            CommonTitleBar.ACTION_RIGHT_TEXT -> {
                rightClick(v, action)
            }
        }
    }
    override fun getContexts(): Context {
        return this
    }
}