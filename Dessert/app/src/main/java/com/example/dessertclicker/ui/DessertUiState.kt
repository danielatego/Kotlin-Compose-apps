package com.example.dessertclicker.ui.theme

import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert

data class DessertUiState(
    val currentDessert: Dessert = Datasource.dessertList[0],
    val currentDessertSold: Int = 0,
    val currentTotalRevenue: Int = 0,
)
