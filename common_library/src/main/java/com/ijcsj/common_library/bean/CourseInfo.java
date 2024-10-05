package com.ijcsj.common_library.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import pw.xiaohaozi.adapter_plus.data.ViewTyper;

public class CourseInfo  implements Parcelable, Serializable , ViewTyper {
    /**
     * 累计播放时间(单位:s)
     */
    private Object accuPlayDuration;
    /**
     * 终端类型
     */
    private Object clientType;
    /**
     * 父id(目录的id)
     */
    private long courseCatalogId;
    /**
     * 课程描述
     */
    private String courseDesc;
    /**
     * 课程信息id
     */
    private long courseInfoId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程类型(1-本地, 2-录播/在线,3-直播)
     */
    private long courseType;
    /**
     * 视频封面url
     */
    private String coverUrl;
    /**
     * 商户id
     */
    private long merchantId;
    /**
     * 最近播放时间
     */
    private Object playLastTime;
    /**
     * 播放进度
     */
    private long playRate;
    /**
     * 排序
     */
    private long sort;
    /**
     * 视频扩展名
     */
    private String videoExt;
    private String courseInfoIdCode;
    /**
     * 最后播放的时间点(单位:毫秒)即 播放的视频位置
     */
    private long videoLastPlayTime;
    /**
     * 视频大小(kb)
     */
    private long videoSize;
    private long downloadLocation; // 下载的位置(就是当前已经下载过的size，也是断点的位置)
    private long size;   //文件的总尺寸
    /**
     * 视频大小字符串(kb)
     */
    private String videoSizeStr;
    private String videoSizeStrMb;
    /**
     * 时长(毫秒)
     */
    private long whenLong;
    private String whenLongS;
    private String imGroupId;
    public CourseInfo() {

    }
    protected CourseInfo(Parcel in) {
        courseCatalogId = in.readLong();
        courseDesc = in.readString();
        courseInfoId = in.readLong();
        courseName = in.readString();
        courseType = in.readLong();
        coverUrl = in.readString();
        merchantId = in.readLong();
        playRate = in.readLong();
        sort = in.readLong();
        videoExt = in.readString();
        videoLastPlayTime = in.readLong();
        videoSize = in.readLong();
        videoSizeStr = in.readString();
        whenLong = in.readLong();
    }

    public static final Creator<CourseInfo> CREATOR = new Creator<CourseInfo>() {
        @Override
        public CourseInfo createFromParcel(Parcel in) {
            return new CourseInfo(in);
        }

        @Override
        public CourseInfo[] newArray(int size) {
            return new CourseInfo[size];
        }
    };

    public Object getAccuPlayDuration() {
        return accuPlayDuration;
    }

    public void setAccuPlayDuration(Object accuPlayDuration) {
        this.accuPlayDuration = accuPlayDuration;
    }

    public Object getClientType() {
        return clientType;
    }

    public void setClientType(Object clientType) {
        this.clientType = clientType;
    }

    public long getCourseCatalogId() {
        return courseCatalogId;
    }

    public void setCourseCatalogId(long courseCatalogId) {
        this.courseCatalogId = courseCatalogId;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public long getCourseInfoId() {
        return courseInfoId;
    }

    public void setCourseInfoId(long courseInfoId) {
        this.courseInfoId = courseInfoId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getCourseType() {
        return courseType;
    }

    public void setCourseType(long courseType) {
        this.courseType = courseType;
    }

    public String getCoverUrl() {

        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public Object getPlayLastTime() {
        return playLastTime;
    }

    public void setPlayLastTime(Object playLastTime) {
        this.playLastTime = playLastTime;
    }

    public long getPlayRate() {
        return playRate;
    }

    public void setPlayRate(long playRate) {
        this.playRate = playRate;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public String getVideoExt() {
        return videoExt;
    }

    public void setVideoExt(String videoExt) {
        this.videoExt = videoExt;
    }

    public long getVideoLastPlayTime() {
        return videoLastPlayTime;
    }

    public void setVideoLastPlayTime(long videoLastPlayTime) {
        this.videoLastPlayTime = videoLastPlayTime;
    }

    public long getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(long videoSize) {
        this.videoSize = videoSize;

    }

    public String getVideoSizeStr() {
        return videoSizeStr;
    }

    public void setVideoSizeStr(String videoSizeStr) {
        this.videoSizeStr = videoSizeStr;
    }

    public long getWhenLong() {
        return whenLong;
    }

    public void setWhenLong(long whenLong) {
        this.whenLong = whenLong;

    }
    public static String convertMilliSecondToHMS(long milliseconds) {
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
        milliseconds -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(courseCatalogId);
        parcel.writeString(courseDesc);
        parcel.writeLong(courseInfoId);
        parcel.writeString(courseName);
        parcel.writeLong(courseType);
        parcel.writeString(coverUrl);
        parcel.writeLong(merchantId);
        parcel.writeLong(playRate);
        parcel.writeLong(sort);
        parcel.writeString(videoExt);
        parcel.writeLong(videoLastPlayTime);
        parcel.writeLong(videoSize);
        parcel.writeString(videoSizeStr);
        parcel.writeLong(whenLong);
    }

    @Override
    public int getItemViewType() {
        return 2;
    }

    public String getVideoSizeStrMb() {
        setVideoSizeStrMb(videoSize/1024+"MB");
        return videoSizeStrMb;
    }

    public void setVideoSizeStrMb(String videoSizeStrMb) {
        this.videoSizeStrMb = videoSizeStrMb;
    }

    public String getWhenLongS() {
        setWhenLongS(convertMilliSecondToHMS(whenLong));
        return whenLongS;
    }

    public void setWhenLongS(String whenLongS) {
        this.whenLongS = whenLongS;
    }

    public long getDownloadLocation() {
        return downloadLocation;
    }

    public void setDownloadLocation(long downloadLocation) {
        this.downloadLocation = downloadLocation;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getCourseInfoIdCode() {
        return courseInfoIdCode;
    }

    public void setCourseInfoIdCode(String courseInfoIdCode) {
        this.courseInfoIdCode = courseInfoIdCode;
    }

    public String getImGroupId() {
        return imGroupId;
    }

    public void setImGroupId(String imGroupId) {
        this.imGroupId = imGroupId;
    }
}
