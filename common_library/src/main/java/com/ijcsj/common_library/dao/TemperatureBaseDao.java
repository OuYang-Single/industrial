package com.ijcsj.common_library.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ijcsj.common_library.bean.HistoryBase;
import com.ijcsj.common_library.bean.TemperatureBase;

import java.util.Date;
import java.util.List;

@Dao
public interface TemperatureBaseDao {
    @Insert
    void insertAll(List<TemperatureBase>baseList);
    @Insert
    void insert(TemperatureBase baseList);

    @Delete
    void delete(TemperatureBase user);

    @Query("SELECT * FROM TemperatureBase  ORDER BY first_date_time  ")
    List<TemperatureBase> getAllData();
    @Query("SELECT * FROM TemperatureBase WHERE first_date_time BETWEEN :startTime AND :endTime  ORDER BY first_date_time")
    List<TemperatureBase> getAllTimeData(Date startTime, Date endTime);

    @Query("SELECT * FROM TemperatureBase WHERE first_date_time BETWEEN :startTime AND :endTime ORDER BY first_date_time ")
    List<TemperatureBase> getMessagesBetweenTime(Date startTime, Date endTime);

    @Query("SELECT * FROM TemperatureBase WHERE first_time = :first_time")
    TemperatureBase getUsersWithName(String first_time);
}
