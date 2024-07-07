package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.Destination
import com.example.mycity.data.DestinationCategory
import com.example.mycity.data.LocalDestinationsProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState

    init {
        initializeUIState()
    }
    private fun initializeUIState() {
        val groupedDestinations: Map<DestinationCategory,List<Destination>> =
            LocalDestinationsProvider.allDestinations.groupBy {
                it.category
            }
        _uiState.value =
            MyCityUiState(
                cityDestinations = groupedDestinations,
                currentSelectedDestination = groupedDestinations[DestinationCategory.Wildlife]?.get(0)
                    ?: LocalDestinationsProvider.defaultDestination
            )
    }

    // Toggles from the list view home screen to the destination screen
    fun showDestinationDetailsScreen(destination: Destination){
        _uiState.update {
            it.copy(
                currentSelectedDestination = destination,
                isShowingHomepage = false,
            )
        }
    }

    // Resets back to the list view form the destination description screen
    fun backToListView(){
        _uiState.update {
            it.copy(
                isShowingHomepage = true,

            )
        }
    }

    // Updates the currently selected category from the menu
    fun changeDestinationCategory( destinationCategory: DestinationCategory){
        _uiState.update {
            it.copy(
                currentCityCategory = destinationCategory
            )
        }
    }

}