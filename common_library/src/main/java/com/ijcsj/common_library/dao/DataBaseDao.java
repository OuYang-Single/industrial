package com.ijcsj.common_library.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ijcsj.common_library.bean.DatasBase;
import com.ijcsj.common_library.bean.HistoryBase;
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


    @Query("SELECT * FROM DatasBase WHERE first_date_time BETWEEN :startTime AND :endTime ORDER BY first_date_time ")
    List<DatasBase> getMessagesBetweenTime(Date startTime, Date endTime);

    @Query("SELECT * FROM DatasBase WHERE data_base_canId =:canId  ORDER BY first_date_time ")
    List<DatasBase> getCanId(String canId);
    @Query("SELECT * FROM DatasBase WHERE data_base_canId =:canId  ORDER BY first_date_time DESC LIMIT :limit OFFSET :offset")
    List<DatasBase> getCanIds(String canId,int limit, int offset);
    @Query("SELECT * FROM DatasBase WHERE data_base_canId =:canId and data_base_type= :d  ORDER BY first_date_time DESC LIMIT :limit OFFSET :offset ")
    List<DatasBase> getCanIdAndTypes(String canId,boolean d,int limit, int offset);
    @Query("SELECT * FROM DatasBase WHERE data_base_canId =:canId and data_base_type= :d  ORDER BY first_date_time  ")
    List<DatasBase> getCanIdAndType(String canId,boolean d);
    @Query("SELECT * FROM DatasBase WHERE  data_base_type= :d  ORDER BY first_date_time ")
    List<DatasBase> getType(boolean d);
    @Query("SELECT * FROM DatasBase WHERE  data_base_type= :d  ORDER BY first_date_time DESC LIMIT :limit OFFSET :offset")
    List<DatasBase> getTypes(boolean d,int limit, int offset);
    @Query("SELECT * FROM DatasBase WHERE first_date_time  ORDER BY first_date_time DESC LIMIT :limit OFFSET :offset")
    List<DatasBase> getMessagesBetweenTime(int limit, int offset);
    @Query("SELECT * FROM DatasBase WHERE first_time = :first_time")
    DatasBase getUsersWithName(String first_time);
}
