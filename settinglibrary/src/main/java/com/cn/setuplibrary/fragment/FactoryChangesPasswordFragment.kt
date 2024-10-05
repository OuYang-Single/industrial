package com.cn.setuplibrary.fragment

import com.cn.setuplibrary.viewmodel.FactoryChangesPasswordViewModel
import com.cn.setuplibrary.viewmodel.ManufacturerDebuggingViewModel
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.stUplibrary.R
import com.ijcsj.stUplibrary.databinding.FragmentFactoryChangesPasswordBinding
import com.ijcsj.stUplibrary.databinding.FragmentManufacturerDebuggingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FactoryChangesPasswordFragment: MvvmBaseFragment<FragmentFactoryChangesPasswordBinding, FactoryChangesPasswordViewModel>() {
    override val viewModel by viewModel<FactoryChangesPasswordViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_factory_changes_password
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