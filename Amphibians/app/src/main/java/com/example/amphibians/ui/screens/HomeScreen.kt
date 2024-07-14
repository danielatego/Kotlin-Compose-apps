package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.amphibians.R
import com.example.amphibians.network.AmphibianData

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    AmphibianImageCard(
        contentPadding,
        amphibianData = AmphibianData(
        stringResource(R.string.test_amphibian_name),
        stringResource(R.string.test_amphibian_type),
        stringResource(R.string.test_amphibian_description),
        stringResource(R.string.test_amphibian_name)))
}

@Composable
fun AmphibianImageCard(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    amphibianData: AmphibianData
){
    Card(
        modifier.padding(paddingValues),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer
//        )

    ) {
        Row(modifier = Modifier.padding(
            dimensionResource(R.dimen.padding_medium)
        )) {
            Text(
                text = amphibianData.name,
                //color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = " (${amphibianData.type})",
                //color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Image(
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading_state),
            modifier = Modifier.fillMaxWidth().height(300.dp),

            contentScale = ContentScale.Crop
            )
        Text(
            text = amphibianData.description,
            //color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}
