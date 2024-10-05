package com.cn.main_library.di



import com.cn.main_library.adapter.PhoneMainAdapter
import com.cn.main_library.adapter.ProjectAdapter
import com.cn.main_library.api.ApiRepository
import com.cn.main_library.api.RequestService
import com.cn.main_library.base.MainBase
import com.cn.main_library.model.MainVideoModel
import com.cn.main_library.viewmodel.MainViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit

val MainModule= module {
    viewModel {
        MainViewModel(get(),get(),get(),get())
    }

    single { MainVideoModel(get()) }
    single { MainBase() }
    single { ProjectAdapter() }
    single { PhoneMainAdapter() }
    single { get<Retrofit>().create(RequestService::class.java) }

    single { ApiRepository(get()) }
}

