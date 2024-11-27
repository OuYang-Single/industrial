package com.cn.setuplibrary.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ijcsj.stUplibrary.BR;

public class EngineeringBean  extends BaseObservable {
    private String pinP="请输入";
    private String pinI="请输入";
    private String pinD="请输入";

    private String temperatureMode="请选择";
    private String fillingTime="请选择";
    private String pumpJammingTime="请选择";
    private String coolingTemperature="请选择";
    private String exhaustPressure="请选择";
    private String emptyingTime="请选择";
    private String highDeviation="请选择";
    private String lowDeviation="请选择";
    private String temperatureDifference="请选择";
    private String temperatureDeviationTime="请选择";
    private String heatingTimeOut="请选择";
    private String coolingTimeOut="请选择";
    private String coalCompensation="请选择";
    private String coalReturnCompensation="请选择";
    private String returnPressureDifference="请选择";
    private String highPressureDeviation="请选择";
    private String lowPressureDeviation="请选择";
    private String minimumInletPressure="请选择";
    private String maximumReturnWaterPressure="请选择";
    private String minimumPumpPressure="请选择";
    private String minimumFlowValue="请选择";
    private String minimumTrafficDelayTime="请选择";
    private String  ipAddress="请选择";

    @Bindable
    public String getPinP() {
        return pinP;
    }

    public void setPinP(String pinP) {
        this.pinP = pinP;
        notifyPropertyChanged(BR.pinP);
    }
    @Bindable
    public String getPinI() {
        return pinI;
    }

    public void setPinI(String pinI) {
        this.pinI = pinI;
        notifyPropertyChanged(BR.pinI);
    }
    @Bindable
    public String getPinD() {
        return pinD;
    }

    public void setPinD(String pinD) {
        this.pinD = pinD;
        notifyPropertyChanged(BR.pinD);
    }
    @Bindable
    public String getTemperatureMode() {
        return temperatureMode;
    }

    public void setTemperatureMode(String temperatureMode) {
        this.temperatureMode = temperatureMode;
        notifyPropertyChanged(BR.temperatureMode);
    }
    @Bindable
    public String getFillingTime() {
        return fillingTime;
    }

    public void setFillingTime(String fillingTime) {
        this.fillingTime = fillingTime;
        notifyPropertyChanged(BR.fillingTime);
    }
    @Bindable
    public String getPumpJammingTime() {
        return pumpJammingTime;
    }

    public void setPumpJammingTime(String pumpJammingTime) {
        this.pumpJammingTime = pumpJammingTime;
        notifyPropertyChanged(BR.pumpJammingTime);
    }
    @Bindable
    public String getCoolingTemperature() {
        return coolingTemperature;
    }

    public void setCoolingTemperature(String coolingTemperature) {
        this.coolingTemperature = coolingTemperature;
        notifyPropertyChanged(BR.coolingTemperature);
    }
    @Bindable
    public String getExhaustPressure() {
        return exhaustPressure;

    }

    public void setExhaustPressure(String exhaustPressure) {
        this.exhaustPressure = exhaustPressure;
        notifyPropertyChanged(BR.exhaustPressure);
    }
    @Bindable
    public String getEmptyingTime() {
        return emptyingTime;
    }

    public void setEmptyingTime(String emptyingTime) {
        this.emptyingTime = emptyingTime;
        notifyPropertyChanged(BR.emptyingTime);
    }
    @Bindable
    public String getHighDeviation() {
        return highDeviation;
    }

    public void setHighDeviation(String highDeviation) {
        this.highDeviation = highDeviation;
        notifyPropertyChanged(BR.highDeviation);
    }
    @Bindable
    public String getLowDeviation() {
        return lowDeviation;
    }

    public void setLowDeviation(String lowDeviation) {
        this.lowDeviation = lowDeviation;
        notifyPropertyChanged(BR.lowDeviation);
    }
    @Bindable
    public String getTemperatureDifference() {
        return temperatureDifference;
    }

    public void setTemperatureDifference(String temperatureDifference) {
        this.temperatureDifference = temperatureDifference;
        notifyPropertyChanged(BR.temperatureDifference);
    }
    @Bindable
    public String getTemperatureDeviationTime() {
        return temperatureDeviationTime;
    }

    public void setTemperatureDeviationTime(String temperatureDeviationTime) {
        this.temperatureDeviationTime = temperatureDeviationTime;
        notifyPropertyChanged(BR.temperatureDeviationTime);
    }
    @Bindable
    public String getHeatingTimeOut() {
        return heatingTimeOut;
    }

    public void setHeatingTimeOut(String heatingTimeOut) {
        this.heatingTimeOut = heatingTimeOut;
        notifyPropertyChanged(BR.heatingTimeOut);
    }
    @Bindable
    public String getCoolingTimeOut() {
        return coolingTimeOut;
    }

    public void setCoolingTimeOut(String coolingTimeOut) {
        this.coolingTimeOut = coolingTimeOut;
        notifyPropertyChanged(BR.coolingTimeOut);
    }
    @Bindable
    public String getCoalCompensation() {
        return coalCompensation;
    }

    public void setCoalCompensation(String coalCompensation) {
        this.coalCompensation = coalCompensation;
        notifyPropertyChanged(BR.coalCompensation);
    }
    @Bindable
    public String getCoalReturnCompensation() {
        return coalReturnCompensation;
    }

    public void setCoalReturnCompensation(String coalReturnCompensation) {
        this.coalReturnCompensation = coalReturnCompensation;
        notifyPropertyChanged(BR.coalReturnCompensation);
    }
    @Bindable
    public String getReturnPressureDifference() {
        return returnPressureDifference;
    }

    public void setReturnPressureDifference(String returnPressureDifference) {
        this.returnPressureDifference = returnPressureDifference;
        notifyPropertyChanged(BR.returnPressureDifference);
    }
    @Bindable
    public String getHighPressureDeviation() {
        return highPressureDeviation;
    }

    public void setHighPressureDeviation(String highPressureDeviation) {
        this.highPressureDeviation = highPressureDeviation;
        notifyPropertyChanged(BR.highPressureDeviation);
    }
    @Bindable
    public String getLowPressureDeviation() {
        return lowPressureDeviation;
    }

    public void setLowPressureDeviation(String lowPressureDeviation) {
        this.lowPressureDeviation = lowPressureDeviation;
        notifyPropertyChanged(BR.lowPressureDeviation);
    }
    @Bindable
    public String getMinimumInletPressure() {
        return minimumInletPressure;
    }

    public void setMinimumInletPressure(String minimumInletPressure) {
        this.minimumInletPressure = minimumInletPressure;
        notifyPropertyChanged(BR.minimumInletPressure);
    }
    @Bindable
    public String getMaximumReturnWaterPressure() {
        return maximumReturnWaterPressure;
    }

    public void setMaximumReturnWaterPressure(String maximumReturnWaterPressure) {
        this.maximumReturnWaterPressure = maximumReturnWaterPressure;
        notifyPropertyChanged(BR.maximumReturnWaterPressure);
    }
    @Bindable
    public String getMinimumPumpPressure() {
        return minimumPumpPressure;
    }

    public void setMinimumPumpPressure(String minimumPumpPressure) {
        this.minimumPumpPressure = minimumPumpPressure;
        notifyPropertyChanged(BR.minimumPumpPressure);
    }
    @Bindable
    public String getMinimumFlowValue() {
        return minimumFlowValue;
    }

    public void setMinimumFlowValue(String minimumFlowValue) {
        this.minimumFlowValue = minimumFlowValue;
        notifyPropertyChanged(BR.minimumFlowValue);
    }
    @Bindable
    public String getMinimumTrafficDelayTime() {
        return minimumTrafficDelayTime;
    }

    public void setMinimumTrafficDelayTime(String minimumTrafficDelayTime) {
        this.minimumTrafficDelayTime = minimumTrafficDelayTime;
        notifyPropertyChanged(BR.minimumTrafficDelayTime);
    }
    @Bindable
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        notifyPropertyChanged(BR.ipAddress);
    }
}
