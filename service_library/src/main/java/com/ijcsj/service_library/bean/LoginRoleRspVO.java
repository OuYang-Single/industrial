package com.ijcsj.service_library.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class LoginRoleRspVO implements Parcelable, Serializable {
    private String roleCode;
    public LoginRoleRspVO() {

    }
    protected LoginRoleRspVO(Parcel in) {
        roleCode = in.readString();
    }

    public static final Creator<LoginRoleRspVO> CREATOR = new Creator<LoginRoleRspVO>() {
        @Override
        public LoginRoleRspVO createFromParcel(Parcel in) {
            return new LoginRoleRspVO(in);
        }

        @Override
        public LoginRoleRspVO[] newArray(int size) {
            return new LoginRoleRspVO[size];
        }
    };

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(roleCode);
    }
}