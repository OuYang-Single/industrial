package com.ijcsj.ui_library.widget.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;

import com.ijcsj.ui_library.R;
import com.ijcsj.ui_library.widget.tab.common.IHiTab;


public class HiTabBottom extends RelativeLayout implements IHiTab<HiTabBottomInfo<?>> {
    private HiTabBottomInfo<?> tabInfo;
    private ImageView tabImageView;
    private ImageView iv_image_d_c;
    private ImageView tabImageViews;
    private TextView tabIconView;
    private TextView tabNameView;

    public HiTabBottom(Context context) {
        this(context, null);
    }

    public HiTabBottom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HiTabBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.hi_tab_bottom, this);
        tabImageView = findViewById(R.id.iv_image);
        iv_image_d_c = findViewById(R.id.iv_image_d_c);
        tabIconView = findViewById(R.id.tv_icon);
        tabNameView = findViewById(R.id.tv_name);
        tabImageViews = findViewById(R.id.iv_image_d);
    }

    @Override
    public void setHiTabInfo(@NonNull HiTabBottomInfo hiTabBottomInfo) {
        this.tabInfo = hiTabBottomInfo;
        inflateInfo(false, true);
    }

    public HiTabBottomInfo getHiTabInfo() {
        return tabInfo;
    }

    public ImageView getTabImageView() {
        return tabImageView;
    }

    public TextView getTabIconView() {
        return tabIconView;
    }

    public TextView getTabNameView() {
        return tabNameView;
    }

    /**
     * 改变某个tab的高度
     *
     * @param height
     */
    @Override
    public void resetHeight(@Px int height) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = height;
        setLayoutParams(layoutParams);
        getTabNameView().setVisibility(View.GONE);
    }

    private void inflateInfo(boolean selected, boolean init) {
        if (tabInfo.tabType == HiTabBottomInfo.TabType.ICON) {
            if (init) {
                tabImageView.setVisibility(GONE);
                tabIconView.setVisibility(VISIBLE);
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), tabInfo.iconFont);
                tabIconView.setTypeface(typeface);
                if (!TextUtils.isEmpty(tabInfo.name)) {
                    tabNameView.setText(tabInfo.name);
                }
            }

            if (selected) {
                tabIconView.setText(TextUtils.isEmpty(tabInfo.selectedIconName) ? tabInfo.defaultIconName : tabInfo.selectedIconName);
                tabIconView.setTextColor(getTextColor(tabInfo.tintColor));
                tabNameView.setTextColor(getTextColor(tabInfo.tintColor));
            } else {
                tabIconView.setText(tabInfo.defaultIconName);
                tabIconView.setTextColor(getTextColor(tabInfo.defaultColor));
                tabNameView.setTextColor(getTextColor(tabInfo.defaultColor));
            }

        } else if (tabInfo.tabType == HiTabBottomInfo.TabType.BITMAP) {
            if (init) {
                tabImageView.setVisibility(VISIBLE);
                tabIconView.setVisibility(GONE);
                if (!TextUtils.isEmpty(tabInfo.name)) {
                    tabNameView.setText(tabInfo.name);
                }else {
                    if (tabImageViews!=null){
                        tabImageView.setVisibility(GONE);
                        tabImageViews.setVisibility(VISIBLE);
                    }
                    tabNameView.setVisibility(GONE);
                }
            }
            if (selected) {
                if (tabInfo.selectedBitmap==null){
                    tabImageView.setImageResource(tabInfo.selectedInt);

                    if (tabImageViews!=null){
                        tabImageViews.setImageResource(tabInfo.selectedInt);
                    }
                    tabIconView.setTextColor(getTextColor(tabInfo.tintColor));
                    tabNameView.setTextColor(getTextColor(tabInfo.tintColor));
                }else {
                    tabImageView.setImageBitmap(tabInfo.selectedBitmap);
                    if (tabImageViews!=null){
                        tabImageViews.setImageBitmap(tabInfo.selectedBitmap);
                    }
                }
                if (iv_image_d_c!=null){
                    iv_image_d_c.setVisibility(VISIBLE);
                }

            } else {
                if (tabInfo.selectedBitmap==null){
                    tabImageView.setImageResource(tabInfo.defaultInt);
                    if (tabImageViews!=null){
                        tabImageViews.setImageResource(tabInfo.defaultInt);
                    }
                    tabIconView.setTextColor(getTextColor(tabInfo.defaultColor));
                    tabNameView.setTextColor(getTextColor(tabInfo.defaultColor));
                }else {
                    tabImageView.setImageBitmap(tabInfo.defaultBitmap);
                    if (tabImageViews!=null){
                        tabImageViews.setImageBitmap(tabInfo.defaultBitmap);
                    }
                }
                if (iv_image_d_c!=null){
                    iv_image_d_c.setVisibility(GONE);
                }

            }
        }
    }

    @Override
    public void onTabSelectedChange(int index, @Nullable HiTabBottomInfo<?> prevInfo, @NonNull HiTabBottomInfo<?> nextInfo) {
        if (prevInfo != tabInfo && nextInfo != tabInfo || prevInfo == nextInfo) {
            return;
        }
        if (prevInfo == tabInfo) {
            inflateInfo(false, false);
        } else {
            inflateInfo(true, false);
        }
    }

    @ColorInt
    private int getTextColor(Object color) {
        if (color instanceof String) {
            return Color.parseColor((String) color);
        } else {
            return (int) color;
        }
    }
}
