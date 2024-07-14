package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.AmphibianData
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibianUiState{

    data class Success(val photos: List<AmphibianData>): AmphibianUiState
    object Error: AmphibianUiState
    object Loading: AmphibianUiState
}

class AmphibiansViewModel():ViewModel(){

    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)

    init {
        getAmphibiansDataList()
    }

    fun getAmphibiansDataList() {
        viewModelScope.launch {
            amphibianUiState = try {
                AmphibianUiState.Success(listOf())
            } catch (e: IOException){
                AmphibianUiState.Error
            }
        }
    }
}