package com.ijcsj.common_library.popup

import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.hjq.shape.view.ShapeTextView
import com.ijcsj.common_library.R
import com.ijcsj.common_library.bean.RealStatus
import com.ijcsj.common_library.util.Constant

import com.lxj.xpopup.core.CenterPopupView

class NotRealPopup(context: Context,var death: RealStatus): CenterPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.not_real_popup
    }

    override fun onCreate() {
        super.onCreate()

        findViewById<ShapeTextView>(R.id.tv_right).setOnClickListener {
            dismiss()
            if (death.status == 0 || death.status == 5) {
                ARouter.getInstance().build(Constant.ACTIVITY_REAL_NAME_PATH).withBoolean("forceRealname",false).navigation()
            } else if (death.status == 4) {
                ARouter.getInstance().build(Constant.ACTIVITY_FACE_PATH)
                    .withBoolean("forceRealname", false).withString("name", death.info?.name)
                    .withString("idNum", death.info?.idNum).navigation()
            }
        }

    }



}