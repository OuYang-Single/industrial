package com.cn.setuplibrary.fragment

import com.cn.setuplibrary.viewmodel.ChangeProjectPasswordViewModel
import com.cn.setuplibrary.viewmodel.ManufacturerParametersViewModel
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.common_library.util.a
import com.ijcsj.stUplibrary.R
import com.ijcsj.stUplibrary.databinding.FragmentManufacturerParametersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ManufacturerParametersFragment : MvvmBaseFragment<FragmentManufacturerParametersBinding, ManufacturerParametersViewModel>() {
    override val viewModel by viewModel<ManufacturerParametersViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_manufacturer_parameters
    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }

    override fun onAgainCreate() {
        observe()
        viewModel.initModel()
        var ad= ShuJuMMkV.getInstances()?.getString(a.TIME,"")
        viewModel.manufactorBean.activationDate=ad
        viewModel.manufactorBean.activationCode= "A2160$ad"
    }

    override fun onAgainCreates() {

    }

    fun observe(){

    }
}