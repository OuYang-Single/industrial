package com.ijcsj.common_library.viewadapter.radiogroup;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.annotation.IdRes;
import androidx.databinding.BindingAdapter;

import com.ijcsj.common_library.command.BindingCommand;


/**
 * 作者：zhuqinghua
 * 时间：2023/10/20
 * 描述：
 */
public class ViewAdapter {

    /**
     * 设置RadioButton状态
     *
     * @param mRadioButton RadioButton
     */
    @BindingAdapter("RadioButtonState")
    public static void setRadioButtonState(RadioButton mRadioButton, boolean isChecked) {
        mRadioButton.setChecked(isChecked);
    }

    @BindingAdapter(value = {"onCheckedChangedCommand"}, requireAll = false)
    public static void onCheckedChangedCommand(final RadioGroup radioGroup, final BindingCommand<String> bindingCommand) {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                bindingCommand.execute(radioButton.getText().toString());
            }
        });
    }
}
