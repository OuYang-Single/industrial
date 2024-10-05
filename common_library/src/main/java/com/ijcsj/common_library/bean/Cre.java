package com.ijcsj.common_library.bean;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

import pw.xiaohaozi.adapter_plus.data.ViewTyper;



public class Cre implements Serializable {
    private String sessionToken;
    private String tmpSecretId;
    private String tmpSecretKey;

    public Cre(){}

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getTmpSecretId() {
        return tmpSecretId;
    }

    public void setTmpSecretId(String tmpSecretId) {
        this.tmpSecretId = tmpSecretId;
    }

    public String getTmpSecretKey() {
        return tmpSecretKey;
    }

    public void setTmpSecretKey(String tmpSecretKey) {
        this.tmpSecretKey = tmpSecretKey;
    }


}