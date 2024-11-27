package com.ijcsj.common_library.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class DatasBase {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "first_date_time")
    private Date dateTime;
    @ColumnInfo(name = "first_time")
    private String time;
    @ColumnInfo(name = "data_base_data")
    private String data;
    @ColumnInfo(name = "data_base_canId")
    private String canId;
    @ColumnInfo(name = "data_base_type")
    private boolean type;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCanId() {
        return canId;
    }

    public void setCanId(String canId) {
        this.canId = canId;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
