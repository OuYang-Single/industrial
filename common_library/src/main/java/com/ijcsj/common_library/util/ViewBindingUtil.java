package com.ijcsj.common_library.util;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.jakewharton.rxbinding4.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;

public class ViewBindingUtil {

    @BindingAdapter(value = {"onClicks"}, requireAll = false)
    public static void onClickCommand(View view, final View.OnClickListener l) {
        RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)//1秒钟内只允许点击1次
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (l != null) {
                            l.onClick(view);
                        }
                    }
                });
    }
}
