package com.ijcsj.common_library.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

import pw.xiaohaozi.adapter_plus.data.ViewTyper;

public class Datum implements Parcelable, Serializable , ViewTyper {
    /**
     * 课程目录idCode
     */
    private String catalogIdCode;
    /**
     * 目录名称
     */
    private String catalogName;
    /**
     * 父id(目录的id)
     */
    private Long courseCatalogId;
    /**
     * 课程信息
     */
    private List<CourseInfo> courseInfos;
    /**
     * 课程数量
     */
    private Long courseNums;
    private String courseNumsName;

    private boolean isExpand;
    /**
     * 课程类型(1-本地, 2-录播/在线,3-直播)
     */
    private Long courseType;
    /**
     * 视频封面url
     */
    private String coverUrl;
    /**
     * 商户id
     */
    private Long merchantId;
    /**
     * 播放次数
     */
    private Long playNums;
    /**
     * 排序
     */
    private Long sort;
    /**
     * 学生数量
     */
    private Long studentNums;


    protected Datum(Parcel in) {
        catalogIdCode = in.readString();
        catalogName = in.readString();
        if (in.readByte() == 0) {
            courseCatalogId = null;
        } else {
            courseCatalogId = in.readLong();
        }
        courseInfos = in.createTypedArrayList(CourseInfo.CREATOR);
        if (in.readByte() == 0) {
            courseNums = null;
        } else {
            courseNums = in.readLong();
        }
        if (in.readByte() == 0) {
            courseType = null;
        } else {
            courseType = in.readLong();
        }
        coverUrl = in.readString();
        if (in.readByte() == 0) {
            merchantId = null;
        } else {
            merchantId = in.readLong();
        }
        if (in.readByte() == 0) {
            playNums = null;
        } else {
            playNums = in.readLong();
        }
        if (in.readByte() == 0) {
            sort = null;
        } else {
            sort = in.readLong();
        }
        if (in.readByte() == 0) {
            studentNums = null;
        } else {
            studentNums = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(catalogIdCode);
        dest.writeString(catalogName);
        if (courseCatalogId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(courseCatalogId);
        }
        dest.writeTypedList(courseInfos);
        if (courseNums == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(courseNums);
        }
        if (courseType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(courseType);
        }
        dest.writeString(coverUrl);
        if (merchantId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(merchantId);
        }
        if (playNums == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(playNums);
        }
        if (sort == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(sort);
        }
        if (studentNums == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(studentNums);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        @Override
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };

    public String getCatalogIdCode() {
        return catalogIdCode;
    }

    public void setCatalogIdCode(String catalogIdCode) {
        this.catalogIdCode = catalogIdCode;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public Long getCourseCatalogId() {
        return courseCatalogId;
    }

    public void setCourseCatalogId(Long courseCatalogId) {
        this.courseCatalogId = courseCatalogId;
    }

    public List<CourseInfo> getCourseInfos() {
        return courseInfos;
    }

    public void setCourseInfos(List<CourseInfo> courseInfos) {
        this.courseInfos = courseInfos;
    }

    public Long getCourseNums() {
        return courseNums;
    }

    public void setCourseNums(Long courseNums) {
        this.courseNums = courseNums;
        setCourseNumsName(courseNums+"部视频");
    }

    public Long getCourseType() {
        return courseType;
    }

    public void setCourseType(Long courseType) {
        this.courseType = courseType;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getPlayNums() {
        return playNums;
    }

    public void setPlayNums(Long playNums) {
        this.playNums = playNums;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getStudentNums() {
        return studentNums;
    }

    public void setStudentNums(Long studentNums) {
        this.studentNums = studentNums;
    }

    @Override
    public int getItemViewType() {
        return 1;
    }

    public String getCourseNumsName() {
        return courseNumsName;
    }

    public void setCourseNumsName(String courseNumsName) {
        this.courseNumsName = courseNumsName;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }
}
