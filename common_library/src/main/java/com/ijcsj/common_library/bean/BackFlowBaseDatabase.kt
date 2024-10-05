package com.ijcsj.common_library.bean

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ijcsj.common_library.dao.BackFlowBaseDao
import com.ijcsj.common_library.dao.HistoryBaseDao
import com.ijcsj.common_library.dao.TemperatureBaseDao
import com.ijcsj.common_library.util.Converters


@Database(version = 1, entities = [BackFlowBase::class])
@TypeConverters(Converters::class)
abstract class BackFlowBaseDatabase : RoomDatabase() {
    abstract fun backFlowBaseDao(): BackFlowBaseDao

    companion object {
        private var instance: BackFlowBaseDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): BackFlowBaseDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                BackFlowBaseDatabase::class.java,
                "BackFlow_Base_Data"
            ).build().apply {
                instance = this
            }
        }
    }
}