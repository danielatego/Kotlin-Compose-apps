package com.example.fictionalheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fictionalheroes.module.Hero
import com.example.fictionalheroes.module.HeroesInformation
import com.example.fictionalheroes.ui.theme.FictionalHeroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            FictionalHeroesTheme {
                HeroApp()
            }
        }
    }
}

@Preview
@Composable
fun HeroItemPreview(){

    FictionalHeroesTheme {
        HeroApp()
    }
}

@Composable
fun HeroApp(){
    Scaffold(
        topBar = {
            HeroTopBar()
        }
    ) {
        HeroesList(
            HeroesInformation().heroesInformationList(),
            modifier = Modifier.padding(it))

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(R.string.app_bar),
                style = MaterialTheme.typography.displayLarge
            )
        }
    )
}

@Composable
fun HeroesList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier){
    LazyColumn(
        modifier,
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_large)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        items(heroes){
            HeroItem(hero = Hero(it.nameRes,it.descriptionRes,it.imageRes))
        }
    }
}

@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
){
   Card(
       modifier = modifier
           .height(dimensionResource(R.dimen.card_height))
           .fillMaxWidth()
           ,
       elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.card_elevation))
   ){
       Row(
           verticalAlignment = Alignment.Top,
           modifier = Modifier
               .fillMaxSize()
               .clip(MaterialTheme.shapes.medium)
               .padding(dimensionResource(R.dimen.padding_large))
       ) {
            Column(modifier= Modifier.weight(1f)) {

                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )

            }
           Spacer(modifier = Modifier
               .padding(end = dimensionResource(R.dimen.padding_large)))
           Image(
               painter = painterResource(hero.imageRes),
               contentDescription = stringResource(
               hero.nameRes),
               modifier = Modifier
                   .size(dimensionResource(R.dimen.box_height))
                   .clip(MaterialTheme.shapes.small)
                   //.weight(1f)
               ,
               contentScale = ContentScale.Crop
           )
       }
   }
}