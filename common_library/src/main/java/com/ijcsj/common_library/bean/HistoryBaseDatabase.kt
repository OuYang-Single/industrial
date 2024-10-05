package com.ijcsj.common_library.bean

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ijcsj.common_library.dao.HistoryBaseDao
import com.ijcsj.common_library.util.Converters

@Database(version = 1, entities = [HistoryBase::class])
@TypeConverters(Converters::class)
abstract class HistoryBaseDatabase : RoomDatabase() {
    abstract fun historyBaseDao(): HistoryBaseDao

    companion object {
        private var instance: HistoryBaseDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): HistoryBaseDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                HistoryBaseDatabase::class.java,
                "app_database"
            ).build().apply {
                instance = this
            }
        }
    }
}