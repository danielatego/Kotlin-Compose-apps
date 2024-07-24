package com.example.flightsearch.data

import com.example.flightsearch.ui.screens.SearchSuggestions
import kotlinx.coroutines.flow.Flow

interface FlightsRepository {

    // get real time search suggestions as a user types while searching

    fun getAirportSuggestions(entry: String): Flow<List<SearchSuggestions>>

    // get a list of flight destinations from a selected airport

    fun getFlights(iata: String): Flow<List<Airport>>

    // Add a flight trip to favorites table

    suspend fun addFavourite(flight: Favorite)

    // Get airport details from an IATA code

    fun getAirportName(iata: String): Flow<Airport>

    // Get a list of all your favorite trips

    fun getAllFavourites(): Flow<List<Favorite>>

    // Remove a trip from favorites table

    suspend fun removeFavorite(favorite: Favorite)
}