package com.example.bookshelf.ui.Screens

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
import com.example.bookshelf.BookShelfPhotosApplication
import com.example.bookshelf.data.BookShelfPhotosRepository
import com.example.bookshelf.network.BookData
import com.example.bookshelf.network.Items
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BookShelfUiState{
    data class Success(val books: Items): BookShelfUiState
    object Error : BookShelfUiState
    object Loading: BookShelfUiState
}

class BookShelfViewModel(private val bookShelfPhotosRepository: BookShelfPhotosRepository) : ViewModel() {
    var bookShelfUiState: BookShelfUiState by mutableStateOf(BookShelfUiState.Loading)
        private set

    fun getBooksPhotos() {
        viewModelScope.launch{
            bookShelfUiState = try {
               BookShelfUiState.Success(bookShelfPhotosRepository.getBooksPhotos())
            }catch (e:IOException){
                BookShelfUiState.Error
            }
        }
    }

    init {
        getBooksPhotos()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookShelfPhotosApplication)
                val bookShelfPhotosRepository = application.container.bookShelfPhotosRepository
                BookShelfViewModel(bookShelfPhotosRepository=bookShelfPhotosRepository)
            }
        }
    }
}