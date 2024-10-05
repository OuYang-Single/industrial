package com.ijcsj.common_library.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ijcsj.common_library.BR;


public class Configuration extends BaseObservable {

    private  boolean isOrders=true;
    private  String order;
    private  String orderName="按升序排序";
    private  boolean mode=true;

    @Bindable
    public boolean isOrders() {
        return isOrders;
    }

    public void setOrders(boolean orders) {
        isOrders = orders;
        notifyPropertyChanged( BR.orders);
    }
    @Bindable
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
        notifyPropertyChanged( BR.order);
    }
    @Bindable
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
        notifyPropertyChanged( BR.orderName);
    }
    @Bindable
    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
        notifyPropertyChanged( BR.mode);
    }
}
