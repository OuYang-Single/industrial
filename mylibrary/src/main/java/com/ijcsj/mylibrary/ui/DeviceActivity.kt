 package com.ijcsj.mylibrary.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ijcsj.common_library.ui.MvvmBaseActivity
import com.ijcsj.my_library.R
import com.ijcsj.my_library.databinding.ActivityDeviceBinding
import com.ijcsj.mylibrary.adapter.DeviceAdapter
import com.ijcsj.mylibrary.viewmodel.DeviceViewModel
import com.ijcsj.mylibrary.viewmodel.MyViewModel
import com.ijcsj.ui_library.utils.immersive
import com.ijcsj.ui_library.widget.CommonTitleBar
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import org.koin.androidx.viewmodel.ext.android.viewModel
 @Route(path = "/my/DeviceActivity")
 class DeviceActivity  : MvvmBaseActivity<ActivityDeviceBinding, DeviceViewModel>() {
     override val viewModel by viewModel<DeviceViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.activity_device

     override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }

    override fun onAgainCreate(savedInstanceState: Bundle?) {
        immersive(darkMode = true)
        viewModel.initModel()
        val onRefreshLoadMoreListener =
            viewDataBinding?.srlWork?.setOnRefreshLoadMoreListener(object :
                OnRefreshLoadMoreListener {
                override fun onRefresh(refreshLayout: RefreshLayout) {
                    viewModel.page=0;
                    viewModel.getDeviceList()

                }

                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    viewModel.page++
                    viewModel.getDeviceList()
                }

            })
        viewModel.datum.observe(this){result->
            if ( viewDataBinding?.rlWork?.adapter==null){
                viewDataBinding?.rlWork?.adapter=viewModel.adapter
            }
           viewDataBinding?.srlWork?.finishRefresh();//传入false表示刷新失败
           viewDataBinding?.srlWork?.finishLoadMore();//传入false表示刷新失败
           result.doOnSuccessWithValue {
               showContent()
               viewModel.datums.clear()
              if ( it.records!=null){
                  viewModel.datums.addAll(it.records!!)
              }
               if (viewModel.datums.size<=0){
                   viewModel.datums.add( viewModel.model.getDevice())
               }
               viewModel.adapter.refresh(viewModel.datums)
           }
           result.doOnFailure { code, s ->
               showFailure("")
           }
       }
    }
     override fun topBarAc(): CommonTitleBar? {
         return viewDataBinding?.topBar;
     }

 }