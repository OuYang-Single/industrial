package com.ijcsj.mylibrary.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.ijcsj.common_library.ui.MvvmBaseActivity
import com.ijcsj.my_library.R
import com.ijcsj.my_library.databinding.ActivityAboutBinding
import com.ijcsj.my_library.databinding.ActivitySettingBinding
import com.ijcsj.mylibrary.viewmodel.AboutViewModel
import com.ijcsj.mylibrary.viewmodel.SettingViewModel
import com.ijcsj.ui_library.stateview.StateView
import com.ijcsj.ui_library.widget.CommonTitleBar
import com.suke.widget.SwitchButton
import org.koin.androidx.viewmodel.ext.android.viewModel
@Route(path = "/my/SettingActivity")
class SettingActivity: MvvmBaseActivity<ActivitySettingBinding, SettingViewModel>() {
    override val viewModel by viewModel<SettingViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.activity_setting

    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel

    }

    override fun onAgainCreate(savedInstanceState: Bundle?) {
        viewDataBinding?.sbSettingSizeSystem?.setChecked(viewModel.sbSettingSizeSystem())
        viewDataBinding?.sbSettingSizeSystem?.setOnCheckedChangeListener { view, isChecked -> viewModel. setSbSettingSizeSystem(isChecked) }

        viewDataBinding?.sbSettingCustomizedPlayback?.setChecked(viewModel.sbSettingCustomizedPlayback())
        viewDataBinding?.sbSettingCustomizedPlayback?.setOnCheckedChangeListener { view, isChecked -> viewModel. setSbSettingCustomizedPlayback(isChecked) }

        viewDataBinding?.sbSettingLandscapeScreenPlayback?.setChecked(viewModel.sbSettingLandscapeScreenPlayback())
        viewDataBinding?.sbSettingLandscapeScreenPlayback?.setOnCheckedChangeListener { view, isChecked -> viewModel. setSbSettingLandscapeScreenPlayback(isChecked) }

        viewDataBinding?.sbSettingInduction?.setChecked(viewModel.sbSettingInduction())
        viewDataBinding?.sbSettingInduction?.setOnCheckedChangeListener { view, isChecked -> viewModel. setSbSettingInduction(isChecked) }


        viewDataBinding?.sbSettingBarrageMemory?.setChecked(viewModel.sbSettingBarrageMemory())
        viewDataBinding?.sbSettingBarrageMemory?.setOnCheckedChangeListener { view, isChecked -> viewModel. setSbSettingBarrageMemory(isChecked) }

        viewDataBinding?.sbSettingBarrageOperation?.setChecked(viewModel.sbSettingBarrageOperation())
        viewDataBinding?.sbSettingBarrageOperation?.setOnCheckedChangeListener { view, isChecked -> viewModel. setSbSettingBarrageOperation(isChecked) }


        viewDataBinding?.sbSettingSmallScreen?.setChecked(viewModel.sbSettingSmallScreen())
        viewDataBinding?.sbSettingSmallScreen?.setOnCheckedChangeListener { view, isChecked -> viewModel. setSbSettingSmallScreen(isChecked) }
    }
    override fun topBarAc(): CommonTitleBar? {
        return viewDataBinding?.topBar;
    }
}