package com.ijcsj.inlet_library.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.MvvmBaseActivity
import com.ijcsj.inlet_library.R
import com.ijcsj.inlet_library.databinding.ActivityInletBinding
import com.ijcsj.inlet_library.viewmodel.InletViewModel
import com.ijcsj.ui_library.utils.immersive
import com.orhanobut.logger.Logger
import org.koin.androidx.viewmodel.ext.android.viewModel
@Route(path = "/Inlet/InletActivity")
class InletActivity : MvvmBaseActivity<ActivityInletBinding, InletViewModel>() {
    override val viewModel by viewModel<InletViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() =R.layout.activity_inlet

    override fun onBinding() {

        viewDataBinding?.viewModel=viewModel
    }

    override fun onAgainCreate(savedInstanceState: Bundle?) {
        immersive(darkMode = false)
        Logger.w("App $this $viewModel");
        if (viewModel!=null){
            viewModel.initModel()
        }
        finish()
    }


}