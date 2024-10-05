package com.cn.setuplibrary.viewmodel

import com.cn.setuplibrary.model.ManufacturerParametersModel
import com.cn.setuplibrary.model.OperationLogModel
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel

class OperationLogViewModel  (override val model: OperationLogModel) : MvmBaseViewModel<IBaseView, OperationLogModel>(){
    public override fun initModel() {

    }
}