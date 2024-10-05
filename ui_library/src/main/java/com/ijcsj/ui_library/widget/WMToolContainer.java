package com.ijcsj.ui_library.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


import com.ijcsj.ui_library.toolitem.WMToolItem;
import com.ijcsj.ui_library.toolitem.WMToolTimes;
import com.ijcsj.ui_library.utils.WMUtil;

import java.util.ArrayList;
import java.util.List;

public class WMToolContainer extends WMHorizontalScrollView {

    private Context context;
    private List<WMToolItem> tools = new ArrayList<>();

    public WMToolContainer(Context context) {
        this(context, null);
    }

    public WMToolContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WMToolContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public WMToolContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }



    public void addToolItem(WMToolItem toolItem) {
        tools.add(toolItem);
        List<View> views = toolItem.getView(context);
        if (views != null) {
            for (View view : views) {
                view.setBackgroundColor(0);
                if ("1".equals(view.getTag())){
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(WMUtil.getPixelByDp(context, 1), LinearLayout.LayoutParams.MATCH_PARENT);
                    int margin = WMUtil.getPixelByDp(context, 1);
                   layoutParams.setMargins(WMUtil.getPixelByDp(context, 2),WMUtil.getPixelByDp(context, 15),WMUtil.getPixelByDp(context, 2),WMUtil.getPixelByDp(context, 15));
                    view.setBackgroundColor(Color.BLACK);
                    this.addItemView(view,layoutParams);
                }else {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    int margin = WMUtil.getPixelByDp(context, 1);
                    layoutParams.setMargins(margin, margin, margin, margin);
                    view.setLayoutParams(layoutParams);
                    int padding = WMUtil.getPixelByDp(context, 10);
                    view.setPadding(padding, padding, padding, padding);
                    this.addItemView(view);
                }

            }
        }
    }


    public List<WMToolItem> getTools() {
        return tools;
    }

}