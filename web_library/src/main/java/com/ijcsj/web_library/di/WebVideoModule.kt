package com.ijcsj.web_library.di


import com.ijcsj.web_library.api.ApiRepository
import com.ijcsj.web_library.api.RequestService
import com.ijcsj.web_library.model.WebModel
import com.ijcsj.web_library.viewmodel.WebViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit

val WebVideoModule= module {
    viewModel {
        WebViewModel(get())
    }

    single { WebModel(get()) }

    single { get<Retrofit>().create(RequestService::class.java) }

    single { ApiRepository(get()) }
}
