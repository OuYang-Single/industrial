package com.ijcsj.common_library.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity
public class TemperatureBase {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "first_date_time")
    private Date dateTime;
    @ColumnInfo(name = "first_time")
    private String time;
    @ColumnInfo(name = "first_temperature")
    private int temperature;

    public TemperatureBase(){

    }
    public TemperatureBase(int temperature,Date dateTime){
        this.temperature=temperature;
        this.dateTime=dateTime;
    }
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
