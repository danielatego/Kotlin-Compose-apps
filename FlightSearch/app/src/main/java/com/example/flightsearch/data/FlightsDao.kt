package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flightsearch.ui.screens.SearchSuggestions
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightsDao {

    @Query("SELECT DISTINCT * from airport " +
            "WHERE iata_code LIKE '%'||:entry||'%' " +
            "OR name LIKE '%'||:entry||'%' " +
            "ORDER BY passengers DESC"
    )
    fun getAirportSuggestions(entry: String): Flow<List<SearchSuggestions>>

    @Query("SELECT * from airport " +
            "WHERE iata_code != :iata " +
            "ORDER BY passengers DESC")
    fun getFlights(iata: String): Flow<List<Airport>>

    @Insert(
        entity= Favorite::class ,
        onConflict = OnConflictStrategy.IGNORE
    )
    suspend fun addFavourite(flight: Favorite)

    @Query("SELECT * FROM airport " +
            "WHERE iata_code = :iata " +
            " LIMIT 1")
    fun getAirportName(iata: String): Flow<Airport>

    @Query("SELECT * FROM favorite")
    fun getAllFavourites(): Flow<List<Favorite>>

    @Delete(entity = Favorite::class)
    suspend fun removeFavorite(favorite: Favorite)
}