package com.ijcsj.web_library.viewmodel

import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.web_library.model.WebModel

class WebViewModel(override val model: WebModel) : MvmBaseViewModel<IBaseView, WebModel>(){

    public override fun initModel() {
    }
}