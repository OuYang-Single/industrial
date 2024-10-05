package com.ijcsj.common_library.viewadapter.viewgroup;

import static com.ijcsj.common_library.viewadapter.view.ViewAdapter.CLICK_INTERVAL;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;

import com.ijcsj.common_library.command.BindingCommand;
import com.jakewharton.rxbinding4.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;

/**
 * 作者：zhuqinghua
 * 时间：2023/10/20
 * 描述：
 */
public final class ViewAdapter {
    /**
     * requireAll 是意思是是否需要绑定全部参数, false为否
     * View的onClick事件绑定
     * onClickCommand 绑定的命令,
     * isThrottleFirst 是否开启防止过快点击
     */
    @BindingAdapter(value = {"onViewGroupClickCommand"}, requireAll = false)
    public static void onViewGroupClickCommand(View view, final BindingCommand clickCommand) {
        RxView.clicks(view)
                .throttleFirst(CLICK_INTERVAL, TimeUnit.SECONDS)//1秒钟内只允许点击1次
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (clickCommand != null) {
                            clickCommand.execute(view);
                        }
                    }
                });
    }

}

