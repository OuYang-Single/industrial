package com.cn.datalibrary.fragment

import androidx.databinding.ObservableList
import androidx.recyclerview.widget.SimpleItemAnimator
import com.alibaba.android.arouter.facade.annotation.Route
import com.cn.datalibrary.R
import com.cn.datalibrary.databinding.FragmentBataBinding
import com.cn.datalibrary.databinding.FragmentBataMainBinding
import com.cn.datalibrary.viewmodel.BataMainViewModel
import com.cn.datalibrary.viewmodel.BataViewModel
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.bean.DataTitle
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.ui_library.stateview.StateView
import org.koin.androidx.viewmodel.ext.android.viewModel

class DataFragment : MvvmBaseFragment<FragmentBataBinding, BataViewModel>() {
    override val viewModel by viewModel<BataViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_bata

    override fun onBinding() {
        viewDataBinding?.viewModel = viewModel
        viewDataBinding?.content?.let {
            mStateView = StateView.inject(it)
        }

    }

    override fun onAgainCreate() {
        observe()
        ( viewDataBinding?.rvProject?.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false //取消刷新闪屏动画
        viewModel.initModel()
    }

    override fun onAgainCreates() {

    }

    fun observe(){
        viewModel.dataTitleList.observe(this){
           if ( viewDataBinding?.rvProject?.adapter==null){
               viewDataBinding?.rvProject?.adapter=viewModel.adapter
           }
            viewModel.adapter.refresh(it)
            viewModel.list=  ( viewModel.adapter.datas as ObservableList<DataTitle>)
        }
        LiveDataBus.get().with(Socketcan.CAN_099, CanFrame::class.java).observe(this){
            if (it.can_id==153){
                viewModel.setCan099Data(it)
            }

        }
        LiveDataBus.get().with(Socketcan.CAN_100, CanFrame::class.java).observe(this){
            if (it.can_id==256){
                viewModel.setCan100Data(it)
            }
        }

    }

}