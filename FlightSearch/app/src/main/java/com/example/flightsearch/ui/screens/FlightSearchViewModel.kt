package com.example.flightsearch.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.ColumnInfo
import com.example.flightsearch.FlightSearchApplication
import com.example.flightsearch.data.FlightsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

sealed interface FlightSearchUiState {
    data class Favourites(val favourites: List<FlightTrip>): FlightSearchUiState
    data class SearchResults(val lastSearch: StateFlow<List<FlightTrip>>): FlightSearchUiState
    data class Searching(val entry: StateFlow<List<SearchSuggestions>> ): FlightSearchUiState
}

class FlightSearchViewModel(
    private  val flightsRepository: FlightsRepository,
    //private val userPreferencesRepository: UserPreferencesRepository
): ViewModel(){

    var flightSearchUiState: FlightSearchUiState by mutableStateOf(
        FlightSearchUiState
            .Searching(entry = getListSearchSuggestions("int")
    ))
        private set



    fun getListSearchSuggestions(entry: String): StateFlow<List<SearchSuggestions>> {
        val databaseSuggestions = flightsRepository.getAirportSuggestions(entry)
        return databaseSuggestions.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = listOf<SearchSuggestions>()
        )
    }





    companion object{
        private const val TIMEOUT_MILLIS = 5_000L

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                val flightsRepository = application.container.flightsRepository
                FlightSearchViewModel(flightsRepository=flightsRepository)
            }
        }
    }

}

data class FlightTrip(
    val departIata: String,
    val departName: String,
    val arriveIata: String,
    val arriveName: String,
)

data class SearchSuggestions(
    @ColumnInfo(name = "iata_code") val iataCode: String,
    @ColumnInfo(name = "name") val airportName: String
)