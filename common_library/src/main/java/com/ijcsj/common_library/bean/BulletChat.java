package com.ijcsj.common_library.bean;

public class BulletChat {
    /**
     * 弹幕内容
     */
    private String content;
    /**
     * 课程信息id
     */
    private long courseInfoId;
    private String userName;
    /**
     * 字体颜色
     */
    private String fontColor;
    /**
     * 字体位置(1:顶部;2:中部;3:底部)
     */
    private long fontSite;
    /**
     * 字体大小
     */
    private long fontSize;
    /**
     * 视频时间(单位:秒)
     */
    private long videoTime;
    public BulletChat(){

    }
    public BulletChat(String content, long courseInfoId, String fontColor, long fontSite, long fontSize, long videoTime){
        this.content=content;
        this.courseInfoId=courseInfoId;
        this.fontColor=fontColor;
        this.fontSite=fontSite;
        this.fontSize=fontSize;
        this.videoTime=videoTime;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCourseInfoId() {
        return courseInfoId;
    }

    public void setCourseInfoId(long courseInfoId) {
        this.courseInfoId = courseInfoId;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public long getFontSite() {
        return fontSite;
    }

    public void setFontSite(long fontSite) {
        this.fontSite = fontSite;
    }

    public long getFontSize() {
        return fontSize;
    }

    public void setFontSize(long fontSize) {
        this.fontSize = fontSize;
    }

    public long getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(long videoTime) {
        this.videoTime = videoTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
