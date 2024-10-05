package com.cn.main_library.base;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.cn.main_library.BR;

import java.util.List;

public class MainBase  extends BaseObservable {

    private boolean isWarn;
    private boolean isWarn1;
    private boolean isWarn2;
    private boolean isWarn3;
    private String signalStrength="信号强度: -- %";
    private String settingTemperature="--";
    private String device="工控设备 --";
    private int temperature=0; //回煤温度
    private String temperatureString="--"; //回煤温度
    private int pressure=0; //回煤压力
    private String pressureString="--"; //回煤压力
    private int flow=0; //回煤流量
    private String flowString="--"; //回煤流量
    private String time;
    private String times;
    private String  workStatusProcess;
    private boolean isWaterPump=false;
    private boolean isHeat=false;
    private boolean isMoisturizing=false;
    private boolean isBurial=false;

    private boolean isSwitchOn;
    private int  istype;
    private ObservableList<ProjectBase> projectBaseList;
    @Bindable
    public String getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(String signalStrength) {
        this.signalStrength = signalStrength;
        notifyPropertyChanged( BR.signalStrength);
    }
    @Bindable
    public boolean isWarn() {
        return isWarn;
    }

    public void setWarn(boolean warn) {
        isWarn = warn;
        notifyPropertyChanged( BR.warn);
    }
    @Bindable
    public String getSettingTemperature() {
        return settingTemperature;
    }

    public void setSettingTemperature(String settingTemperature) {
        this.settingTemperature = settingTemperature;
        notifyPropertyChanged( BR.settingTemperature);
    }
    @Bindable
    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
        notifyPropertyChanged( BR.device);
    }

    public ObservableList<ProjectBase> getProjectBaseList() {
        return projectBaseList;
    }

    public void setProjectBaseList(ObservableList<ProjectBase> projectBaseList) {
        this.projectBaseList = projectBaseList;
    }
    @Bindable
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyPropertyChanged( BR.temperature);
    }
    @Bindable
    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
        notifyPropertyChanged( BR.flow);
    }
    @Bindable
    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
        notifyPropertyChanged( BR.pressure);
    }

    @Bindable
    public boolean isSwitchOn() {
        return isSwitchOn;
    }

    public void setSwitchOn(boolean switchOn) {
        isSwitchOn = switchOn;
        notifyPropertyChanged( BR.switchOn);
    }
    @Bindable
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        notifyPropertyChanged( BR.time);
    }
    @Bindable
    public String getFlowString() {
        return flowString;
    }

    public void setFlowString(String flowString) {
        this.flowString = flowString;
        notifyPropertyChanged( BR.flowString);
    }
    @Bindable
    public String getPressureString() {
        return pressureString;
    }

    public void setPressureString(String pressureString) {
        this.pressureString = pressureString;
        notifyPropertyChanged( BR.pressureString);
    }
    @Bindable
    public String getTemperatureString() {
        return temperatureString;
    }

    public void setTemperatureString(String temperatureString) {
        this.temperatureString = temperatureString;
        notifyPropertyChanged( BR.temperatureString);
    }
    @Bindable
    public boolean isWaterPump() {
        return isWaterPump;
    }

    public void setWaterPump(boolean waterPump) {
        isWaterPump = waterPump;
        notifyPropertyChanged( BR.waterPump);
    }
    @Bindable
    public boolean isHeat() {
        return isHeat;
    }

    public void setHeat(boolean heat) {
        isHeat = heat;
        notifyPropertyChanged( BR.heat);
    }
    @Bindable
    public boolean isMoisturizing() {
        return isMoisturizing;
    }

    public void setMoisturizing(boolean moisturizing) {
        isMoisturizing = moisturizing;
        notifyPropertyChanged( BR.moisturizing);
    }
    @Bindable
    public boolean isBurial() {
        return isBurial;
    }

    public void setBurial(boolean burial) {
        isBurial = burial;
        notifyPropertyChanged( BR.burial);
    }
    @Bindable
    public String getWorkStatusProcess() {
        return workStatusProcess;
    }

    public void setWorkStatusProcess(String workStatusProcess) {
        this.workStatusProcess = workStatusProcess;
        notifyPropertyChanged( BR.workStatusProcess);
    }
    @Bindable
    public int getIstype() {
        return istype;
    }

    public void setIstype(int istype) {
        this.istype = istype;
        notifyPropertyChanged( BR.istype);
    }
    @Bindable
    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
        notifyPropertyChanged( BR.times);
    }
    @Bindable
    public boolean isWarn3() {
        return isWarn3;
    }

    public void setWarn3(boolean warn3) {
        isWarn3 = warn3;
        notifyPropertyChanged( BR.warn3);
    }
    @Bindable
    public boolean isWarn2() {
        return isWarn2;
    }

    public void setWarn2(boolean warn2) {
        isWarn2 = warn2;
        notifyPropertyChanged( BR.warn2);
    }
    @Bindable
    public boolean isWarn1() {
        return isWarn1;
    }

    public void setWarn1(boolean warn1) {
        isWarn1 = warn1;
        notifyPropertyChanged( BR.warn1);
    }
}
