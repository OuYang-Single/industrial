package com.ijcsj.mylibrary.fragment

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.my_library.R
import com.ijcsj.my_library.databinding.FragmentMyBinding
import com.ijcsj.mylibrary.viewmodel.MyViewModel
import com.wildma.idcardcamera.camera.IDCardCamera
import org.koin.androidx.viewmodel.ext.android.viewModel
@Route(path = "/my/MyFragment")
class MyFragment: MvvmBaseFragment<FragmentMyBinding, MyViewModel>() {
    override val viewModel by viewModel<MyViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_my
    override fun onBinding() {
        viewDataBinding?.mainBean=viewModel
    }

    override fun onAgainCreate() {
        viewModel.initModel()

    }

    override fun onAgainCreates() {

    }
}