package com.example.flightsearch.data

import com.example.flightsearch.ui.screens.SearchSuggestions
import kotlinx.coroutines.flow.Flow

class OfflineFlightsRepository(private val flightsDao: FlightsDao): FlightsRepository {

    override fun getAllFavourites(): Flow<List<Favorite>> {
        return flightsDao.getAllFavourites()
    }

    override suspend fun addFavourite(flight: Favorite) {
        return flightsDao.addFavourite(flight)
    }

    override fun getAirportName(iata: String): Flow<Airport> {
        return flightsDao.getAirportName(iata)
    }

    override fun getAirportSuggestions(entry: String): Flow<List<SearchSuggestions>> {
        return flightsDao.getAirportSuggestions(entry)
    }

    override fun getFlights(iata: String): Flow<List<Airport>> {
       return flightsDao.getFlights(iata)
    }

    override suspend fun removeFavorite(favorite: Favorite) {
        return flightsDao.removeFavorite(favorite)
    }
}