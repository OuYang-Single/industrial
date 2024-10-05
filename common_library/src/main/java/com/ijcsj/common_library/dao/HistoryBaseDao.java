package com.ijcsj.common_library.dao;


import androidx.paging.PagingSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ijcsj.common_library.bean.HistoryBase;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Dao
public interface HistoryBaseDao {
    @Insert
    void insertAll(HistoryBase... users);
    @Update
    void updateHistoryBase(HistoryBase user);
    @Delete
    void delete(HistoryBase user);

    @Delete
    void delete( List<HistoryBase> das);
    @Query("SELECT * FROM HistoryBase ")
    List<HistoryBase> getAllData();
    @Query("SELECT * FROM HistoryBase WHERE eventTime BETWEEN :startTime AND :endTime")
    List<HistoryBase> getAllTimeData(Date startTime, Date endTime);

    @Query("SELECT * FROM HistoryBase WHERE eventTime BETWEEN :startTime AND :endTime ORDER BY eventTime DESC LIMIT :limit OFFSET :offset")
    List<HistoryBase> getMessagesBetweenTime(Date startTime, Date endTime, int limit, int offset);


    @Query("SELECT * FROM HistoryBase WHERE last_reason = :last_reason AND first_state = :state")
    List<HistoryBase> getHistoryBaseWithSameData(String last_reason, String state);
}