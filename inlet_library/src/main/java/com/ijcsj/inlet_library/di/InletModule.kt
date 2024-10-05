package com.ijcsj.inlet_library.di


import com.ijcsj.inlet_library.api.ApiRepository
import com.ijcsj.inlet_library.api.RequestService
import com.ijcsj.inlet_library.base.MainBases
import com.ijcsj.inlet_library.model.InletModel
import com.ijcsj.inlet_library.model.MainModel
import com.ijcsj.inlet_library.viewmodel.InletViewModel
import com.ijcsj.inlet_library.viewmodel.MainViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit

val InletViewModel= module {
    viewModel {
        InletViewModel(get())
    }
    viewModel {
        MainViewModel(get(),get())
    }
    single { InletModel(get()) }
    single { MainModel(get()) }
    single { MainBases() }
    single { get<Retrofit>().create(RequestService::class.java) }

    single { ApiRepository(get()) }
}

