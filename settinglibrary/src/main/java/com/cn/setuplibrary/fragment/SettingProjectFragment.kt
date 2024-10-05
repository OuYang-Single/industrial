package com.cn.setuplibrary.fragment

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.launcher.ARouter
import com.cn.setuplibrary.adapter.FragmentAdapter
import com.cn.setuplibrary.adapter.PageAdapter
import com.cn.setuplibrary.viewmodel.SettingProjectViewModel
import com.cn.setuplibrary.viewmodel.SettingUserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.common_library.util.a
import com.ijcsj.stUplibrary.R
import com.ijcsj.stUplibrary.databinding.FragmentSettingProjectBinding
import com.ijcsj.stUplibrary.databinding.FragmentSettingUserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingProjectFragment  : MvvmBaseFragment<FragmentSettingProjectBinding, SettingProjectViewModel>() {
    override val viewModel by viewModel<SettingProjectViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_setting_project
    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }

    override fun onAgainCreate() {
        observe()
        var adapter2= activity?.let { it1 -> FragmentAdapter(it1, viewModel.fragmentList) }
        viewDataBinding?.viewPage2?.adapter= adapter2
        var mediator  =  TabLayoutMediator(viewDataBinding?.tabLayout!!, viewDataBinding?.viewPage2!!
        ) { tab, p1 ->
            tab.setText(viewModel.projectBaseList[p1]);
        }
        viewModel.initModel()
        // 监听页面切换事件
        viewDataBinding?.viewPage2?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
             var ad=   adapter2?.fragmentList?.get(position) as MvvmBaseFragment<*, *>
                ad?.onAgainCreates()
                // 获取当前页面和下一个页面的视图
                if (position==1){
                    var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.ENGINEERING_LOG_ON,false)
                    if (isEngineeringLogOn==false){
                        ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",1).navigation()
                    }else{
                        viewDataBinding?.tv1?.visibility= View.INVISIBLE
                        viewDataBinding?.tv2?.visibility= View.VISIBLE
                    }
                }else{
                    viewDataBinding?.tv1?.visibility= View.VISIBLE
                    viewDataBinding?.tv2?.visibility= View.INVISIBLE
                }

            }
        })
        mediator.attach();
    }

    override fun onAgainCreates() {

    }

    override fun onPause() {
        super.onPause()
        viewDataBinding?.viewPage2?.currentItem=0
    }

    fun observe(){

    }
}