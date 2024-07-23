package com.example.flightsearch.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.R
import com.example.flightsearch.ui.screens.FlightSearchScreen
import com.example.flightsearch.ui.screens.FlightSearchUiState
import com.example.flightsearch.ui.screens.FlightSearchViewModel
import kotlinx.coroutines.launch

@Composable
fun FlightSearchApp(
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) {
        val flightSearchViewModel:FlightSearchViewModel = viewModel(factory = FlightSearchViewModel.Factory)
        val flightSearchState by flightSearchViewModel.flightSearchUiState.collectAsState()
        val editorUiState = flightSearchViewModel.searchUiState
        val coroutine = rememberCoroutineScope()
        FlightSearchScreen(
            modifier = Modifier.padding(it),
            addFavorite= { coroutine.launch {
                flightSearchViewModel.addFavorite(it)
            } },
            onValueChange = flightSearchViewModel::updateSearchUiState,
            onSuggestionSelection = flightSearchViewModel::updateUiStateToShowFlights,
            uiState = editorUiState,
            flightSearchUiState = flightSearchState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(){
   TopAppBar(
       title = {
           Text(
               text = stringResource(id = R.string.app_name)
           )
       },
       colors = TopAppBarDefaults.topAppBarColors(
           containerColor = MaterialTheme.colorScheme.primary,
           titleContentColor = MaterialTheme.colorScheme.onPrimary
       )
   )
}