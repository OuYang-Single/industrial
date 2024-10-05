package com.ijcsj.login_library.di

import com.ijcsj.login_library.api.ApiRepository
import com.ijcsj.login_library.api.RequestService
import com.ijcsj.login_library.bean.LoginBean
import com.ijcsj.login_library.bean.LoginsBean
import com.ijcsj.login_library.model.LogInModel
import com.ijcsj.login_library.viewmodel.LogInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val LoginModule= module {
    viewModel {
        LogInViewModel(get(),get(),get())
    }

    single { LogInModel(get()) }
    single { LoginBean() }
    single { LoginsBean() }

    single { get<Retrofit>().create(RequestService::class.java) }

    single { ApiRepository(get()) }
}
