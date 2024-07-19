package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow


interface BusScheduleRepository {
    fun getAllSchedulesStream(): Flow<List<BusSchedule>>

    fun getSingleScheduleStream(stopName: String): Flow<List<BusSchedule>>
}

