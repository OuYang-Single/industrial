package com.cn.setuplibrary.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ijcsj.stUplibrary.BR;

public class PasswordBean extends BaseObservable {
    private String password;
    private String passwordConfirm;
    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
    @Bindable
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
        notifyPropertyChanged(BR.passwordConfirm);
    }
}
