package com.ijcsj.mylibrary.di

import com.ijcsj.mylibrary.api.ApiRepository
import com.ijcsj.mylibrary.api.RequestService
import com.ijcsj.mylibrary.fragment.MyFragment
import com.ijcsj.mylibrary.model.AboutModel
import com.ijcsj.mylibrary.model.DeviceModel
import com.ijcsj.mylibrary.model.FeedbackModel
import com.ijcsj.mylibrary.model.MyModel
import com.ijcsj.mylibrary.model.RealNameModel
import com.ijcsj.mylibrary.model.SettingModel
import com.ijcsj.mylibrary.viewmodel.AboutViewModel
import com.ijcsj.mylibrary.viewmodel.DeviceViewModel
import com.ijcsj.mylibrary.viewmodel.FeedbackViewModel
import com.ijcsj.mylibrary.viewmodel.MyViewModel
import com.ijcsj.mylibrary.viewmodel.RealNameViewModel
import com.ijcsj.mylibrary.viewmodel.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

var MyModule =module {
    viewModel {
        MyViewModel(get())
    }
    viewModel {
        DeviceViewModel(get())
    }

    viewModel {
        FeedbackViewModel(get())
    }

    viewModel {
        AboutViewModel(get())
    }
    viewModel {
        SettingViewModel(get())
    }
    viewModel {
        RealNameViewModel(get())
    }
    single { FeedbackModel(get()) }
    single { SettingModel(get()) }
    single { MyModel(get()) }
    single { DeviceModel(get()) }
    single { AboutModel(get()) }
    single { RealNameModel(get()) }

    single { get<Retrofit>().create(RequestService::class.java) }

    single { ApiRepository(get()) }
}
