package com.cn.setuplibrary.di

import com.cn.setuplibrary.adapter.DataTitleAdapter
import com.cn.setuplibrary.adapter.HistoryAdapterBgs
import com.cn.setuplibrary.adapter.OperationAdapter
import com.cn.setuplibrary.model.SettingMainModel
import com.cn.setuplibrary.viewmodel.SettingMainViewModel
import com.cn.setuplibrary.api.ApiRepository
import com.cn.setuplibrary.api.RequestService
import com.cn.setuplibrary.bean.DataBase
import com.cn.setuplibrary.bean.DebuggingSettingsBean
import com.cn.setuplibrary.bean.EngineeringBean
import com.cn.setuplibrary.bean.ManufactorBean
import com.cn.setuplibrary.bean.PasswordBean
import com.cn.setuplibrary.bean.SettingUserBean
import com.cn.setuplibrary.model.ChangeProjectPasswordModel
import com.cn.setuplibrary.model.EngineeringParametersModel
import com.cn.setuplibrary.model.FactoryChangesPasswordModel
import com.cn.setuplibrary.model.ManufacturerDebuggingModel
import com.cn.setuplibrary.model.ManufacturerParametersModel
import com.cn.setuplibrary.model.ManufacturerUpgradeModel
import com.cn.setuplibrary.model.OperationLogModel
import com.cn.setuplibrary.model.SettingFactoryModel
import com.cn.setuplibrary.model.SettingProjectModel
import com.cn.setuplibrary.model.SettingUserModel
import com.cn.setuplibrary.viewmodel.ChangeProjectPasswordViewModel
import com.cn.setuplibrary.viewmodel.EngineeringParametersViewModel
import com.cn.setuplibrary.viewmodel.FactoryChangesPasswordViewModel
import com.cn.setuplibrary.viewmodel.ManufacturerDebuggingViewModel
import com.cn.setuplibrary.viewmodel.ManufacturerParametersViewModel
import com.cn.setuplibrary.viewmodel.ManufacturerUpgradeViewModel
import com.cn.setuplibrary.viewmodel.OperationLogViewModel
import com.cn.setuplibrary.viewmodel.SettingFactoryViewModel
import com.cn.setuplibrary.viewmodel.SettingProjectViewModel
import com.cn.setuplibrary.viewmodel.SettingUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

var SettingModule =module {

    viewModel {
        SettingMainViewModel(get(),get())
    }

    viewModel {
        SettingUserViewModel(get(),get(),get())
    }
    viewModel {
        SettingProjectViewModel(get())
    }
    viewModel {
        SettingFactoryViewModel(get())
    }
    viewModel {
        EngineeringParametersViewModel(get(),get())
    }
    viewModel {
        ChangeProjectPasswordViewModel(get(),get())
    }
    viewModel {
        ManufacturerDebuggingViewModel(get(),get())
    }

    viewModel {
        ManufacturerParametersViewModel(get(),get())
    }
    viewModel {
        ManufacturerUpgradeViewModel(get())
    }
    viewModel {
        OperationLogViewModel(get(),get (),get(),get())
    }
    viewModel {
        FactoryChangesPasswordViewModel(get())
    }
    single { SettingMainModel(get()) }
    single { SettingUserModel(get()) }
    single { SettingProjectModel(get()) }
    single { SettingFactoryModel(get()) }
    single { EngineeringParametersModel(get()) }
    single { ChangeProjectPasswordModel(get()) }
    single { ManufacturerDebuggingModel(get()) }
    single { ManufacturerParametersModel(get()) }
    single { ManufacturerUpgradeModel(get()) }
    single { OperationLogModel(get()) }
    single { FactoryChangesPasswordModel(get()) }
    single { DataTitleAdapter() }
    single { OperationAdapter() }
    single { HistoryAdapterBgs() }
    single { PasswordBean() }
    single {  DataBase() }
    single { ManufactorBean() }
    single { EngineeringBean() }
    single { SettingUserBean() }
    single { DebuggingSettingsBean() }
    single { get<Retrofit>().create(RequestService::class.java) }

    single { ApiRepository(get()) }
}
