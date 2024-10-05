package com.ijcsj.mylibrary.ui

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.ijcsj.common_library.bean.FileUpload
import com.ijcsj.common_library.ui.MvvmBaseActivity
import com.ijcsj.my_library.R
import com.ijcsj.my_library.databinding.ActivityAboutBinding
import com.ijcsj.my_library.databinding.ActivityFeedbackBinding
import com.ijcsj.mylibrary.viewmodel.AboutViewModel
import com.ijcsj.mylibrary.viewmodel.FeedbackViewModel
import com.ijcsj.ui_library.stateview.StateView
import com.ijcsj.ui_library.widget.CommonTitleBar
import com.ijcsj.ui_library.widget.ngv.DefaultNgvAdapter
import com.ijcsj.ui_library.widget.ngv.NgvChildImageView
import com.kongzue.dialog.v3.WaitDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
@Route(path = "/my/AboutActivity")
class AboutActivity: MvvmBaseActivity<ActivityAboutBinding, AboutViewModel>() {
    override val viewModel by viewModel<AboutViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.activity_about

    override fun onBinding() {
       viewDataBinding?.viewModel=viewModel
        viewDataBinding?.content?.let {
            mStateView= StateView.inject(it)
        }
    }

    override fun onAgainCreate(savedInstanceState: Bundle?) {
        viewModel.getAbout()
        viewModel.about.observe(this){ result->
            result.doOnSuccessWithValue {
                showContent()
                viewDataBinding?.data=it;
                viewModel.data=it
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