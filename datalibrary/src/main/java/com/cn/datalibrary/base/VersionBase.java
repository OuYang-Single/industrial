package com.cn.datalibrary.base;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.cn.datalibrary.BR;

public class VersionBase extends BaseObservable {
    private String ver="";
    @Bindable
    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
        notifyPropertyChanged( BR.ver);
    }
}
