package com.cn.datalibrary.di

import com.cn.datalibrary.adapter.Data1ValueAdapter
import com.cn.datalibrary.adapter.Data2ValueAdapter
import com.cn.datalibrary.adapter.DataTitleAdapter
import com.cn.datalibrary.adapter.DataValueAdapter
import com.cn.datalibrary.adapter.HistoryAdapter
import com.cn.datalibrary.adapter.HistoryAdapterBg
import com.cn.datalibrary.api.ApiRepository
import com.cn.datalibrary.api.RequestService
import com.cn.datalibrary.base.DataFormBase
import com.cn.datalibrary.base.DataHistoryBase
import com.cn.datalibrary.base.VersionBase
import com.cn.datalibrary.fragment.DataFragment
import com.cn.datalibrary.model.BataMainVideoModel
import com.cn.datalibrary.model.BataVideoModel
import com.cn.datalibrary.model.FormViewVideoModel
import com.cn.datalibrary.model.HistoryVideoModel
import com.cn.datalibrary.model.InputVideoModel
import com.cn.datalibrary.model.OutputVideoModel
import com.cn.datalibrary.model.VersionVideoModel
import com.cn.datalibrary.viewmodel.BataMainViewModel
import com.cn.datalibrary.viewmodel.BataViewModel
import com.cn.datalibrary.viewmodel.FormViewModel
import com.cn.datalibrary.viewmodel.HistoryViewModel
import com.cn.datalibrary.viewmodel.InputViewModel
import com.cn.datalibrary.viewmodel.OutputViewModel
import com.cn.datalibrary.viewmodel.VersionViewModel
import com.google.gson.Gson
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val DataModule= module {
    viewModel {
        BataMainViewModel(get(),get())
    }
   viewModel {
     BataViewModel(get(),get())
    }
    viewModel {
        OutputViewModel(get(),get())
    }
    viewModel {
        InputViewModel(get(),get())
    }
    viewModel {
        VersionViewModel(get(),get())
    }
    viewModel {
        HistoryViewModel(get(),get(),get(),get())
    }
    viewModel {
        FormViewModel(get(),get())
    }

    single { BataMainVideoModel(get()) }
    single { BataVideoModel(get(),get()) }
    single { OutputVideoModel(get(),get()) }
    single { InputVideoModel(get(),get()) }
    single { VersionVideoModel(get()) }
    single { HistoryVideoModel(get()) }
    single { FormViewVideoModel(get()) }
    single { DataFormBase() }
    single { DataTitleAdapter() }
    single { HistoryAdapter() }
    single { HistoryAdapterBg() }
    single { DataValueAdapter() }
    single { Data1ValueAdapter() }
    single { Data2ValueAdapter() }
    single { DataHistoryBase() }
    single { VersionBase() }
    single { Gson() }
    single { get<Retrofit>().create(RequestService::class.java) }

    single { ApiRepository(get()) }
}

