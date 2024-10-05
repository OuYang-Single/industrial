package com.cn.datalibrary.fragment

import com.cn.datalibrary.R
import com.cn.datalibrary.databinding.FragmentHistoryBinding
import com.cn.datalibrary.databinding.FragmentVersionBinding
import com.cn.datalibrary.viewmodel.HistoryViewModel
import com.cn.datalibrary.viewmodel.VersionViewModel
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.ui_library.stateview.StateView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment: MvvmBaseFragment<FragmentHistoryBinding, HistoryViewModel>() {
    override val viewModel by viewModel<HistoryViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_history

    override fun onBinding() {
        viewDataBinding?.viewModel = viewModel
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
            viewDataBinding?.rvHistory?.adapter=viewModel.adapter
            mStateView?.showContent()
            viewModel.adapter.currentPage=viewModel.currentPage
            viewModel.adapter.refresh(it)
        }
        viewModel.historyBaseBgList.observe(this){
            if (viewDataBinding?.rvHistorys?.adapter==null){
                viewDataBinding?.rvHistorys?.adapter=viewModel.adapterBg
            }
            viewModel.adapterBg.refresh(it)
        }
    }

}