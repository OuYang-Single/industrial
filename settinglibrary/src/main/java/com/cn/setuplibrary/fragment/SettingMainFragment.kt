package com.cn.setuplibrary.fragment


import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.cn.setuplibrary.adapter.PageAdapter
import com.cn.setuplibrary.viewmodel.SettingMainViewModel
import com.ijcsj.common_library.ui.MvvmBaseFragment
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.stUplibrary.R
import com.ijcsj.stUplibrary.databinding.FragmentSettingMainBinding
import com.kongzue.dialogx.dialogs.WaitDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

@Route(path = "/setting/SettingMainFragment")
class SettingMainFragment: MvvmBaseFragment<FragmentSettingMainBinding, SettingMainViewModel>() {
    override val viewModel by viewModel<SettingMainViewModel>()
    override val bindingVariable: Int get() = 0
    override val layoutId: Int get() = R.layout.fragment_setting_main
    override fun onBinding() {
        viewDataBinding?.viewModel=viewModel
    }

    override fun onAgainCreate() {
        observe()
        viewDataBinding?.vpContent?.offscreenPageLimit=7
        viewDataBinding?.vpContent?.setUserInputEnabled(false);

        viewModel.initModel()
    }

    override fun onAgainCreates() {

    }

    fun observe(){
        viewDataBinding?.tvBut1?.text="数据导出"
        viewDataBinding?.tvBut1?.tag="1"
        viewModel.dataTitleList.observe(this){
            if (viewDataBinding?.rvProject?.adapter==null){
                viewDataBinding?.rvProject?.adapter=viewModel.adapter
            }
            viewModel.adapter.refresh(it)
        }
        viewModel.dataFragmentList.observe(this){
            var adapter2= activity?.let { it1 -> PageAdapter(it1, it) }
            viewDataBinding?.vpContent?.adapter= adapter2
        }
        viewModel.dataFragmentList.observe(this){
            var adapter2= activity?.let { it1 -> PageAdapter(it1, it) }
            viewDataBinding?.vpContent?.adapter= adapter2
        }
        viewModel.currentItem.observe(this){
            viewDataBinding?.vpContent?.currentItem=it
            if (it==1){
                viewDataBinding?.llOperate?.visibility= View.GONE
            }else{
                if (it==0){
                    viewDataBinding?.tvBut1?.text="数据导出"
                    viewDataBinding?.tvBut1?.tag="1"
                    viewDataBinding?.tvBut3?.visibility= View.GONE
                }else{
                    viewDataBinding?.tvBut1?.text="参数初始化"
                    viewDataBinding?.tvBut1?.tag="2"
                }
                viewDataBinding?.llOperate?.visibility= View.VISIBLE
            }
        }
        LiveDataBus.get().with("onClickBut3", Boolean::class.java ).observe(this){
            if (it){
                viewDataBinding?.tvBut3?.visibility= View.VISIBLE
            }else{
                viewDataBinding?.tvBut3?.visibility= View.GONE
            }

        }
        viewModel.dataTitle.observe(this){
            WaitDialog.dismiss(600)
            if (it){
                toastUtils("表格生成完成")
            }else{
                toastUtils("表格生成失败")
            }

        }

    }
}