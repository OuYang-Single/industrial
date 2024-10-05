package com.ijcsj.common_library.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.ijcsj.common_library.R
import com.ijcsj.common_library.viewmodel.IMvvmBaseViewModel
import com.ijcsj.ui_library.stateview.StateView
import com.ijcsj.ui_library.utils.AppGlobals
import com.ijcsj.ui_library.utils.ToastUtils
import com.ijcsj.ui_library.widget.CommonTitleBar
import com.kongzue.dialogx.dialogs.WaitDialog

/**
 * 应用模块:fragment
 *
 *
 * 类描述: 基类fragment
 */
abstract class MvvmBaseFragment<V : ViewDataBinding?, VM : IMvvmBaseViewModel<IBaseView>?> : Fragment(),
    IBaseView {
     var viewDataBinding: V? = null
      abstract val viewModel: VM
     var mFragmentTag: String = this.javaClass.simpleName
    private var isFirstLoad = true // 是否第一次加载

   var mStateView: StateView?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (layoutId!=-1){
            viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        }else{
            viewDataBinding = getViews()?.let { DataBindingUtil.bind(it) }
        }

        return viewDataBinding?.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        ARouter.getInstance().inject(this)
        super.onViewCreated(view, savedInstanceState)
        if (null != viewModel) {
            viewModel?.attachUi(this)
        }
        if (bindingVariable > 0) {
            viewDataBinding?.setVariable(bindingVariable, viewModel)
            viewDataBinding?.executePendingBindings()
        }
        onBinding()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        onAgainCreates()
        if(isFirstLoad){
            onAgainCreate()
            isFirstLoad = false;
        }
        activity?.let {
            if ("com.cn.phoneapp"== it.packageName)  {
            }else{
                hideBottomNavigationBar(it.window)
            }
        }
    }
    fun hideBottomNavigationBar(window: Window) {
        // 隐藏导航栏
        // 设置沉浸式模式
        // 设置沉浸式模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 在 Android 11（API 级别 30）及以上版本中，可以直接使用 Window 的 setDecorFitsSystemWindows 方法
            window.setDecorFitsSystemWindows(false)
        } else {
            // 在 Android 10（API 级别 29）中，可以尝试使用以下方法
            val decorView: View = window.getDecorView()
            val uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // 隐藏导航栏
                    or View.SYSTEM_UI_FLAG_IMMERSIVE // 全屏模式
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_FULLSCREEN) // 隐藏状态栏
            decorView.systemUiVisibility = uiOptions
        }
    }
    override fun onPause() {
        super.onPause()
    }
    protected abstract fun onBinding()
    override fun onDestroyView() {
        super.onDestroyView()
    }
    fun toastUtils(it :String){
        ToastUtils.makeText(context,it).show()
    }
    override fun onDestroy() {
        super.onDestroy()
        if (null != viewModel && viewModel!!.isUiAttach) {
            viewModel!!.detachUi()
        }
    }

    override fun onDetach() {
        super.onDetach()
        if (null != viewModel && viewModel!!.isUiAttach) {
            viewModel!!.detachUi()
        }
    }




    private val isShowedContent = false
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
    override fun showFailure(message: String) {
        ToastUtils.makeText(context,message).show()
    }


    fun setLoadSir(view: View?) {}

    @get:LayoutRes
    abstract val layoutId: Int

    open fun getViews():View? {
        return null;
    }
    override fun LeftClick(v: View?, action: Int) {

    }

    override fun rightClick(v: View?, action: Int) {

    }
    /**
     * 获取参数
     */
    abstract val bindingVariable: Int

    override fun topBarAc(): CommonTitleBar? {
        return null;
    }
    override fun closure(intent: Intent?) {

    }
    override fun getContexts(): Context? {

        return context
    }
    protected abstract fun onAgainCreate()
    abstract fun onAgainCreates()
}