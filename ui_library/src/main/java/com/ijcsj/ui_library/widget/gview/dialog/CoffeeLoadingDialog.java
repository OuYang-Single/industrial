package com.ijcsj.ui_library.widget.gview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ijcsj.ui_library.R;
import com.ijcsj.ui_library.widget.gview.loading.CoffeeDrawable;


/**
 * Created by guc on 2021/2/6.
 * Description：咖啡加载动画
 */
public class CoffeeLoadingDialog extends Dialog {
    private TextView tvTip;
    private ImageView tvCoffee;
    private CoffeeDrawable drawable;

    public CoffeeLoadingDialog(@NonNull Context context) {
        this(context, R.style.LoadingDialog);
    }

    public CoffeeLoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    private void initView() {
        setContentView(R.layout.view_coffee_loading_dialog);
        tvTip = findViewById(R.id.tvTips);
        tvCoffee = findViewById(R.id.iv_coffee);
        drawable = CoffeeDrawable.create(tvCoffee, 50);
        drawable.setProgress(1);
    }

    public void setTips(String tip) {
        tvTip.setText(tip);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        drawable.start();
    }

}
