package com.cn.setuplibrary.fragment

import com.cn.setuplibrary.viewmodel.ManufacturerParametersViewModel
import com.cn.setuplibrary.viewmodel.ManufacturerUpgradeViewModel
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.stUplibrary.R
import com.ijcsj.stUplibrary.databinding.FragmentManufacturerParametersBinding
import com.ijcsj.stUplibrary.databinding.FragmentManufacturerUpgradeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ManufacturerUpgradeFragment : MvvmBaseFragment<FragmentManufacturerUpgradeBinding, ManufacturerUpgradeViewModel>() {
    override val viewModel by viewModel<ManufacturerUpgradeViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_manufacturer_upgrade
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