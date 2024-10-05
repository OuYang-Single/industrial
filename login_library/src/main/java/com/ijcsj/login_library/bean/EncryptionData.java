package com.ijcsj.login_library.bean;

public class EncryptionData {
    /**
     * 加密key
     */
    private String ik;
    /**
     * 加密value
     */
    private String iv;

    public String getIk() {
        return ik;
    }

    public void setIk(String ik) {
        this.ik = ik;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}