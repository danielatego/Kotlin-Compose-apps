package com.example.flightsearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Favorite::class,Airport::class], version = 1, exportSchema = false)
abstract class FlightSearchDatabase: RoomDatabase() {

    abstract fun flightsDao(): FlightsDao

    companion object {
        @Volatile
        private var Instance: FlightSearchDatabase? = null

        fun getDatabase(context: Context): FlightSearchDatabase{
            // if the instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this){
                Room
                    .databaseBuilder(context,FlightSearchDatabase::class.java,"flights_database")
                    //.fallbackToDestructiveMigration(false)
                    .createFromAsset("database/flight_search.db")
                    .build()
            }
        }
    }
}