package com.cn.datalibrary.fragment

import com.cn.datalibrary.R
import com.cn.datalibrary.databinding.FragmentInputBinding
import com.cn.datalibrary.databinding.FragmentVersionBinding
import com.cn.datalibrary.viewmodel.InputViewModel
import com.cn.datalibrary.viewmodel.VersionViewModel
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.ui_library.stateview.StateView
import org.koin.androidx.viewmodel.ext.android.viewModel

class VersionFragment: MvvmBaseFragment<FragmentVersionBinding, VersionViewModel>() {
    override val viewModel by viewModel<VersionViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_version

    override fun onBinding() {
        viewDataBinding?.viewModel = viewModel
        viewDataBinding?.content?.let {
            mStateView = StateView.inject(it)
        }

    }

    override fun onAgainCreate() {
        observe()
        viewModel.initModel()
    }

    override fun onAgainCreates() {

    }

    fun observe(){
        LiveDataBus.get().with(Socketcan.CAN_109, CanFrame::class.java).observe(this){
            if (it.can_id==265){
                viewModel.setCan109Data(it)
            }
        }
    }

}