package com.example.mycity.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycity.R
import com.example.mycity.data.Destination
import com.example.mycity.data.LocalDestinationsProvider
import com.example.mycity.ui.theme.MyCityTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityDestinationListItem(
    destination: Destination,
    onCardTap: (Destination) -> Unit,
    isSelected: Boolean,
    modifier: Modifier =Modifier
){
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    MaterialTheme.colorScheme.secondaryContainer

        ),
        onClick = {onCardTap(destination)}

    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.medium_padding))
        ){
            Image(
                painter = painterResource(destination.destinationPicture),
                contentDescription = stringResource(destination.name),
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.small_padding))),
                contentScale = ContentScale.Crop

            )
            Column(
                //horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                .padding(start = dimensionResource(R.dimen.medium_padding))

            ) {
                Text(
                    text = stringResource(destination.name),
                    style = MaterialTheme.typography.titleSmall,
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
                        else MaterialTheme.colorScheme.onSecondaryContainer,
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.small_padding) )
                )
                Text(
                    text = stringResource(destination.description),
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
                    else MaterialTheme.colorScheme.onSecondaryContainer,
                    fontSize = 12.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}


@Composable
fun MyCityDestinationsListOnly(
    myCityUiState: MyCityUiState,
    onDestinationPressed: (Destination) -> Unit,
    modifier: Modifier = Modifier
){
    val destinations = myCityUiState.currentCityCategoryDestinations
    LazyColumn(
        modifier = modifier.padding(horizontal = dimensionResource(R.dimen.medium_padding)),
        contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
    ) {
        item {
            MyCityHomePageTopBar(modifier= Modifier)
        }
        items(destinations){
                MyCityDestinationListItem(
                    destination = it,
                    onCardTap = {onDestinationPressed(it)},
                    isSelected = false )
        }
    }

}
@Composable
fun MyCityDestinationsListAndDetailContent(
    myCityUiState: MyCityUiState,
    onDestinationPressed: (Destination) -> Unit,
    modifier:Modifier = Modifier,
    displayUpButton:Boolean =false
){
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        MyCityDestinationsListOnly(
            myCityUiState = myCityUiState,
            onDestinationPressed=onDestinationPressed,
            modifier = Modifier.weight(3f)
        )
        val activity = LocalContext.current as Activity
        MyCityDestinationDescription(
            destination = myCityUiState.currentSelectedDestination,
            onBackPressed = {activity.finish()},
            modifier = Modifier.weight(5f),
            displayUpButton = displayUpButton
        )
    }
}


@Composable
fun MyCityDestinationDescription(
    destination: Destination,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    displayUpButton: Boolean =true
){
    BackHandler {
        onBackPressed()
    }
    Box(modifier = modifier){
        LazyColumn(
            contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            item {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .padding(horizontal = dimensionResource(R.dimen.medium_padding)),
                    contentAlignment = Alignment.Center
                ){
                    if(displayUpButton) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.TopStart
                        ) {
                            IconButton(
                                onClick = onBackPressed,
                                modifier = Modifier.background(
                                    MaterialTheme.colorScheme.surface,
                                    CircleShape
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.back)
                                )
                            }
                        }
                    }
                    Text(text = stringResource(destination.name),
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 24.sp
                        )

                }

                Card(
                    modifier=Modifier.padding(horizontal= dimensionResource(R.dimen.medium_padding)),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.large_padding))
                    ) {
                        Image(
                            painter = painterResource(destination.destinationPicture),
                            contentDescription = stringResource( destination.name),
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = stringResource(destination.description),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 28.sp,
                            letterSpacing = 0.5.sp,
                            modifier= Modifier.padding(top = dimensionResource(R.dimen.large_padding))
                        )
                    }
                }
            }
        }
    }

}


@Composable
fun MyCityHomePageTopBar(
    modifier: Modifier =Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth() ) {
        Text(
            text = stringResource(R.string.nairobi_default),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(R.font.alice_regular)),
            letterSpacing = 1.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
        Image(
            painter = painterResource(R.drawable.nairobi_logo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
                .fillMaxWidth()
                .clip(CircleShape)

        )
    }
}



@Preview
@Composable
fun MyCityHomePageTopBarPreview(){
    MyCityTheme {
        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            MyCityHomePageTopBar()
        }
    }
}


@Preview
@Composable
fun MyCityDestinationDescriptionPreview(){
    MyCityTheme {
        MyCityDestinationDescription(
            onBackPressed = {},
            destination = LocalDestinationsProvider.defaultDestination)
}}





@Preview
@Composable
fun MyCityDestinationItemPreview(){
    MyCityTheme {
        MyCityDestinationListItem(
            destination=LocalDestinationsProvider.defaultDestination,
            isSelected = false,
            onCardTap = { /*TODO*/ }
        )
    }
}