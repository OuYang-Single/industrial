package com.ijcsj.common_library.bean;

import java.time.OffsetDateTime;


public class PayRecord {
    /**
     * 当前播放持续时间(单位:毫秒)
     */
    private Long duration;
    /**
     * 播放结束时间
     */
    private String playEnd;
    /**
     * 播放记录UUid
     */
    private String playRecordUuid;
    /**
     * 播放开始时间
     */
    private String playStart;
    /**
     * 视频结束时间(单位:毫秒)
     */
    private Long playVideoEnd;
    /**
     * 视频播放的最长时间(单位:毫秒)
     */
    private Long playVideoMaxTime;
    /**
     * 视频开始时间(单位:毫秒)
     */
    private Long playVideoStart;

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getPlayEnd() {
        return playEnd;
    }

    public void setPlayEnd(String playEnd) {
        this.playEnd = playEnd;
    }

    public String getPlayRecordUuid() {
        return playRecordUuid;
    }

    public void setPlayRecordUuid(String playRecordUuid) {
        this.playRecordUuid = playRecordUuid;
    }

    public String getPlayStart() {
        return playStart;
    }

    public void setPlayStart(String playStart) {
        this.playStart = playStart;
    }

    public Long getPlayVideoEnd() {
        return playVideoEnd;
    }

    public void setPlayVideoEnd(Long playVideoEnd) {
        this.playVideoEnd = playVideoEnd;
    }

    public Long getPlayVideoMaxTime() {
        return playVideoMaxTime;
    }

    public void setPlayVideoMaxTime(Long playVideoMaxTime) {
        this.playVideoMaxTime = playVideoMaxTime;
    }

    public Long getPlayVideoStart() {
        return playVideoStart;
    }

    public void setPlayVideoStart(Long playVideoStart) {
        this.playVideoStart = playVideoStart;
    }
}