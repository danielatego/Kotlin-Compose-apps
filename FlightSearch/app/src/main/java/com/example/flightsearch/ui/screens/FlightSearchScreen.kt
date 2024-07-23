package com.example.flightsearch.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.flightsearch.R
import com.example.flightsearch.data.Favorite


@Composable
fun FlightSearchScreen(
    uiState: SearchUiState,
    addFavorite: (Favorite)->Unit,
    onSuggestionSelection:(String,String) -> Unit,
    modifier: Modifier=Modifier,
    onValueChange: (SearchUiState) -> Unit,
    flightSearchUiState: FlightSearchUiState
) {
    Column(modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))) {
        TextField(onValueChange = onValueChange, uiState = uiState)
        Spacer(modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_semi_large)))
        Column(modifier = Modifier){
            Text(text = "Flights from NBO",
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_semi_large)),
                style = MaterialTheme.typography.titleMedium
            )
            when(flightSearchUiState){
                is FlightSearchUiState.Searching ->ListOfSuggestions(
                    searchSuggestionsList = flightSearchUiState.suggestions,
                    suggestionOnClick =onSuggestionSelection)
                is FlightSearchUiState.SearchResults -> ListOfFlights(listOfFlights = flightSearchUiState.SearchList,addFavorite=addFavorite)
                is FlightSearchUiState.Favourites -> null
            }

        }
    }

}

@Composable
fun TextField(
    uiState: SearchUiState,
    onValueChange:(SearchUiState)->Unit,
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = uiState.editTextEntry,
        onValueChange = {
                       onValueChange(uiState.copy(editTextEntry = it))
        },
        leadingIcon = {
            TextFieldIcons(
                vectorResource = R.drawable.search,
                fontResource = R.string.search_icon
            )
        },
        trailingIcon = {
            TextFieldIcons(
                vectorResource = R.drawable.voice,
                fontResource = R.string.voice_icon
            )
        },
        shape = CircleShape,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
        )
}

@Composable
fun TextFieldIcons(
    modifier:Modifier = Modifier,
    vectorResource: Int,
    fontResource: Int
){
    Image(
        imageVector = ImageVector
            .vectorResource(vectorResource),
        contentDescription = stringResource(fontResource),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer)
        )
}

@Composable
fun SearchSuggestionItem(
    iataCode: String,
    airport: String,
    modifier: Modifier =Modifier,
    suggestionOnClick:()-> Unit
){
    Box(modifier = modifier
        .clickable(onClick = suggestionOnClick)
        .defaultMinSize(minHeight = 48.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen.padding_very_small))
        ) {
            Text(
                text = iataCode,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(end = dimensionResource(R.dimen.small_padding))
            )
            Text(
                text = airport,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
fun SearchResultItem(
    flightTrip: FlightTrip,
    modifier: Modifier = Modifier,
    onSearchClick: ()->Unit ={}
){
    Card(
        shape = RoundedCornerShape(
            topEnd = dimensionResource(R.dimen.padding_large),
            bottomStart = dimensionResource(R.dimen.padding_large)
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSearchClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .fillMaxWidth()

        ) {
            Column (modifier = Modifier.weight(5f)){
              SearchResultTextColumn(flightTrip)
          }  
            Image(
                modifier=Modifier.weight(1f),
                imageVector = ImageVector.vectorResource(R.drawable.star),
                contentDescription = stringResource(
                R.string.favourite_icon
            ))
        }
    }
}

@Composable
fun SearchResultTextColumn(
    flightTrip: FlightTrip,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_very_small))
    ) {
        Text(text = stringResource(R.string.depart),
            style = MaterialTheme.typography.labelMedium)
        Row {
            Text(
                text = flightTrip.departIata,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(end = dimensionResource(R.dimen.small_padding))
            )
            Text(
                text = flightTrip.departName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        Text(text = stringResource(R.string.arrive),
            style = MaterialTheme.typography.labelMedium)
        Row {
            Text(
                text = flightTrip.arriveIata,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(end = dimensionResource(R.dimen.small_padding))
            )
            Text(
                text = flightTrip.arriveName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
fun ListOfSuggestions(
    suggestionOnClick: (String,String)-> Unit,
    searchSuggestionsList: List<SearchSuggestions>
){
    LazyColumn(

        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_very_small))
    ) {
       items(items = searchSuggestionsList, key = {suggestion -> suggestion.iataCode}){
           SearchSuggestionItem(iataCode = it.iataCode, airport = it.airportName, suggestionOnClick = {suggestionOnClick(it.iataCode,it.airportName)})
       }
    }
}
@Composable
fun ListOfFlights(
    listOfFlights:List<FlightTrip>,
    addFavorite: (Favorite) -> Unit,
    modifier: Modifier=Modifier
){

    LazyColumn(
        modifier =modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {

        items(items = listOfFlights){
            SearchResultItem(
                onSearchClick = {addFavorite(Favorite(departure_code = it.departIata, destination_code = it.arriveIata))},
                flightTrip = FlightTrip(
                    departIata = it.departIata,
                    departName = it.departName,
                    arriveIata = it.arriveIata,
                    arriveName = it.arriveName)
            )
        }
    }
}