package com.ijcsj.common_library.bean

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ijcsj.common_library.dao.HistoryBaseDao
import com.ijcsj.common_library.dao.TemperatureBaseDao
import com.ijcsj.common_library.util.Converters


@Database(version = 1, entities = [TemperatureBase::class])
@TypeConverters(Converters::class)
abstract class TemperatureBaseDatabase : RoomDatabase() {
    abstract fun temperatureBaseDao(): TemperatureBaseDao

    companion object {
        private var instance: TemperatureBaseDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): TemperatureBaseDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                TemperatureBaseDatabase::class.java,
                "Temperature_Base_Data"
            ).build().apply {
                instance = this
            }
        }
    }
}