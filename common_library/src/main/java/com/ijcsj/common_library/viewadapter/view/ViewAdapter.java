package com.ijcsj.common_library.viewadapter.view;

import android.annotation.SuppressLint;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.databinding.BindingAdapter;

import com.ijcsj.common_library.command.BindingCommand;
import com.jakewharton.rxbinding4.view.RxView;
import com.orhanobut.logger.Logger;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Unit;


/**
 * 作者：zhuqinghua
 * 时间：2023/10/20
 * 描述：
 */

public class ViewAdapter {
    //防重复点击间隔(秒)
    public static final int CLICK_INTERVAL = 1;

    static Map<View,BindingCommand> viewBindingCommandMap=new HashMap<>();

    /**
     * requireAll 是意思是是否需要绑定全部参数, false为否
     * View的onClick事件绑定
     * onClickCommand 绑定的命令,
     * isThrottleFirst 是否开启防止过快点击
     */

    @BindingAdapter(value = {"onClickCommand"}, requireAll = false)
    public static void onClickCommand(View view,  final BindingCommand clickCommand) {
        Log.w("ouyang", "onClickCommand  onClickRight "+view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickCommand != null) {
                    clickCommand.execute(view);
                }
            }
        });

    }

    /**
     * 连续点击多次事件
     * src 拷贝的源数组
     * srcPos 从源数组的那个位置开始拷贝.
     * dst 目标数组
     * dstPos 从目标数组的那个位子开始写数据
     * length 拷贝的元素的个数
     */
    @BindingAdapter(value = {"mClickCommand"}, requireAll = false)
    public static void mClickCommand(View view, final BindingCommand clickCommand) {
            view.setOnClickListener(new View.OnClickListener() {
                final static int COUNTS = 3;//点击次数
                final static long DURATION = 1 * 1000;//规定有效时间
                long[] mHits = new long[COUNTS];

                @Override
                public void onClick(View v) {
                    //每次点击时，数组向前移动一位
                    System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                    //为数组最后一位赋值
                    mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                    if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
                        mHits = new long[COUNTS];//重新初始化数组
                        if (clickCommand != null) {
                            clickCommand.execute(view);
                        }
                        String tips = "您已在[" + DURATION + "]ms内连续点击【" + mHits.length + "】次了！！！";
                        Logger.d("mClickCommand: "+tips, "$this ====>>  onViewCreated pos ===>> 开始");
                    }
                }
            });
    }


    /**
     * view的onLongClick事件绑定
     */
    @BindingAdapter(value = {"onLongClickCommand"}, requireAll = false)
    public static void onLongClickCommand(View view, final BindingCommand clickCommand) {
        RxView.longClicks(view)
                .subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Throwable {
                        if (clickCommand != null) {
                            clickCommand.execute(view);
                        }
                    }
                });
    }

    /**
     * 回调控件本身
     *
     * @param currentView
     * @param bindingCommand
     */
    @BindingAdapter(value = {"currentView"}, requireAll = false)
    public static void replyCurrentView(View currentView, BindingCommand bindingCommand) {
        if (bindingCommand != null) {
            bindingCommand.execute(currentView);
        }
    }

    /**
     * view是否需要获取焦点
     */
    @BindingAdapter({"requestFocus"})
    public static void requestFocusCommand(View view, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        } else {
            view.clearFocus();
        }
    }

    /**
     * view的焦点发生变化的事件绑定
     */
    @BindingAdapter({"onFocusChangeCommand"})
    public static void onFocusChangeCommand(View view, final BindingCommand<Boolean> onFocusChangeCommand) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (onFocusChangeCommand != null) {
                    onFocusChangeCommand.execute(hasFocus);
                }
            }
        });
    }

    /**
     * view的显示隐藏
     */
    @BindingAdapter(value = {"isVisible"}, requireAll = false)
    public static void isVisible(View view, final Boolean visibility) {
        if (visibility) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }


    @BindingAdapter(value = "textViewState")
    public static void setTextViewState(TextView textView, boolean isSelected) {
        if (textView != null) {
            textView.setSelected(isSelected);
        }
    }

    @BindingAdapter(value = "textViewEnabled")
    public static void setTextViewEnabled(TextView textView, boolean isEnabled) {
        if (textView != null) {
            textView.setEnabled(isEnabled);
        }
    }


//    @BindingAdapter({"onTouchCommand"})
//    public static void onTouchCommand(View view, final ResponseCommand<MotionEvent, Boolean> onTouchCommand) {
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (onTouchCommand != null) {
//                    return onTouchCommand.execute(event);
//                }
//                return false;
//            }
//        });
//    }
}
