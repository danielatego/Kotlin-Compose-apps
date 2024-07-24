package com.example.flightsearch.ui.screens

import android.util.Log
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
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FlightsRepository
import com.example.flightsearch.data.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface FlightSearchUiState {
    data class Favourites(val favourites: List<FlightTrip>): FlightSearchUiState
    data class SearchResults(val SearchList: List<FlightTrip>): FlightSearchUiState
    data class Searching(val suggestions: List<SearchSuggestions>): FlightSearchUiState
}

class FlightSearchViewModel(
    private  val flightsRepository: FlightsRepository,
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel(){
    /*
        this is the state of the search field
     */
    var searchUiState: SearchUiState by mutableStateOf(SearchUiState(editTextEntry = ""))
        private set

    var flightSearchUiState: StateFlow<FlightSearchUiState> by mutableStateOf(
        getListSearchSuggestions("")
    )
        private set

    init {
        var initialString: String=""
        viewModelScope.launch {
            initialString = userPreferencesRepository.lastSearchedFlight.first()
            if (initialString.isNotBlank())
            {
                searchUiState= SearchUiState(editTextEntry = initialString)
                flightSearchUiState= getListSearchSuggestions(initialString)
            }else{
                getListOfFavoriteDestinations()
            }
        }

    }

    fun getListSearchSuggestions(entry: String): StateFlow<FlightSearchUiState> {
        return flightsRepository.getAirportSuggestions(entry).map {
            FlightSearchUiState.Searching(it)
        }
            .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = FlightSearchUiState.Searching(listOf())
        )
    }
    fun getListOfDestinations(entry: String): StateFlow<FlightSearchUiState>{

        return flightsRepository.getFlights(entry).map {
           FlightSearchUiState.SearchResults(
               it
                   .map {
                   FlightTrip(
                       departIata = searchUiState.editTextEntry,
                       departName = searchUiState.airportName,
                       arriveIata = it.iata_code,
                       arriveName = it.name
                   )
               }
           )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = FlightSearchUiState.SearchResults(listOf())
        )
    }
    fun getListOfFavoriteDestinations() {

            viewModelScope.launch {
               flightSearchUiState= flightsRepository.getAllFavourites().map {
                    FlightSearchUiState.Favourites(
                        it
                            .map {
                                FlightTrip(
                                    departIata = it.departure_code,
                                    departName = flightsRepository.getAirportName(it.departure_code).first().name,
                                    arriveIata = it.destination_code,
                                    arriveName = flightsRepository.getAirportName(it.destination_code).first().name
                                )
                            }
                    )
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue = FlightSearchUiState.Favourites(listOf())
                )
            }

    }
    suspend fun addFavorite(favorite: Favorite){
        flightsRepository.addFavourite(favorite)
    }
    fun updateSearchUiState(entry: SearchUiState){
        searchUiState = entry
        viewModelScope.launch {
            userPreferencesRepository.saveLastSearchedItem(entry.editTextEntry)
        }
        if (entry.editTextEntry.isNotBlank()) {
            flightSearchUiState = getListSearchSuggestions(searchUiState.editTextEntry)
        }else{
            getListOfFavoriteDestinations()
        }
    }
    fun updateUiStateToShowFlights(entry: String,airportName: String){
        searchUiState = searchUiState.copy(editTextEntry=entry, airportName = airportName)
        flightSearchUiState = getListOfDestinations(entry)
    }
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                val flightsRepository = application.container.flightsRepository
                val userPreferencesRepository =application.userPreferencesRepository
                FlightSearchViewModel(
                    userPreferencesRepository=userPreferencesRepository,
                    flightsRepository=flightsRepository
                )
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
data class SearchUiState(
    val editTextEntry: String = "",
    val suggestions: List<SearchSuggestions> = listOf(),
    val airportName: String = ""
)

data class LastSearchedUiState(
    val searchedString: String = ""
)