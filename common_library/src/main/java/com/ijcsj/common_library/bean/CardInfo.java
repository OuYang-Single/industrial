package com.ijcsj.common_library.bean;

import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ijcsj.common_library.BR;

public class CardInfo extends BaseObservable {
    private String name="--";
    private String idNum="--";
    private String viaBase64;
    private String fileJust;
    private String fileBack;
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (TextUtils.isEmpty(this.name)){
            this.name="--";
        }
        notifyPropertyChanged( BR.name);
    }
    @Bindable
    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
        if (TextUtils.isEmpty(this.idNum)){
            this.idNum="--";
        }
        notifyPropertyChanged( BR.idNum);
    }
    @Bindable
    public String getViaBase64() {
        return viaBase64;
    }

    public void setViaBase64(String viaBase64) {
        this.viaBase64 = viaBase64;
        notifyPropertyChanged( BR.viaBase64);
    }
    @Bindable
    public String getFileBack() {
        return fileBack;
    }

    public void setFileBack(String fileBack) {
        this.fileBack = fileBack;
        notifyPropertyChanged( BR.fileBack);
    }
    @Bindable
    public String getFileJust() {
        return fileJust;
    }

    public void setFileJust(String fileJust) {
        this.fileJust = fileJust;
        notifyPropertyChanged( BR.fileJust);
    }
}
