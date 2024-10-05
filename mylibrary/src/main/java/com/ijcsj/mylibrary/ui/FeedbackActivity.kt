package com.ijcsj.mylibrary.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.ijcsj.common_library.bean.FileUpload
import com.ijcsj.common_library.ui.MvvmBaseActivity
import com.ijcsj.my_library.R
import com.ijcsj.my_library.databinding.ActivityFeedbackBinding
import com.ijcsj.mylibrary.viewmodel.FeedbackViewModel
import com.ijcsj.ui_library.anko.dp
import com.ijcsj.ui_library.widget.CommonTitleBar
import com.ijcsj.ui_library.widget.ngv.DefaultNgvAdapter
import com.ijcsj.ui_library.widget.ngv.NgvChildImageView
import com.kongzue.dialog.v3.WaitDialog
import org.koin.androidx.viewmodel.ext.android.viewModel


@Route(path = "/my/FeedbackActivity")
class FeedbackActivity : MvvmBaseActivity<ActivityFeedbackBinding, FeedbackViewModel>() {
    override val viewModel by viewModel<FeedbackViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.activity_feedback

    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }

    override fun onAgainCreate(savedInstanceState: Bundle?) {
        viewModel.initModel()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)


        viewDataBinding?.root?.viewTreeObserver?.addOnGlobalLayoutListener {
            val r = Rect()
            viewDataBinding?.let {
                 it.root.getWindowVisibleDisplayFrame(r)
                val rect = Rect() //1、获取main在窗体的可视区域
                val location = IntArray(2)
                val screenHeight = it.root.rootView.height
                val keypadHeight = screenHeight - r.bottom
                if (keypadHeight > screenHeight * 0.15) { // 0.15是一个阈值，用于判断是否是键盘高度
                    it.nsv.getLocationInWindow(location) // 4､获取Scroll的窗体坐标，算出main需要滚动的高度

                    val srollHeight: Int = location.get(1) +  it.nsv.height  //5､让界面整体上移键盘的高度

                    it.nsv.scrollTo(0, srollHeight)// 在这里调整布局，使得输入框不被遮挡
                } else {
                    it.nsv.scrollTo(0, 0);
                    // 键盘可能是不可见的
                }
            }
        }
        viewModel.datum.observe(this){
           it?.let {
               if ( viewDataBinding?.rlWork?.adapter==null){
                   viewDataBinding?.rlWork?.adapter=viewModel.adapter
               }
               viewModel.adapter.refresh(it)
           }
       }

        viewModel.mAdapter.setOnChildClickListener(object : DefaultNgvAdapter.OnChildClickedListener<FileUpload> {
            override fun onPlusImageClicked(plusImageView: ImageView?, dValueToLimited: Int) {
            }

            override fun onImageDeleted(position: Int, data: FileUpload?) {
            }

            override fun onContentImageClicked(
                position: Int,
                data: FileUpload?,
                childImageView: NgvChildImageView?
            ) {
             viewModel.fileUploadList.add(data?.fileKey)
            }

        })
        viewDataBinding?.ninegridview?.setAdapter(  viewModel.mAdapter)

        viewModel.imgBean.observe(this){result->
            WaitDialog.dismiss()
            result.doOnSuccessWithValue {
                val fileUpload=  FileUpload()
                viewModel.mAdapter.addData(fileUpload)
            }
            result.doOnFailure { code, s ->
                toastUtils("上传失败，请重试")
            }
       }
        viewModel.submitBean.observe(this){result->
            WaitDialog.dismiss()
            result.doOnSuccessWithValue {
                toastUtils("提交成功")
                finish()
            }
            result.doOnFailure { code, s ->
                toastUtils("提交失败，请重试")
            }
        }
    }
    override fun topBarAc(): CommonTitleBar? {
        return viewDataBinding?.topBar;
    }
}