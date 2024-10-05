package com.ijcsj.mylibrary.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.ijcsj.common_library.bean.FileUpload
import com.ijcsj.common_library.ui.MvvmBaseActivity
import com.ijcsj.my_library.R
import com.ijcsj.my_library.databinding.ActivityRealNameBinding
import com.ijcsj.mylibrary.viewmodel.AboutViewModel
import com.ijcsj.mylibrary.viewmodel.RealNameViewModel
import com.ijcsj.ui_library.widget.CommonTitleBar
import com.kongzue.dialogx.dialogs.WaitDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
@Route(path = "/my/RealNameActivity")
class RealNameActivity : MvvmBaseActivity<ActivityRealNameBinding, RealNameViewModel>() {
    override val viewModel by viewModel<RealNameViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.activity_real_name
   var forceRealname:Boolean=false;
    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }

    override fun onAgainCreate(savedInstanceState: Bundle?) {
        forceRealname=  intent.getBooleanExtra("forceRealname",false)
        viewModel.initModel()
        viewModel.forceRealname=forceRealname;
        viewDataBinding?.topBar?.leftTextView?.visibility=if (forceRealname) View.GONE else View.VISIBLE

        viewModel.imgBean.observe(this){result->
            WaitDialog.dismiss()
            result.doOnSuccessWithValue {

            }
            result.doOnFailure { code, s ->
                if (TextUtils.isEmpty(s)){
                    toastUtils("上传失败，请重试")
                }else{
                    toastUtils(s!!)
                }

            }
        }
    }
    @Deprecated("Deprecated in Java")
    @SuppressLint("MissingSuperCall")
    override fun  onBackPressed( ) {
        if (!forceRealname) {
            finish()
        }
    }
    override fun topBarAc(): CommonTitleBar? {
        return viewDataBinding?.topBar;
    }
}