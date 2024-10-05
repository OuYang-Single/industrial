package com.ijcsj.common_library.viewadapter.viewgroup;

import androidx.databinding.ViewDataBinding;

/**
 * 作者：zhuqinghua
 * 时间：2023/10/20
 * 描述：
 */
public interface IBindingItemViewModel<V extends ViewDataBinding> {
    void injecDataBinding(V binding);
}
