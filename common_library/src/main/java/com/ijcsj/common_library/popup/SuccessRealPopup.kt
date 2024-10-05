package com.ijcsj.common_library.popup

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ijcsj.common_library.R
import com.ijcsj.common_library.bean.RealStatus
import com.ijcsj.service_library.login.LoginServiceProvider
import com.ijcsj.ui_library.utils.ScreenUtils
import com.lxj.xpopup.core.CenterPopupView

class SuccessRealPopup(context: Context,var death: RealStatus): CenterPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.success_real_popup
    }

    override fun onCreate() {
        super.onCreate()
       var aRouter= LoginServiceProvider.getUserProfile()
      var img_crop=  findViewById<ImageView>(R.id.img_crop)

        //使用Glide框架加载图片
        Glide.with(img_crop.context)
            .load(aRouter?.userInfo?.avatar)
            .error(com.ijcsj.ui_library.R.mipmap.ic_default_avatar)
            .placeholder(com.ijcsj.ui_library.R.mipmap.ic_default_avatar)
            .into(img_crop)
        if (death.info!=null&&death.info.name!=null){
            (findViewById<TextView>(R.id.tv_name) as TextView).text=death.info.name
        }else{
            (findViewById<TextView>(R.id.tv_name) as TextView).text="--"
        }
        if (death.info!=null&&death.info.idNum!=null){
            (findViewById<TextView>(R.id.tv_name) as TextView).text=death.info.idNum
        }else{
            findViewById<TextView>(R.id.tv_ip).text="--"
        }

        findViewById<ImageView>(R.id.tv_right).setOnClickListener {
            dismiss()
        }

    }



}