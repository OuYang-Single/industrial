package com.ijcsj.common_library.viewadapter.checkbox;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.databinding.BindingAdapter;

import com.ijcsj.common_library.command.BindingCommand;


/**
 * 作者：zhuqinghua
 * 时间：2023/10/20
 * 描述：
 */
public class ViewAdapter {
    /**
     * @param bindingCommand //绑定监听
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"onCheckedChangedCommand"}, requireAll = false)
    public static void setCheckedChanged(final CheckBox checkBox, final BindingCommand<Boolean> bindingCommand) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bindingCommand.execute(b);
            }
        });
    }
}
