package com.ijcsj.common_library.bean

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ijcsj.common_library.dao.BackFlowBaseDao
import com.ijcsj.common_library.dao.HistoryBaseDao
import com.ijcsj.common_library.dao.SetUpBaseDao
import com.ijcsj.common_library.dao.TemperatureBaseDao
import com.ijcsj.common_library.util.Converters


@Database(version = 1, entities = [SetUpBean::class])
@TypeConverters(Converters::class)
abstract class SetUpBaseDatabase : RoomDatabase() {
    abstract fun backFlowBaseDao(): SetUpBaseDao

    companion object {
        private var instance: SetUpBaseDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): SetUpBaseDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                SetUpBaseDatabase::class.java,
                "SetUp_Base_Data"
            ).build().apply {
                instance = this
            }
        }
    }
}