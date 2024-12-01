package com.cn.setuplibrary.popup;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.setuplibrary.adapter.StyleAdapter;
import com.cn.setuplibrary.viewmodel.OperationLogViewModel;
import com.ijcsj.stUplibrary.R;
import com.ijcsj.ui_library.utils.ScreenUtils;
import com.lxj.xpopup.impl.PartShadowPopupView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 自定义局部阴影弹窗
 * Create by dance, at 2018/12/21
 */
public class CustomPartShadowPopupView extends PartShadowPopupView {
    List<String> list;
    StyleAdapter styleAdapter;
    public CustomPartShadowPopupView(@NonNull Context context, List<String> list, StyleAdapter styleAdapter) {
        super(context);
        this.list=list;
        this.styleAdapter=styleAdapter;
    }
    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_part_shadow_popup;
    }

    RecyclerView text;
    @Override
    protected void onCreate() {
        super.onCreate();
        text = findViewById(R.id.rv_historys);
        styleAdapter.refresh(list);
        text.setAdapter(styleAdapter);
        Log.e("tag","CustomPartShadowPopupView onCreate");

    }

    @Override
    protected int getMaxWidth() {
        return ScreenUtils.dp2PxInt(getContext(),100);
    }

  /*  @Override
    protected int getMaxHeight() {
        return ScreenUtils.dp2PxInt(getContext(),100);
    }
*/
    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag","CustomPartShadowPopupView onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        OperationLogViewModel.Companion. hideBottomNavigationBar(((Activity)getContext()).getWindow());
        Log.e("tag","CustomPartShadowPopupView onDismiss");
    }
}
