package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.network.AmphibianData

@Composable
fun HomeScreen(
    amphibianUiState: AmphibianUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    when(amphibianUiState){
        is AmphibianUiState.Loading -> LoadingScreen(paddingValues = contentPadding)
        is AmphibianUiState.Error -> ErrorScreen(retryAction = retryAction)
        is AmphibianUiState.Success -> AmphibiansList(
            contentPadding = contentPadding,
            modifier = modifier,
            amphibianListData = amphibianUiState.amphibiansData
        )
    }
}

@Composable
fun AmphibiansList(
    contentPadding: PaddingValues,
    modifier: Modifier,
    amphibianListData:List<AmphibianData>
){
    LazyColumn(
        contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
            .padding(contentPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        items(items = amphibianListData, key = {amphibianData -> amphibianData.name}){
            amphibianData -> AmphibianImageCard(amphibianData = amphibianData)
        }
    }
}
@Composable
fun AmphibianImageCard(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
    amphibianData: AmphibianData
){
    Card(
        modifier.padding(paddingValues)
    ) {
        Row(modifier = Modifier.padding(
            dimensionResource(R.dimen.padding_medium)
        )) {
            Text(
                text = amphibianData.name+ " (${amphibianData.type})",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

        }
        AsyncImage(
            model = ImageRequest.Builder(
                context = LocalContext.current
            ).crossfade(true)
                .data(amphibianData.imgSrc)
                .build(),
            error = painterResource(R.drawable.ic_connection_error),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = amphibianData.name,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth

            )
        Text(
            text = amphibianData.description,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            lineHeight = 24.sp,
            letterSpacing = 1.0.sp,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ){
        Image(
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading_state),
            modifier
                .size(360.dp)
                .padding(paddingValues)
        )
    }
}
@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues= PaddingValues(0.dp),
    retryAction:() -> Unit
){
    Column (
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.error),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onError))
        Text(
            text = stringResource(R.string.internet_connection_error),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onError
        )
        Spacer(modifier = modifier.padding(vertical = dimensionResource(R.dimen.padding_medium)))
        Button(
            onClick = retryAction,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Text(
                stringResource(R.string.retry),
            )
        }
    }
}

@Preview
@Composable
fun AmphibianImageCardPreview(){
    AmphibianImageCard(

        amphibianData = AmphibianData(
            stringResource(R.string.test_amphibian_name),
            stringResource(R.string.test_amphibian_type),
            stringResource(R.string.test_amphibian_description),
            stringResource(R.string.test_amphibian_src)))
}