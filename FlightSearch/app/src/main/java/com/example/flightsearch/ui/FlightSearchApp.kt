package com.example.flightsearch.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.flightsearch.R
import com.example.flightsearch.ui.Screens.FlightSearchScreen

@Composable
fun FlightSearchApp(
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) {
        FlightSearchScreen(modifier = Modifier.padding(it))
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