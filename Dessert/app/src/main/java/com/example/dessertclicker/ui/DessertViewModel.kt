package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import com.example.dessertclicker.ui.theme.DessertUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    private val desserts: List<Dessert> = Datasource.dessertList

    var numberOfDessertSold = 0

    var accruingRevenue = 0

    private fun selectDessert(): Dessert {

        var dessertToShow = desserts.first()

        for (dessert in desserts) {
            if (numberOfDessertSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }

    fun dessertClicked(){
        numberOfDessertSold++
        var dessert = selectDessert()
        accruingRevenue += dessert.price
        _uiState.update { currentState->
            currentState.copy(
                currentDessertSold = numberOfDessertSold,
                currentDessert = dessert,
                currentTotalRevenue = accruingRevenue
            )
        }
    }
    private fun resetGame() {
        //usedWords.clear()
        _uiState.value = DessertUiState()
    }
    init {
        resetGame()
    }
}