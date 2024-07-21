package com.example.flightsearch.ui.Screens

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


@Composable
fun FlightSearchScreen(
    modifier: Modifier=Modifier
) {
    Column(modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))) {
        TextField()
        Spacer(modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_semi_large)))
        Column(modifier = Modifier){
            Text(text = "Flights from NBO",
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_semi_large)),
                style = MaterialTheme.typography.titleMedium
            )

            ListOfFlights()
        }
    }

}

@Composable
fun TextField(
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = "",
        onValueChange = {},
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
                text = "NBO",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(end = dimensionResource(R.dimen.small_padding))
            )
            Text(
                text = "Nairobi International Airport",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
fun SearchResultItem(
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
            Column {
              SearchResultTextColumn()
          }  
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.star),
                contentDescription = stringResource(
                R.string.favourite_icon
            ))
        }
    }
}

@Composable
fun SearchResultTextColumn(
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
                text = "NBO",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(end = dimensionResource(R.dimen.small_padding))
            )
            Text(
                text = "Nairobi International Airport",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        Text(text = stringResource(R.string.arrive),
            style = MaterialTheme.typography.labelMedium)
        Row {
            Text(
                text = "NBO",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(end = dimensionResource(R.dimen.small_padding))
            )
            Text(
                text = "Nairobi International Airport",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
fun ListOfSuggestions(){

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_very_small))
    ) {
       items(count =20){
           SearchSuggestionItem(suggestionOnClick = {})
       }
    }
}
@Composable
fun ListOfFlights(modifier: Modifier=Modifier){

    LazyColumn(
        modifier =modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {

        items(count =20){
            SearchResultItem()
        }
    }
}