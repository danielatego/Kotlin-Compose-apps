package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineBusScheduleRepository(private val busScheduleDao: BusScheduleDao): BusScheduleRepository {
    override fun getAllSchedulesStream(): Flow<List<BusSchedule>> {
        return busScheduleDao.getAllItems()
    }

    override fun getSingleScheduleStream(stopName: String): Flow<List<BusSchedule>> {
        return busScheduleDao.getItem(stopName)
    }
}