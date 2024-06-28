package com.example.uplift

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.uplift.module.Day
import com.example.uplift.module.DayInfo
import com.example.uplift.ui.theme.UpliftTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            UpliftTheme(
             dynamicColor = false
            ) {
                UpliftApp()
            }
        }
    }
}

@Preview
@Composable
fun DayItemPreview(){
    UpliftTheme(
        dynamicColor = false,
        darkTheme = false) {
        //DayItem(day = Day(R.string.day_number,R.string.one,R.drawable.thirty),1)
        //DayItemList(daysList = DayInfo().listOfDaysInfo())
        UpliftApp()
    }
}
@Composable
fun UpliftApp(){
    Scaffold(
        topBar = {
            AppBar()
        }
    ) {
    DayItemList(
        daysList = DayInfo().listOfDaysInfo(),
        modifier = Modifier.padding(it))
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(){
    CenterAlignedTopAppBar(title = {
        Text(stringResource(R.string.app_name),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center)
    })
}

@Composable
fun DayItemList(
    daysList: List<Day>,
    modifier: Modifier=Modifier
){
    LazyColumn(
        modifier,
        contentPadding = PaddingValues(
            horizontal = dimensionResource(R.dimen.padding_large),
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        items(daysList.size){
            DayItem(daysList[it],(it+1))
        }
    }
}
@Composable
fun DayItem(
    day: Day,
    dayNumber: Int,
    modifier: Modifier= Modifier
){
    var showMessage: Boolean by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(
        targetValue = if (showMessage) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )
    Surface(onClick = {
        showMessage=!showMessage
    }) {
        Card(
            colors = CardDefaults.cardColors(color),
            modifier = modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )

        ) {
            Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))) {
                Text(
                    stringResource(R.string.day_number, dayNumber),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_large))
                )
                Image(
                    painterResource(day.picture),
                    stringResource(day.title),
                    Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                if (showMessage) {
                    Text(
                        stringResource(day.title),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_large))
                    )
                }
            }
        }
    }
}