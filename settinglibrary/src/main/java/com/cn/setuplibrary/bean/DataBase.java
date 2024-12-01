package com.cn.setuplibrary.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


import com.ijcsj.stUplibrary.BR;

import java.util.Date;

public class DataBase extends BaseObservable {
    private String StartTime="请选择开始日期";
    private Date StartTimeData;

    private String EndTime="请选择结束日期";
    private Date EndTimeData;
    private String currentPage="第 1 页";
    private String commonPage="共 8 页";
    private boolean isLeftProhibit;
    private boolean isRightProhibit;

    public Date getEndTimeData() {
        return EndTimeData;
    }

    public void setEndTimeData(Date endTimeData) {
        EndTimeData = endTimeData;
    }
    @Bindable
    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
        notifyPropertyChanged(BR.endTime);
    }

    public Date getStartTimeData() {
        return StartTimeData;
    }

    public void setStartTimeData(Date startTimeData) {
        StartTimeData = startTimeData;
    }
    @Bindable
    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
        notifyPropertyChanged( BR.startTime);
    }
    @Bindable
    public boolean isLeftProhibit() {
        return isLeftProhibit;
    }

    public void setLeftProhibit(boolean leftProhibit) {
        isLeftProhibit = leftProhibit;
        notifyPropertyChanged( BR.leftProhibit);
    }
    @Bindable
    public boolean isRightProhibit() {
        return isRightProhibit;
    }

    public void setRightProhibit(boolean rightProhibit) {
        isRightProhibit = rightProhibit;
        notifyPropertyChanged( BR.rightProhibit);
    }
    @Bindable
    public String getCommonPage() {
        return commonPage;
    }

    public void setCommonPage(String commonPage) {
        this.commonPage = commonPage;
        notifyPropertyChanged( BR.commonPage);
    }
    @Bindable
    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
        notifyPropertyChanged( BR.currentPage);
    }
}
