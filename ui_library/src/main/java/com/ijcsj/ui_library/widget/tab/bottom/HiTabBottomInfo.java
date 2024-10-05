package com.ijcsj.ui_library.widget.tab.bottom;

import android.graphics.Bitmap;

public class HiTabBottomInfo<Color>{
    public enum TabType {
        BITMAP, ICON
    }

    public String fragment;
    public String name;
    public Bitmap defaultBitmap;
    public int defaultInt;
    public Bitmap selectedBitmap;
    public int selectedInt;
    public String iconFont;
    /**
     * Tips：在Java代码中直接设置iconfont字符串无效，需要定义在string.xml
     */
    public String defaultIconName;
    public String selectedIconName;
    public Color defaultColor;
    public Color tintColor;
    public TabType tabType;

    public HiTabBottomInfo(String name, Bitmap defaultBitmap, Bitmap selectedBitmap) {
        this.name = name;
        this.defaultBitmap = defaultBitmap;
        this.selectedBitmap = selectedBitmap;
        this.tabType = TabType.BITMAP;
    }
    public HiTabBottomInfo(String name, int defaultBitmap, int selectedBitmap, Color defaultColor, Color tintColor) {
        this.name = name;
        this.defaultInt = defaultBitmap;
        this.selectedInt = selectedBitmap;
        this.defaultColor = defaultColor;
        this.tintColor = tintColor;
        this.tabType = TabType.BITMAP;
    }
    public HiTabBottomInfo(String name, int defaultBitmap, int selectedBitmap, Color defaultColor, Color tintColor,String fragment) {
        this.name = name;
        this.defaultInt = defaultBitmap;
        this.selectedInt = selectedBitmap;
        this.defaultColor = defaultColor;
        this.tintColor = tintColor;
        this.fragment =fragment;
        this.tabType = TabType.BITMAP;

    }

    public HiTabBottomInfo( int defaultBitmap, int selectedBitmap, String fragment) {
        this.defaultInt = defaultBitmap;
        this.selectedInt = selectedBitmap;
        this.fragment =fragment;
        this.tabType = TabType.ICON;
    }
    public HiTabBottomInfo(String name, String iconFont, String defaultIconName, String selectedIconName, Color defaultColor, Color tintColor) {
        this.name = name;
        this.iconFont = iconFont;
        this.defaultIconName = defaultIconName;
        this.selectedIconName = selectedIconName;
        this.defaultColor = defaultColor;
        this.tintColor = tintColor;
        this.tabType = TabType.ICON;
    }
}
