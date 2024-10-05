package com.ijcsj.ui_library.editor.interfaces;


import com.ijcsj.ui_library.editor.bean.FontColorBean;

/**
 * 颜色选择回调接口
 *
 * Created by yyp on 2019.04.11
 */
public interface OnColorSelectListener {

    void onColorSelect(FontColorBean bean, int pos);
}
