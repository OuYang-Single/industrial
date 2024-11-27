package com.ijcsj.common_library.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ijcsj.common_library.bean.DatasBase;
import com.ijcsj.common_library.bean.SetUpBean;

import java.util.Date;
import java.util.List;

@Dao
public interface DataBaseDao {
    @Insert
    void insertAll(List<DatasBase>baseList);
    @Insert
    void insert(DatasBase baseList);

    @Delete
    void delete(DatasBase user);

    @Query("SELECT * FROM DatasBase  ORDER BY first_date_time  ")
    List<DatasBase> getAllData();
    @Query("SELECT * FROM DatasBase WHERE first_date_time BETWEEN :startTime AND :endTime  ORDER BY first_date_time")
    List<DatasBase> getAllTimeData(Date startTime, Date endTime);

    @Query("SELECT * FROM DatasBase WHERE first_date_time BETWEEN :startTime AND :endTime ORDER BY first_date_time ")
    List<DatasBase> getMessagesBetweenTime(Date startTime, Date endTime);

    @Query("SELECT * FROM DatasBase WHERE first_time = :first_time")
    DatasBase getUsersWithName(String first_time);
}
