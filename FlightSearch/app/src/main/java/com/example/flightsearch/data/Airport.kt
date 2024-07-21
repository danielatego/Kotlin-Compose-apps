package com.example.flightsearch.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport (
    @PrimaryKey()
    val id: Int = 0,
    val iata_code: String,
    val name: String,
    val passengers: Int
)