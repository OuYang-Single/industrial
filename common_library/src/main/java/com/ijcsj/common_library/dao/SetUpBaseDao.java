package com.ijcsj.common_library.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ijcsj.common_library.bean.BackFlowBase;
import com.ijcsj.common_library.bean.SetUpBean;

import java.util.Date;
import java.util.List;

@Dao
public interface SetUpBaseDao {
    @Insert
    void insertAll(List<SetUpBean>baseList);
    @Insert
    void insert(SetUpBean baseList);

    @Delete
    void delete(SetUpBean user);

    @Query("SELECT * FROM SetUpBean  ORDER BY first_date_time  ")
    List<SetUpBean> getAllData();
    @Query("SELECT * FROM SetUpBean WHERE first_date_time BETWEEN :startTime AND :endTime  ORDER BY first_date_time")
    List<SetUpBean> getAllTimeData(Date startTime, Date endTime);

    @Query("SELECT * FROM SetUpBean WHERE first_date_time BETWEEN :startTime AND :endTime ORDER BY first_date_time ")
    List<SetUpBean> getMessagesBetweenTime(Date startTime, Date endTime);

    @Query("SELECT * FROM SetUpBean WHERE first_time = :first_time")
    SetUpBean getUsersWithName(String first_time);
}
