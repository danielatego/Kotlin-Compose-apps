package com.example.mycity.ui

import com.example.mycity.data.Destination
import com.example.mycity.data.DestinationCategory
import com.example.mycity.data.LocalDestinationsProvider

data class MyCityUiState(
    val cityDestinations: Map<DestinationCategory,List<Destination>> = emptyMap(),
    val currentCityCategory: DestinationCategory = DestinationCategory.Wildlife,
    val currentSelectedDestination: Destination = LocalDestinationsProvider.defaultDestination,
    val isShowingHomepage: Boolean = true,
){
    val currentCityCategoryDestinations: List<Destination> by lazy { cityDestinations[currentCityCategory]!! }
}
