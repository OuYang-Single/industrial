
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
import com.ijcsj.my_library.databinding.ActivityAboutLittleGooseBinding
import com.ijcsj.mylibrary.viewmodel.AboutViewModel
import com.ijcsj.ui_library.widget.CommonTitleBar
import org.koin.androidx.viewmodel.ext.android.viewModel
@Route(path = "/my/AboutLittleGooseActivity")
class AboutLittleGooseActivity: MvvmBaseActivity<ActivityAboutLittleGooseBinding, AboutViewModel>() {
    override val viewModel by viewModel<AboutViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.activity_about_little_goose

    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }

    override fun onAgainCreate(savedInstanceState: Bundle?) {
        viewModel.getLittleGooseAbout()
        viewModel.littleGooseAbout.observe(this){ result->
            result.doOnSuccessWithValue {
                showContent()
                viewDataBinding?.data=it;
            }
            result.doOnFailure { code, s ->
                showRetry()
            }
        }
    }
    override fun topBarAc(): CommonTitleBar? {
        return viewDataBinding?.topBar;
    }
}