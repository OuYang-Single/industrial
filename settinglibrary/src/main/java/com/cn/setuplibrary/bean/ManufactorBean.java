package com.cn.setuplibrary.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ijcsj.stUplibrary.BR;

public class ManufactorBean extends BaseObservable {
    private String typeMachine="A2";
    private String temperatureUpperLimit="160℃";
    private String temperatureOffline="50℃";
    private String activationDate;
    private String activationCode;
    @Bindable
    public String getTypeMachine() {
        return typeMachine;
    }

    public void setTypeMachine(String typeMachine) {
        this.typeMachine = typeMachine;
        notifyPropertyChanged(BR.typeMachine);
    }
    @Bindable
    public String getTemperatureUpperLimit() {
        return temperatureUpperLimit;
    }

    public void setTemperatureUpperLimit(String temperatureUpperLimit) {
        this.temperatureUpperLimit = temperatureUpperLimit;
        notifyPropertyChanged(BR.temperatureUpperLimit);
    }
    @Bindable
    public String getTemperatureOffline() {
        return temperatureOffline;
    }

    public void setTemperatureOffline(String temperatureOffline) {
        this.temperatureOffline = temperatureOffline;
        notifyPropertyChanged(BR.temperatureOffline);
    }
    @Bindable
    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
        notifyPropertyChanged(BR.activationDate);
    }
    @Bindable
    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
        notifyPropertyChanged(BR.activationCode);
    }
}
