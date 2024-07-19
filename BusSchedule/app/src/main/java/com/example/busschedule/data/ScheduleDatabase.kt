package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [BusSchedule::class], version = 1, exportSchema = false)
 abstract class ScheduleDatabase: RoomDatabase() {

   abstract fun busScheduleDao(): BusScheduleDao

    companion object {
        @Volatile
        private var Instance: ScheduleDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): ScheduleDatabase{
            return Instance ?: synchronized(this){
                Room
                    .databaseBuilder(context,ScheduleDatabase::class.java,"schedule_database")
                    .fallbackToDestructiveMigration()
                    .createFromAsset("database/bus_schedule.db")
                    .build()
            }
        }
    }
}