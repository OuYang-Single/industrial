package com.cn.setuplibrary.fragment

import com.cn.setuplibrary.viewmodel.ManufacturerDebuggingViewModel
import com.cn.setuplibrary.viewmodel.ManufacturerParametersViewModel
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.stUplibrary.R
import com.ijcsj.stUplibrary.databinding.FragmentManufacturerDebuggingBinding
import com.ijcsj.stUplibrary.databinding.FragmentManufacturerParametersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ManufacturerDebuggingFragment: MvvmBaseFragment<FragmentManufacturerDebuggingBinding, ManufacturerDebuggingViewModel>() {
    override val viewModel by viewModel<ManufacturerDebuggingViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_manufacturer_debugging
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