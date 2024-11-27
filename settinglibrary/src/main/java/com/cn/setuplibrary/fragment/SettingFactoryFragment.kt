package com.cn.setuplibrary.fragment

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.launcher.ARouter
import com.cn.setuplibrary.adapter.FragmentAdapter
import com.cn.setuplibrary.viewmodel.SettingFactoryViewModel
import com.cn.setuplibrary.viewmodel.SettingUserViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.common_library.util.a
import com.ijcsj.stUplibrary.R
import com.ijcsj.stUplibrary.databinding.FragmentSettingFactoryBinding
import com.ijcsj.stUplibrary.databinding.FragmentSettingUserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFactoryFragment : MvvmBaseFragment<FragmentSettingFactoryBinding, SettingFactoryViewModel>() {
    override val viewModel by viewModel<SettingFactoryViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_setting_factory
    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }
    var boolean=false;

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
                when (position){
                    0->{
                        viewDataBinding?.tv1?.visibility= View.VISIBLE
                        viewDataBinding?.tv2?.visibility= View.INVISIBLE
                        viewDataBinding?.tv3?.visibility= View.INVISIBLE
                        viewDataBinding?.tv4?.visibility= View.INVISIBLE
                        viewDataBinding?.tv5?.visibility= View.INVISIBLE
                    }
                    1->{
                        viewDataBinding?.tv1?.visibility= View.INVISIBLE
                        viewDataBinding?.tv2?.visibility= View.VISIBLE
                        viewDataBinding?.tv3?.visibility= View.INVISIBLE
                        viewDataBinding?.tv4?.visibility= View.INVISIBLE
                        viewDataBinding?.tv5?.visibility= View.INVISIBLE
                    }
                    2->{
                        viewDataBinding?.tv1?.visibility= View.INVISIBLE
                        viewDataBinding?.tv2?.visibility= View.INVISIBLE
                        viewDataBinding?.tv3?.visibility= View.VISIBLE
                        viewDataBinding?.tv4?.visibility= View.INVISIBLE
                        viewDataBinding?.tv5?.visibility= View.INVISIBLE
                    }
                    3->{
                        viewDataBinding?.tv1?.visibility= View.INVISIBLE
                        viewDataBinding?.tv2?.visibility= View.INVISIBLE
                        viewDataBinding?.tv3?.visibility= View.INVISIBLE
                        viewDataBinding?.tv4?.visibility= View.VISIBLE
                        viewDataBinding?.tv5?.visibility= View.INVISIBLE
                    }
                    4->{
                        var isEngineeringLogOn=  ShuJuMMkV.getInstances()?.getBoolean(a.MANUFACTOR_LOG_ON,false)
                        if (isEngineeringLogOn==false){
                            boolean=true;
                            ARouter.getInstance().build("/LogIn/Machine/LogInActivity").withInt("type",2).navigation()
                        //    viewDataBinding?.viewPage2?.currentItem=0
                        }else{
                            viewDataBinding?.tv1?.visibility= View.INVISIBLE
                            viewDataBinding?.tv2?.visibility= View.INVISIBLE
                            viewDataBinding?.tv3?.visibility= View.INVISIBLE
                            viewDataBinding?.tv4?.visibility= View.INVISIBLE
                            viewDataBinding?.tv5?.visibility= View.VISIBLE
                        }

                    }
                }
            }
        })
        mediator.attach();
    }

    override fun onAgainCreates() {

    }

    override fun onResume() {
        super.onResume()
        if (boolean){
            boolean=false;
            viewDataBinding?.viewPage2?.currentItem=0
        }
    }
    override fun onPause() {
        super.onPause()

    }

    fun observe(){

    }
}