package com.ijcsj.common_library.bean;

import com.tencent.cloud.huiyansdkface.facelight.process.FaceVerifyStatus;

import java.io.Serializable;

public class InputData implements Serializable {
    private String faceId;
    private String agreementNo;
    private String openApiAppId;
    private String openApiAppVersion;
    private String openApiNonce;
    private String openApiUserId;
    private String openApiSign;
    private String keyLicence;
    private FaceVerifyStatus.Mode verifyMode;

    public InputData(){

    }
    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public String getOpenApiAppId() {
        return openApiAppId;
    }

    public void setOpenApiAppId(String openApiAppId) {
        this.openApiAppId = openApiAppId;
    }

    public String getOpenApiAppVersion() {
        return openApiAppVersion;
    }

    public void setOpenApiAppVersion(String openApiAppVersion) {
        this.openApiAppVersion = openApiAppVersion;
    }

    public String getOpenApiNonce() {
        return openApiNonce;
    }

    public void setOpenApiNonce(String openApiNonce) {
        this.openApiNonce = openApiNonce;
    }

    public String getOpenApiUserId() {
        return openApiUserId;
    }

    public void setOpenApiUserId(String openApiUserId) {
        this.openApiUserId = openApiUserId;
    }

    public String getOpenApiSign() {
        return openApiSign;
    }

    public void setOpenApiSign(String openApiSign) {
        this.openApiSign = openApiSign;
    }

    public String getKeyLicence() {
        return keyLicence;
    }

    public void setKeyLicence(String keyLicence) {
        this.keyLicence = keyLicence;
    }

    public FaceVerifyStatus.Mode getVerifyMode() {
        return verifyMode;
    }

    public void setVerifyMode(FaceVerifyStatus.Mode verifyMode) {
        this.verifyMode = verifyMode;
    }
}
