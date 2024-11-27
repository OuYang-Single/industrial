package com.cn.setuplibrary.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ijcsj.stUplibrary.BR;

public class DebuggingSettingsBean extends BaseObservable {
  private   String inletValve="关闭";//进水阀
  private   String waterOutletValve="关闭";//出水阀
  private   String waterPumpValve="关闭";//水泵
  private   String waterReplenishmentPumpValve="关闭";//补水泵
  private   String turnOnHeating="关闭";//开加热
  private   String blowAirOn="关闭";//开吹气
  private   String openTheColdEndValve="关闭";//开冷端阀门
  private   String openTheColdEndProportionalValve="0";//开冷端比例阀
  private   String openTheProportionalValveAtTheHotEnd="0";//开热端比例阀

    @Bindable
    public String getInletValve() {
        return inletValve;
    }

    public void setInletValve(String inletValve) {
        this.inletValve = inletValve;
        notifyPropertyChanged(BR.inletValve);
    }
    @Bindable
    public String getWaterOutletValve() {
        return waterOutletValve;
    }

    public void setWaterOutletValve(String waterOutletValve) {
        this.waterOutletValve = waterOutletValve;
        notifyPropertyChanged(BR.waterOutletValve);
    }
    @Bindable
    public String getWaterPumpValve() {
        return waterPumpValve;
    }

    public void setWaterPumpValve(String waterPumpValve) {
        this.waterPumpValve = waterPumpValve;
        notifyPropertyChanged(BR.waterPumpValve);
    }
    @Bindable
    public String getWaterReplenishmentPumpValve() {
        return waterReplenishmentPumpValve;
    }

    public void setWaterReplenishmentPumpValve(String waterReplenishmentPumpValve) {
        this.waterReplenishmentPumpValve = waterReplenishmentPumpValve;
        notifyPropertyChanged(BR.waterReplenishmentPumpValve);
    }
    @Bindable
    public String getTurnOnHeating() {
        return turnOnHeating;
    }

    public void setTurnOnHeating(String turnOnHeating) {
        this.turnOnHeating = turnOnHeating;
        notifyPropertyChanged(BR.turnOnHeating);
    }
    @Bindable
    public String getBlowAirOn() {
        return blowAirOn;
    }

    public void setBlowAirOn(String blowAirOn) {
        this.blowAirOn = blowAirOn;
        notifyPropertyChanged(BR.blowAirOn);
    }
    @Bindable
    public String getOpenTheColdEndValve() {
        return openTheColdEndValve;
    }

    public void setOpenTheColdEndValve(String openTheColdEndValve) {
        this.openTheColdEndValve = openTheColdEndValve;
        notifyPropertyChanged(BR.openTheColdEndValve);
    }
    @Bindable
    public String getOpenTheColdEndProportionalValve() {
        return openTheColdEndProportionalValve;
    }

    public void setOpenTheColdEndProportionalValve(String openTheColdEndProportionalValve) {
        this.openTheColdEndProportionalValve = openTheColdEndProportionalValve;
        notifyPropertyChanged(BR.openTheColdEndProportionalValve);
    }
    @Bindable
    public String getOpenTheProportionalValveAtTheHotEnd() {
        return openTheProportionalValveAtTheHotEnd;
    }

    public void setOpenTheProportionalValveAtTheHotEnd(String openTheProportionalValveAtTheHotEnd) {
        this.openTheProportionalValveAtTheHotEnd = openTheProportionalValveAtTheHotEnd;
        notifyPropertyChanged(BR.openTheProportionalValveAtTheHotEnd);
    }
}
