package com.cn.setuplibrary.fragment

import com.cn.setuplibrary.viewmodel.OperationLogViewModel
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.stUplibrary.R
import com.ijcsj.stUplibrary.databinding.FragmentOperationLogBinding
import com.ijcsj.ui_library.stateview.StateView
import com.kongzue.dialogx.dialogs.WaitDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class OperationLogFragment  : MvvmBaseFragment<FragmentOperationLogBinding, OperationLogViewModel>() {
    override val viewModel by viewModel<OperationLogViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_operation_log
    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
        viewDataBinding?.content?.let {
            mStateView = StateView.inject(it)
        }
    }

    override fun onAgainCreate() {
        mStateView?.showLoading()
        observe()
        viewModel.initModel()
    }

    override fun onAgainCreates() {

    }

    fun observe(){
        viewModel.historyBaseList.observe(this){
            WaitDialog.dismiss(600);
            viewDataBinding?.rvHistory?.adapter=viewModel.adapter
            mStateView?.showContent()
            viewModel.adapter.refresh(it!!)
        }
        LiveDataBus.get().with("CanWrites", Boolean::class.java).observe(this){
            viewModel.initModel()
        }
      viewModel.historyBaseBgList.observe(this){
            if (viewDataBinding?.rvHistorys?.adapter==null){
                viewDataBinding?.rvHistorys?.adapter=viewModel.adapterBg
            }
            viewModel.adapterBg.refresh(it)
        }
    }
}