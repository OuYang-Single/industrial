package com.ijcsj.common_library.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.common_library.ui.IBaseView
import java.lang.ref.Reference
import java.lang.ref.WeakReference

/**
 * 应用模块: viewModel
 *
 *
 * 类描述: 管理 v M
 *
 *
 *
 */
abstract class MvmBaseViewModel<V : IBaseView?, M : BaseModel?> : ViewModel(),
    IMvvmBaseViewModel<V> {
    private var mUiRef: Reference<V?>? = null
    abstract val model: M
    var gson: Gson = Gson()
    val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message
    override fun attachUi(view: V) {
        mUiRef = WeakReference(view)
    }

    override fun getPageView(): V? {
        if (null == mUiRef) {
            return null
        }
        return if (null != mUiRef!!.get()) {
            mUiRef!!.get()!!
        } else null
    }

    override fun isUiAttach(): Boolean {
        return null != mUiRef && null != mUiRef!!.get()
    }

    override fun detachUi() {
        if (null != mUiRef) {
            mUiRef!!.clear()
            mUiRef = null
        }
    }

    protected fun loadData() {}
    protected abstract fun initModel()



}