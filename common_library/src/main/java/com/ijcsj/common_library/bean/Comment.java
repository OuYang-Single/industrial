package com.ijcsj.common_library.bean;

public class Comment {
    /**
     * 评论内容
     */
    private String content;
    /**
     * 课程目录id
     */
    private Long courseCatalogId;
    /**
     * 课程信息id
     */
    private long courseInfoId;

    private String  userName;
    public Comment(){

    }
    public Comment(String content,String userName,Long courseCatalogId,Long courseInfoId){
        this.content=content;
        this.userName=userName;
        this.courseCatalogId=courseCatalogId;
        this.courseInfoId=courseInfoId;
    }
    public String getContent() { return content; }
    public void setContent(String value) { this.content = value; }

    public Long getCourseCatalogId() { return courseCatalogId; }
    public void setCourseCatalogId(Long value) { this.courseCatalogId = value; }

    public long getCourseInfoId() { return courseInfoId; }
    public void setCourseInfoId(long value) { this.courseInfoId = value; }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
