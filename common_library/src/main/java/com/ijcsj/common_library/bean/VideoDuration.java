package com.ijcsj.common_library.bean;

public class VideoDuration {
    private  long     CourseInfoId;
    private  long     CurrentPosition;

    public VideoDuration(){

    }
    public VideoDuration(long     CourseInfoId,long     CurrentPosition){
        this.CourseInfoId=CourseInfoId;
        this.CurrentPosition=CurrentPosition;

    }

    public long getCourseInfoId() {
        return CourseInfoId;
    }

    public void setCourseInfoId(long courseInfoId) {
        CourseInfoId = courseInfoId;
    }

    public long getCurrentPosition() {
        return CurrentPosition;
    }

    public void setCurrentPosition(long currentPosition) {
        CurrentPosition = currentPosition;
    }
}
