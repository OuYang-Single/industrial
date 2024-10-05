package com.cn.datalibrary.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.cn.datalibrary.R
import com.cn.datalibrary.adapter.PageAdapter
import com.cn.datalibrary.databinding.FragmentBataMainBinding
import com.cn.datalibrary.viewmodel.BataMainViewModel
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.ui_library.stateview.StateView
import org.koin.androidx.viewmodel.ext.android.viewModel

@Route(path = "/bata/BataMainFragment")
class BataMainFragment : MvvmBaseFragment<FragmentBataMainBinding, BataMainViewModel>() {
    override val viewModel by viewModel<BataMainViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_bata_main

    override fun onBinding() {
        viewDataBinding?.viewModel = viewModel
        viewDataBinding?.content?.let {
            mStateView = StateView.inject(it)
        }

    }

    override fun onAgainCreate() {
         observe()
        viewDataBinding?.vpContent?.offscreenPageLimit=7
        viewDataBinding?.vpContent?.setUserInputEnabled(false);

        viewModel.initModel()
    }

    override fun onAgainCreates() {

    }

    fun observe(){
        viewModel.dataTitleList.observe(this){
            if (viewDataBinding?.rvProject?.adapter==null){
                viewDataBinding?.rvProject?.adapter=viewModel.adapter
            }
            viewModel.adapter.refresh(it)
        }
        viewModel.dataFragmentList.observe(this){
          var adapter2= activity?.let { it1 -> PageAdapter(it1, it) }
           viewDataBinding?.vpContent?.adapter= adapter2
        }
        viewModel.dataFragmentList.observe(this){
            var adapter2= activity?.let { it1 -> PageAdapter(it1, it) }
            viewDataBinding?.vpContent?.adapter= adapter2
        }
        viewModel.currentItem.observe(this){
            viewDataBinding?.vpContent?.currentItem=it
        }

    }

}