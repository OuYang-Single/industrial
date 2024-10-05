package com.ijcsj.login_library.bean;

import static com.ijcsj.common_library.util.AESExample.AES_CBC;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


import com.ijcsj.common_library.util.AESExample;
import com.ijcsj.common_library.util.AesUtils;
import com.ijcsj.common_library.util.StringGenerator;
import com.ijcsj.login_library.BR;

import java.io.Serializable;

public class LoginBean extends BaseObservable implements Serializable {
    private String phone="";
    private String translate="";
    private String password="";
    private String translateHint="发送验证码";
    private boolean translateTextColor=false;
    private boolean enabled=false;
    private boolean type=false;
    private boolean passwordDisplay=false;
    private boolean privacy=false;
    private String captchaKey;
    private  EncryptionData encryptionData;


    @Bindable
    public boolean isReturns() {
        return returns;
    }

    public void setReturns(boolean returns) {
        this.returns = returns;
        notifyPropertyChanged( BR.returns);
    }

    private boolean returns=false;

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged( BR.phone);
    }
    @Bindable
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        notifyPropertyChanged( BR.enabled);
    }
    @Bindable
    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
        notifyPropertyChanged( BR.translate);
    }
    @Bindable
    public String getTranslateHint() {
        return translateHint;
    }

    public void setTranslateHint(String translateHint) {
        this.translateHint = translateHint;
        notifyPropertyChanged( BR.translateHint);
    }
    @Bindable
    public boolean isTranslateTextColor() {
        return translateTextColor;
    }

    public void setTranslateTextColor(boolean translateTextColor) {
        this.translateTextColor = translateTextColor;
        notifyPropertyChanged( BR.translateTextColor);
    }
    @Bindable
    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
        notifyPropertyChanged( BR.type);
    }
    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged( BR.password);
    }
    @Bindable
    public boolean isPasswordDisplay() {
        return passwordDisplay;
    }

    public void setPasswordDisplay(boolean passwordDisplay) {
        this.passwordDisplay = passwordDisplay;
        notifyPropertyChanged( BR.passwordDisplay);
    }
    @Bindable
    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
        notifyPropertyChanged( BR.privacy);
    }
    public LoginTranslate getApifoxModel(){
        return new LoginTranslate( phone,translate,captchaKey);
    }

    public LoginParam getLoginParam(){
        return new LoginParam( null, StringGenerator.md5(encryptionData.getIk()+phone+encryptionData.getIv()), AESExample.aesEncrypt(password,"UTF-8",encryptionData.getIk(),encryptionData.getIv()),phone);
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public EncryptionData getEncryptionData() {
        return encryptionData;
    }

    public void setEncryptionData(EncryptionData encryptionData) {
        this.encryptionData = encryptionData;
    }
}
