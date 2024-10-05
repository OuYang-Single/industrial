package com.ijcsj.common_library.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yaoxiaowen.download.FileInfo;
import com.yaoxiaowen.download.config.InnerConstant;
import com.yaoxiaowen.download.db.DbHolder;
import com.yaoxiaowen.download.db.DbOpenHelper;

import java.io.File;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class DbHolders  {
    private Context context;
    private SQLiteDatabase mDb;

    public DbHolders(Context context) {
        this.context = context;
        mDb = new DbOpenHelper(context).getWritableDatabase();
    }
    @SuppressLint("Range")
    public  List<FileInfo> getFileInfo(){

// 假设db是一个已经打开的SQLiteDatabase实例，表名为tableName
        Cursor cursor = mDb.query(InnerConstant.Db.NAME_TABALE, null, null, null, null, null, null);
        List<FileInfo> list=new ArrayList<>();

        while (cursor.moveToNext()){
            FileInfo downloadFile = new FileInfo();
            downloadFile.setId( cursor.getString(cursor.getColumnIndex( InnerConstant.Db.id)) );
            downloadFile.setDownloadUrl( cursor.getString(cursor.getColumnIndex( InnerConstant.Db.downloadUrl)) );
            downloadFile.setFilePath( cursor.getString(cursor.getColumnIndex( InnerConstant.Db.filePath)) );
            downloadFile.setSize( cursor.getLong( cursor.getColumnIndex(InnerConstant.Db.size)) );
            downloadFile.setDownloadLocation( cursor.getLong( cursor.getColumnIndex(InnerConstant.Db.downloadLocation)));
            downloadFile.setDownloadStatus( cursor.getInt(cursor.getColumnIndex(InnerConstant.Db.downloadStatus)) );
            list.add(downloadFile);
        }
        cursor.close();
        return list;
    }
    public void deleteFileInfo(String id){
        if (has(id)){
            mDb.delete(InnerConstant.Db.NAME_TABALE, InnerConstant.Db.id + " = ?", new String[]{id});
        }
    }
    private boolean has(String id){
        Cursor cursor = mDb.query(InnerConstant.Db.NAME_TABALE, null,  " " + InnerConstant.Db.id + " = ? ", new String[]{id}, null, null, null);
        boolean has = cursor.moveToNext();
        cursor.close();
        return has;
    }
}
