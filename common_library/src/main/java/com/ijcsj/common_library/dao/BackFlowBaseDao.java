package com.ijcsj.common_library.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ijcsj.common_library.bean.BackFlowBase;
import com.ijcsj.common_library.bean.TemperatureBase;

import java.util.Date;
import java.util.List;

@Dao
public interface BackFlowBaseDao {
    @Insert
    void insertAll(List<BackFlowBase>baseList);
    @Insert
    void insert(BackFlowBase baseList);

    @Delete
    void delete(BackFlowBase user);

    @Query("SELECT * FROM BackFlowBase  ORDER BY first_date_time  ")
    List<BackFlowBase> getAllData();
    @Query("SELECT * FROM BackFlowBase WHERE first_date_time BETWEEN :startTime AND :endTime  ORDER BY first_date_time")
    List<BackFlowBase> getAllTimeData(Date startTime, Date endTime);

    @Query("SELECT * FROM BackFlowBase WHERE first_date_time BETWEEN :startTime AND :endTime ORDER BY first_date_time ")
    List<BackFlowBase> getMessagesBetweenTime(Date startTime, Date endTime);

    @Query("SELECT * FROM BackFlowBase WHERE first_time = :first_time")
    BackFlowBase getUsersWithName(String first_time);
}
