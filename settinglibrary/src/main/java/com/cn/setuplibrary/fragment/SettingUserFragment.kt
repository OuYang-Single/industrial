package com.cn.setuplibrary.fragment

import com.cn.setuplibrary.adapter.PageAdapter
import com.cn.setuplibrary.viewmodel.SettingMainViewModel
import com.cn.setuplibrary.viewmodel.SettingUserViewModel
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.common_library.util.BacklightController
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.common_library.util.a
import com.ijcsj.stUplibrary.R
import com.ijcsj.stUplibrary.databinding.FragmentSettingUserBinding
import com.ijcsj.ui_library.utils.ToastUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingUserFragment : MvvmBaseFragment<FragmentSettingUserBinding, SettingUserViewModel>() {
    override val viewModel by viewModel<SettingUserViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_setting_user
    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.initModel()
        viewModel.initTimeOut( BacklightController.getScreenOffTimeout(context?.contentResolver))
    }
    override fun onAgainCreate() {
        observe()
    }

    override fun onAgainCreates() {

    }

    fun observe(){
        viewModel.message.observe(this){
            toastUtils(it)
        }
        LiveDataBus.get().with("WORKING_MODEDD", Boolean::class.java).observe(this){
            viewModel.initModel()
        }
    }
}