package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansDataApplication
import com.example.amphibians.data.AmphibianDataRepository
import com.example.amphibians.network.AmphibianData
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibianUiState{

    data class Success(val amphibiansData: List<AmphibianData>): AmphibianUiState
    object Error: AmphibianUiState
    object Loading: AmphibianUiState
}

class AmphibiansViewModel(private val amphibiansDataRepository: AmphibianDataRepository) :ViewModel(){

    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)

    init {
        getAmphibiansDataList()
    }

    fun getAmphibiansDataList() {
        viewModelScope.launch {
            amphibianUiState = try {
                AmphibianUiState.Success(amphibiansData = amphibiansDataRepository.getAmphibiansData())
            } catch (e: IOException){
                AmphibianUiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansDataApplication)
                val amphibiansDataRepository = application.container.amphibianDataRepository
                AmphibiansViewModel(amphibiansDataRepository = amphibiansDataRepository)

            }
        }
    }
}