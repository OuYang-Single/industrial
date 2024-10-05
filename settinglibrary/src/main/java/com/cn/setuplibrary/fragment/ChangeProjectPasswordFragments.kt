package com.cn.setuplibrary.fragment

import com.cn.setuplibrary.viewmodel.ChangeProjectPasswordViewModel
import com.cn.setuplibrary.viewmodel.EngineeringParametersViewModel
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.stUplibrary.R
import com.ijcsj.stUplibrary.databinding.FragmentChangeProjectPasswordBinding
import com.ijcsj.stUplibrary.databinding.FragmentChangeProjectPasswordSBinding
import com.ijcsj.stUplibrary.databinding.FragmentEngineeringParametersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangeProjectPasswordFragments : MvvmBaseFragment<FragmentChangeProjectPasswordSBinding, ChangeProjectPasswordViewModel>() {
    override val viewModel by viewModel<ChangeProjectPasswordViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_change_project_password_s
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
        viewModel.message.observe(this){
            toastUtils(it)
        }
    }
}