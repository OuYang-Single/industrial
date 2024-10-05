package com.ijcsj.common_library.bean;

import java.io.Serializable;

public class DownloadInit implements Serializable {
    private String fileUrl;
    private long courseInfoId;
    /**
     * APPID
     */
    private String appId;
    /**
     * cos  bucket
     */
    private String bucket;
    /**
     * cos临时秘钥信息
     */
    private Cre cre;
    /**
     * cos  dir
     */
    private String dir;
    private long expiredTime;
    /**
     * cos过期时间
     */
    private String filekey;
    /**
     * 预减id
     */
    private long predeductId;
    private String region;
    /**
     * 有效时间
     */
    private long startTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public Cre getCre() {
        return cre;
    }

    public void setCre(Cre cre) {
        this.cre = cre;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getFilekey() {
        return filekey;
    }

    public void setFilekey(String filekey) {
        this.filekey = filekey;
    }

    public long getPredeductId() {
        return predeductId;
    }

    public void setPredeductId(long predeductId) {
        this.predeductId = predeductId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public long getCourseInfoId() {
        return courseInfoId;
    }

    public void setCourseInfoId(long courseInfoId) {
        this.courseInfoId = courseInfoId;
    }
}