package com.ijcsj.common_library.bean

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ijcsj.common_library.dao.BackFlowBaseDao
import com.ijcsj.common_library.dao.DataBaseDao
import com.ijcsj.common_library.dao.HistoryBaseDao
import com.ijcsj.common_library.dao.SetUpBaseDao
import com.ijcsj.common_library.dao.TemperatureBaseDao
import com.ijcsj.common_library.util.Converters


@Database(version = 1, entities = [DatasBase::class])
@TypeConverters(Converters::class)
abstract class DataBaseDatabase : RoomDatabase() {
    abstract fun backFlowBaseDao(): DataBaseDao

    companion object {
        private var instance: DataBaseDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): DataBaseDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                DataBaseDatabase::class.java,
                "Data_Base_Data"
            ).build().apply {
                instance = this
            }
        }
    }
}