package com.ijcsj.common_library.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity
public class HistoryBase {
    @PrimaryKey(autoGenerate = true)
    private int uid;


    @ColumnInfo(name = "last_reason")
    private String reason;

    private String number;
    @ColumnInfo(name = "first_time")
    private String time;
    private Date eventTime;
    @ColumnInfo(name = "first_state")
    private String state;

   public HistoryBase(){

   }
    public HistoryBase(String number, String reason, String time, String state){
       this.reason=reason;
       this.number=number;
       this.time=time;
       this.state=state;
    }
    public HistoryBase(String number, String reason, String time, String state,Date eventTime){
        this.reason=reason;
        this.number=number;
        this.time=time;
        this.state=state;
        this.eventTime=eventTime;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
