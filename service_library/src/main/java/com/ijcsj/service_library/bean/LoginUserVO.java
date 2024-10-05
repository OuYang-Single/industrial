package com.ijcsj.service_library.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ijcsj.service_library.BR;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class LoginUserVO  extends BaseObservable implements Parcelable, Serializable {
    /**
     * 激活时间
     */
    private String activationTime;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 是否有绑定微信
     */
    private Boolean bindWechat;
    /**
     * 描述
     */
    private String description;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 到期时间
     */
    private String expireTime;
    /**
     * id
     */
    private Long id;
    /**
     * 最近访问时间
     */
    private String lastLoginTime;
    /**
     * 机构ID
     */
    private Long merchantId;
    /**
     * 机构名称
     */
    private String merchantName;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 新购/续费
     */
    private Boolean newPurchase;
    /**
     * 真实姓名
     */
    private String realname;
    /**
     * 性别 0男 1女 2保密
     */
    private Long sex;
    /**
     * 身份（0-本公司员工,1-机构人员,2-机构学生）
     */
    private String userIdentity;
    /**
     * 用户名
     */
    private String username;
    /**
     * 版本级别(1:试用版,2:标准版,3:旗舰版,4:至尊版)
     */
    private Long versionLevel;
    public LoginUserVO() {

    }

    protected LoginUserVO(Parcel in) {
        activationTime = in.readString();
        avatar = in.readString();
        byte tmpBindWechat = in.readByte();
        bindWechat = tmpBindWechat == 0 ? null : tmpBindWechat == 1;
        description = in.readString();
        email = in.readString();
        expireTime = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        lastLoginTime = in.readString();
        if (in.readByte() == 0) {
            merchantId = null;
        } else {
            merchantId = in.readLong();
        }
        merchantName = in.readString();
        mobile = in.readString();
        byte tmpNewPurchase = in.readByte();
        newPurchase = tmpNewPurchase == 0 ? null : tmpNewPurchase == 1;
        realname = in.readString();
        if (in.readByte() == 0) {
            sex = null;
        } else {
            sex = in.readLong();
        }
        userIdentity = in.readString();
        username = in.readString();
        if (in.readByte() == 0) {
            versionLevel = null;
        } else {
            versionLevel = in.readLong();
        }
    }

    public static final Creator<LoginUserVO> CREATOR = new Creator<LoginUserVO>() {
        @Override
        public LoginUserVO createFromParcel(Parcel in) {
            return new LoginUserVO(in);
        }

        @Override
        public LoginUserVO[] newArray(int size) {
            return new LoginUserVO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(activationTime);
        parcel.writeString(avatar);
        parcel.writeByte((byte) (bindWechat == null ? 0 : bindWechat ? 1 : 2));
        parcel.writeString(description);
        parcel.writeString(email);
        parcel.writeString(expireTime);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(lastLoginTime);
        if (merchantId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(merchantId);
        }
        parcel.writeString(merchantName);
        parcel.writeString(mobile);
        parcel.writeByte((byte) (newPurchase == null ? 0 : newPurchase ? 1 : 2));
        parcel.writeString(realname);
        if (sex == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(sex);
        }
        parcel.writeString(userIdentity);
        parcel.writeString(username);
        if (versionLevel == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(versionLevel);
        }
    }

    public String getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(String activationTime) {
        this.activationTime = activationTime;
    }
    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged( BR.avatar);
    }

    public Boolean getBindWechat() {
        return bindWechat;
    }

    public void setBindWechat(Boolean bindWechat) {
        this.bindWechat = bindWechat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Bindable
    public String getExpireTime() {
        if (!expireTime.contains("有效期")) {
            this.expireTime="有效期："+expireTime;
        }
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime="有效期："+expireTime;
        notifyPropertyChanged( BR.expireTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getNewPurchase() {
        return newPurchase;
    }

    public void setNewPurchase(Boolean newPurchase) {
        this.newPurchase = newPurchase;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged( BR.username);
    }

    public Long getVersionLevel() {
        return versionLevel;
    }

    public void setVersionLevel(Long versionLevel) {
        this.versionLevel = versionLevel;
    }
}
