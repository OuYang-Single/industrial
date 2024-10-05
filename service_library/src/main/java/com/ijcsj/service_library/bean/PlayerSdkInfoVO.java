package com.ijcsj.service_library.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class PlayerSdkInfoVO  implements Parcelable, Serializable {
    /**
     * sdk访问key
     */
    private String accessKey;
    /**
     * SDK初始化URI
     */
    private String initUri;
    public PlayerSdkInfoVO() {
    }
    protected PlayerSdkInfoVO(Parcel in) {
        accessKey = in.readString();
        initUri = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accessKey);
        dest.writeString(initUri);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlayerSdkInfoVO> CREATOR = new Creator<PlayerSdkInfoVO>() {
        @Override
        public PlayerSdkInfoVO createFromParcel(Parcel in) {
            return new PlayerSdkInfoVO(in);
        }

        @Override
        public PlayerSdkInfoVO[] newArray(int size) {
            return new PlayerSdkInfoVO[size];
        }
    };

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getInitUri() {
        return initUri;
    }

    public void setInitUri(String initUri) {
        this.initUri = initUri;
    }
}