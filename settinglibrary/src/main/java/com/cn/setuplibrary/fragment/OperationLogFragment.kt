package com.cn.setuplibrary.fragment

import com.cn.setuplibrary.viewmodel.ManufacturerUpgradeViewModel
import com.cn.setuplibrary.viewmodel.OperationLogViewModel
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.stUplibrary.R
import com.ijcsj.stUplibrary.databinding.FragmentManufacturerUpgradeBinding
import com.ijcsj.stUplibrary.databinding.FragmentOperationLogBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OperationLogFragment  : MvvmBaseFragment<FragmentOperationLogBinding, OperationLogViewModel>() {
    override val viewModel by viewModel<OperationLogViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_operation_log
    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }

    override fun onAgainCreate() {
        observe()
        viewModel.initModel()
    }

    override fun onAgainCreates() {

    }

    fun observe(){

    }
}