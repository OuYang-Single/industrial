package com.ijcsj.common_library.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ijcsj.ui_library.widget.CommonTitleBar;


/**
 * 应用模块: activity
 * <p>
 * 类描述: 界面UI显示切换
 * <p>
 */
public interface IBaseView {

    void showContent();


    void showLoading(boolean isPage);


    void showEmpty();
    void showRetry();


    void showFailure(String message);

    void closure(Intent intent);

    void LeftClick(View v, int action);

    Context getContexts();

    void rightClick(View v, int action);
    CommonTitleBar topBarAc();

}
