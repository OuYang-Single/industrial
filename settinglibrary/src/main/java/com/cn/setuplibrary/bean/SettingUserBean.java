package com.cn.setuplibrary.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ijcsj.stUplibrary.BR;


public class SettingUserBean  extends BaseObservable {
    private boolean  isPassword=false;
    private String  timeOut;
    private String  startMode;
    private String  burial;
    private String  drain;
    @Bindable
    public String getTimeOut() {
        return timeOut;

    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
        notifyPropertyChanged(BR.timeOut);
    }
    @Bindable
    public String getStartMode() {
        return startMode;
    }

    public void setStartMode(String startMode) {
        this.startMode = startMode;
        notifyPropertyChanged(BR.startMode);
    }
    @Bindable
    public String getBurial() {
        return burial;
    }

    public void setBurial(String burial) {
        this.burial = burial;
        notifyPropertyChanged(BR.burial);
    }
    @Bindable
    public String getDrain() {
        return drain;
    }

    public void setDrain(String drain) {
        this.drain = drain;
        notifyPropertyChanged(BR.drain);
    }
    @Bindable
    public boolean isPassword() {
        return isPassword;
    }

    public void setPassword(boolean password) {
        isPassword = password;
        notifyPropertyChanged(BR.password);
    }
}
