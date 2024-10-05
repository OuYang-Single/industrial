package com.cn.setuplibrary.viewmodel

import com.cn.setuplibrary.bean.ManufactorBean
import com.cn.setuplibrary.model.EngineeringParametersModel
import com.cn.setuplibrary.model.ManufacturerParametersModel
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel

class ManufacturerParametersViewModel (override val model: ManufacturerParametersModel,var manufactorBean: ManufactorBean) : MvmBaseViewModel<IBaseView, ManufacturerParametersModel>(){
    public override fun initModel() {

    }
}