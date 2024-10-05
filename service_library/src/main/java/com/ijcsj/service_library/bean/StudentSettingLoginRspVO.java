package com.ijcsj.service_library.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class StudentSettingLoginRspVO implements Parcelable, Serializable {
    /**
     * PC允许显示器数量
     */
    private Long allowedPcDisplaysNum;
    /**
     * 是否允许自定义解绑设备(0:不允许,1:允许)
     */
    private Boolean allowUnbindDevice;
    /**
     * 是否允许视频截图(0:不允许,1:允许)
     */
    private Boolean allowVideoCapture;
    /**
     * 语音验证是否可重复播放(1:允许,0:不允许)
     */
    private Boolean audioRepeat;
    /**
     * 到期时间
     */
    private String expireDate;
    /**
     * 是否强制绑定手机(1:强制绑定手机,0:不强制绑定手机)
     */
    private Boolean focePhone;
    /**
     * 是否强制实名认证(1:强制实名,0:不强制实名)
     */
    private Boolean forceRealname;
    /**
     * id
     */
    private Long id;
    /**
     * 业务id
     */
    private String idCode;
    /**
     * 商户id
     */
    private Long merchantId;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 学生用户信息
     */
    private Long studentId;

    public StudentSettingLoginRspVO(){

    }


    protected StudentSettingLoginRspVO(Parcel in) {
        if (in.readByte() == 0) {
            allowedPcDisplaysNum = null;
        } else {
            allowedPcDisplaysNum = in.readLong();
        }
        byte tmpAllowUnbindDevice = in.readByte();
        allowUnbindDevice = tmpAllowUnbindDevice == 0 ? null : tmpAllowUnbindDevice == 1;
        byte tmpAllowVideoCapture = in.readByte();
        allowVideoCapture = tmpAllowVideoCapture == 0 ? null : tmpAllowVideoCapture == 1;
        byte tmpAudioRepeat = in.readByte();
        audioRepeat = tmpAudioRepeat == 0 ? null : tmpAudioRepeat == 1;
        expireDate = in.readString();
        byte tmpFocePhone = in.readByte();
        focePhone = tmpFocePhone == 0 ? null : tmpFocePhone == 1;
        byte tmpForceRealname = in.readByte();
        forceRealname = tmpForceRealname == 0 ? null : tmpForceRealname == 1;
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        idCode = in.readString();
        if (in.readByte() == 0) {
            merchantId = null;
        } else {
            merchantId = in.readLong();
        }
        remark = in.readString();
        if (in.readByte() == 0) {
            studentId = null;
        } else {
            studentId = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (allowedPcDisplaysNum == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(allowedPcDisplaysNum);
        }
        dest.writeByte((byte) (allowUnbindDevice == null ? 0 : allowUnbindDevice ? 1 : 2));
        dest.writeByte((byte) (allowVideoCapture == null ? 0 : allowVideoCapture ? 1 : 2));
        dest.writeByte((byte) (audioRepeat == null ? 0 : audioRepeat ? 1 : 2));
        dest.writeString(expireDate);
        dest.writeByte((byte) (focePhone == null ? 0 : focePhone ? 1 : 2));
        dest.writeByte((byte) (forceRealname == null ? 0 : forceRealname ? 1 : 2));
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(idCode);
        if (merchantId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(merchantId);
        }
        dest.writeString(remark);
        if (studentId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(studentId);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StudentSettingLoginRspVO> CREATOR = new Creator<StudentSettingLoginRspVO>() {
        @Override
        public StudentSettingLoginRspVO createFromParcel(Parcel in) {
            return new StudentSettingLoginRspVO(in);
        }

        @Override
        public StudentSettingLoginRspVO[] newArray(int size) {
            return new StudentSettingLoginRspVO[size];
        }
    };

    public Long getAllowedPcDisplaysNum() {
        return allowedPcDisplaysNum;
    }

    public void setAllowedPcDisplaysNum(Long allowedPcDisplaysNum) {
        this.allowedPcDisplaysNum = allowedPcDisplaysNum;
    }

    public Boolean getAllowUnbindDevice() {
        return allowUnbindDevice;
    }

    public void setAllowUnbindDevice(Boolean allowUnbindDevice) {
        this.allowUnbindDevice = allowUnbindDevice;
    }

    public Boolean getAllowVideoCapture() {
        return allowVideoCapture;
    }

    public void setAllowVideoCapture(Boolean allowVideoCapture) {
        this.allowVideoCapture = allowVideoCapture;
    }

    public Boolean getAudioRepeat() {
        return audioRepeat;
    }

    public void setAudioRepeat(Boolean audioRepeat) {
        this.audioRepeat = audioRepeat;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Boolean getFocePhone() {
        return focePhone;
    }

    public void setFocePhone(Boolean focePhone) {
        this.focePhone = focePhone;
    }

    public Boolean getForceRealname() {
        return forceRealname;
    }

    public void setForceRealname(Boolean forceRealname) {
        this.forceRealname = forceRealname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
