package com.ijcsj.common_library.bean;

import java.io.Serializable;

public class FaceInit implements Serializable {
    /**
     * 对应ios:orderNo
     */
    private String agreementNo;
    private String faceId;
    private String keyLicence;
    /**
     * 对应IOS:webankAppId
     */
    private String openApiAppId;
    /**
     * 对应IOS:version
     */
    private String openApiAppVersion;
    /**
     * 对应IOS:nonce
     */
    private String openApiNonce;
    /**
     * 对应IOS:userId
     */
    private String openApiUserId;
    /**
     * 签名信息
     */
    private String sign;

    public String getAgreementNo() { return agreementNo; }
    public void setAgreementNo(String value) { this.agreementNo = value; }

    public String getFaceId() { return faceId; }
    public void setFaceId(String value) { this.faceId = value; }

    public String getKeyLicence() { return keyLicence; }
    public void setKeyLicence(String value) { this.keyLicence = value; }

    public String getOpenApiAppId() { return openApiAppId; }
    public void setOpenApiAppId(String value) { this.openApiAppId = value; }

    public String getOpenApiAppVersion() { return openApiAppVersion; }
    public void setOpenApiAppVersion(String value) { this.openApiAppVersion = value; }

    public String getOpenApiNonce() { return openApiNonce; }
    public void setOpenApiNonce(String value) { this.openApiNonce = value; }

    public String getOpenApiUserId() { return openApiUserId; }
    public void setOpenApiUserId(String value) { this.openApiUserId = value; }

    public String getSign() { return sign; }
    public void setSign(String value) { this.sign = value; }
}
