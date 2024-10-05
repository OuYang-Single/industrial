package com.ijcsj.ui_library.anko

import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * @description:
 * @author:  79120
 * @date :   2021/10/10 14:24
 */
inline fun ViewGroup.BottomNavigationView(
    style: Int? = null,
    autoAdd: Boolean = true,
    init: BottomNavigationView.() -> Unit
): BottomNavigationView {
    val navigation =
        if (style != null) BottomNavigationView(ContextThemeWrapper(context, style))
        else BottomNavigationView(context)
    return navigation.apply(init).also { if (autoAdd) addView(it) }
}

//inline fun ViewGroup.SmartRefreshLayout(
//    style: Int? = null,
//    autoAdd: Boolean = true,
//    init: SmartRefreshLayout.() -> Unit
//): SmartRefreshLayout {
//    val navigation =
//        if (style != null) SmartRefreshLayout(ContextThemeWrapper(context, style))
//        else SmartRefreshLayout(context)
//    return navigation.apply(init).also { if (autoAdd) addView(it) }
//}
//
//inline fun ViewGroup.ClassicsHeader(
//    style: Int? = null,
//    autoAdd: Boolean = true,
//    init: ClassicsHeader.() -> Unit
//): ClassicsHeader {
//    val navigation =
//        if (style != null) ClassicsHeader(ContextThemeWrapper(context, style))
//        else ClassicsHeader(context)
//    return navigation.apply(init).also { if (autoAdd) addView(it) }
//}

fun countDownCoroutines(
    total: Int,
    scope: CoroutineScope,
    onTick: (Int) -> Unit,
    onStart: (() -> Unit)? = null,
    onFinish: (() -> Unit)? = null,
): Job {
    return flow {
        for (i in total downTo 0) {
            emit(i)
            delay(1000)
        }
    }.flowOn(Dispatchers.Main)
        .onStart { onStart?.invoke() }
        .onCompletion { onFinish?.invoke() }
        .onEach { onTick.invoke(it) }
        .launchIn(scope)
}