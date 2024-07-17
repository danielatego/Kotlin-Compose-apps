package com.example.bookshelf.ui.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.network.BookData
import com.example.bookshelf.network.Items

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    retryAction: () -> Unit,
    bookShelfUiState: BookShelfUiState,
    paddingValues: PaddingValues
){
    when(bookShelfUiState){
        is BookShelfUiState.Success -> BookGrid(booksList = bookShelfUiState.books, paddingValues = paddingValues,modifier)
        is BookShelfUiState.Error -> Error(paddingValues = paddingValues, retryAction = retryAction,modifier)
        is BookShelfUiState.Loading -> LoadingScreen(modifier)

    }

}
@Composable
fun BookItem(bookData: BookData,modifier: Modifier= Modifier){
    var imgSrc = bookData.volumeInfo.imageLinks?.thumbnail?:"http://books.google.com/books/content?id=J9G50L3c14QC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    imgSrc = imgSrc.replace("http","https")
    Log.d("html",imgSrc)
    AsyncImage(
       model = ImageRequest.Builder(context = LocalContext.current)
           .crossfade(true)
           .data(imgSrc)
           .build()
       ,
       error = painterResource(R.drawable.ic_connection_error),
       placeholder = painterResource(R.drawable.loading_img),
       contentScale = ContentScale.Crop,
       contentDescription=null,
       modifier = modifier.fillMaxWidth()
   )
}
@Composable
fun BookGrid(
    booksList:Items,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier.padding(paddingValues
        ),
        columns = GridCells.Adaptive(minSize = 160.dp)) {
       items(items = booksList.items, key = {item->item.id}){
           BookItem(bookData = it,
               modifier
                   .padding(2.dp)
                   .fillMaxWidth())
                   //.aspectRatio(.8f,matchHeightConstraintsFirst = true))
       }
    }
}

@Composable
fun Error(
    paddingValues: PaddingValues,
    retryAction: ()-> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.error),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = "",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onError)
        )
        Text(
            text = stringResource(R.string.failed_to_load),
            color = MaterialTheme.colorScheme.onError
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = retryAction,
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.errorContainer)
        ) {
            Text(stringResource(R.string.retry),
                color = MaterialTheme.colorScheme.onErrorContainer
            )
        }

    }

}
@Composable
fun LoadingScreen(
    modifier: Modifier= Modifier
){
    Column(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
        , verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary)
        )
    }
}
