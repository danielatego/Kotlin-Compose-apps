package com.example.mycity.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycity.R
import com.example.mycity.data.Destination
import com.example.mycity.data.DestinationCategory
import com.example.mycity.data.NavigationItemContent
import com.example.mycity.ui.theme.MyCityTheme
import com.example.mycity.ui.utils.MyCityContentType
import com.example.mycity.ui.utils.MyCityNavigationType

@Composable
fun MyCityHomeApp(
    myCityUiState: MyCityUiState,
    onDestinationPressed:(Destination)->Unit,
    contentType: MyCityContentType,
    navigationType:MyCityNavigationType,
    selectedDestinationCategory: DestinationCategory,
    onTabPressed: (DestinationCategory) -> Unit,
    onBackPressed:() -> Unit,
    modifier: Modifier = Modifier
){
    if(navigationType==MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER){
        MyCityPermanentNavigationDrawer(
            myCityUiState,
            navigationType,
            onDestinationPressed,
        contentType,
            selectedDestinationCategory = selectedDestinationCategory,
            onTabPressed = {onTabPressed(it)})
    }else{
        if (myCityUiState.isShowingHomepage) {
            Row {
                AnimatedVisibility(visible = navigationType == MyCityNavigationType.NAVIGATION_RAIL) {
                    MyCityNavigationRail(
                        selectedDestinationCategory = selectedDestinationCategory,
                        onTabPressed = { onTabPressed(it) }
                    )
                }
                Column {
                    if (contentType == MyCityContentType.LIST_AND_DETAIL) {
                        MyCityDestinationsListAndDetailContent(
                            myCityUiState = myCityUiState,
                            onDestinationPressed = { onDestinationPressed(it) },
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        MyCityDestinationsListOnly(
                            myCityUiState = myCityUiState,
                            onDestinationPressed = { onDestinationPressed(it) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    AnimatedVisibility(visible = navigationType == MyCityNavigationType.BOTTOM_NAVIGATION) {
                        MyCityBottomNavigationBar(
                            selectedDestinationCategory = selectedDestinationCategory,
                            onTabPressed = { onTabPressed(it) }
                        )
                    }
                }
            }
        }else{
            MyCityDestinationDescription(destination = myCityUiState.currentSelectedDestination, onBackPressed = {onBackPressed()})
        }
    }

}

val navigationItemContentList = listOf<NavigationItemContent>(
    NavigationItemContent(
        destinationCategory = DestinationCategory.Accommodation,
        icon = R.drawable.baseline_hotel_24,
        text = R.string.accomodation
    ),
    NavigationItemContent(
        destinationCategory = DestinationCategory.Wildlife,
        icon = R.drawable.pet,
        text = R.string.wildlife
    ),    NavigationItemContent(
        destinationCategory = DestinationCategory.Culture,
        icon = R.drawable.culture,
        text = R.string.culture
    ),    NavigationItemContent(
        destinationCategory = DestinationCategory.Recreation,
        icon = R.drawable.swim,
        text = R.string.recreation
    ),    NavigationItemContent(
        destinationCategory = DestinationCategory.Restaurants,
        icon = R.drawable.food,
        text = R.string.restaurants
    ),
)



@Composable
fun MyCityPermanentNavigationDrawer(
    myCityUiState: MyCityUiState,
    navigationType:MyCityNavigationType,
    onDestinationPressed:(Destination)->Unit,
    contentType: MyCityContentType,
    selectedDestinationCategory: DestinationCategory,
    onTabPressed: (DestinationCategory) -> Unit,
    modifier: Modifier = Modifier
){
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(
                modifier = modifier.width(dimensionResource(R.dimen.permanent_drawer_width))
            ) {
                Column(modifier = modifier) {
                    NavigationDrawerHeader(
                        modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding))
                    )
                    for(navItem in navigationItemContentList){
                        NavigationDrawerItem(
                            label = {
                                    Text(
                                        text = stringResource(navItem.text),
                                        modifier = Modifier.padding(
                                            horizontal = dimensionResource(R.dimen.medium_padding))
                                    )
                            },
                            selected = selectedDestinationCategory==navItem.destinationCategory,
                            onClick = { onTabPressed(navItem.destinationCategory) },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Transparent
                            ),
                            icon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(navItem.icon),
                                    contentDescription = null)
                            }
                            )

                    }
                }
            }
        }
    ) {
        Row {
            AnimatedVisibility(visible = navigationType == MyCityNavigationType.NAVIGATION_RAIL) {
                MyCityNavigationRail(
                    selectedDestinationCategory = selectedDestinationCategory,
                    onTabPressed = { onTabPressed(it) }
                )
            }
            Column {
                if (contentType == MyCityContentType.LIST_AND_DETAIL) {
                    MyCityDestinationsListAndDetailContent(
                        myCityUiState = myCityUiState,
                        onDestinationPressed = { onDestinationPressed(it) }
                    )
                } else {
                    MyCityDestinationsListOnly(
                        myCityUiState = myCityUiState,
                        onDestinationPressed = { onDestinationPressed(it) }
                    )
                }
                AnimatedVisibility(visible = navigationType == MyCityNavigationType.BOTTOM_NAVIGATION) {
                    MyCityBottomNavigationBar(
                        selectedDestinationCategory = selectedDestinationCategory,
                        onTabPressed = { onTabPressed(it) }
                    )
                }
            }
        }
    }
}



@Composable
fun NavigationDrawerHeader(
    modifier: Modifier =Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth() ) {
        Text(
            text = stringResource(R.string.nairobi_default),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.alice_regular)),
            letterSpacing = 1.sp,
        )
        Image(
            painter = painterResource(R.drawable.nairobi_logo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp)
                .fillMaxWidth()
                .clip(CircleShape)

        )
    }
}

@Composable
private fun MyCityBottomNavigationBar(
    selectedDestinationCategory: DestinationCategory,
    onTabPressed: (DestinationCategory) -> Unit,
    modifier:Modifier = Modifier){
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList){
            NavigationBarItem(
                selected = selectedDestinationCategory == navItem.destinationCategory,
                onClick = { onTabPressed(navItem.destinationCategory)},
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(navItem.icon),
                        contentDescription = null
                    )
                })
        }

    }
}

@Composable
private fun MyCityNavigationRail(
    selectedDestinationCategory: DestinationCategory,
    onTabPressed: (DestinationCategory) -> Unit,
    modifier: Modifier = Modifier
){
    NavigationRail(modifier = Modifier) {
        for (navItem in navigationItemContentList){
            NavigationRailItem(
                selected = selectedDestinationCategory == navItem.destinationCategory,
                onClick = { onTabPressed(navItem.destinationCategory)},
                icon = {
                    Icon(
                    imageVector = ImageVector.vectorResource(navItem.icon),
                    contentDescription = null)
                }
            )
        }
    }
}

//@Preview
//@Composable
//fun MyCityPermanentNavigationDrawerPreview(){
//    MyCityTheme {
//        MyCityPermanentNavigationDrawer(
//            selectedDestinationCategory = DestinationCategory.Wildlife,
//            onTabPressed = {})
//    }
//}
@Preview
@Composable
fun MyCityBottomNavigationPreview(){
    MyCityTheme {
        MyCityBottomNavigationBar(
            selectedDestinationCategory = DestinationCategory.Wildlife,
            onTabPressed ={} )
    }
}

@Preview
@Composable
fun MyCityNavigationRailPreview(){
    MyCityTheme {
        MyCityNavigationRail(
            selectedDestinationCategory = DestinationCategory.Wildlife,
            onTabPressed = {})
    }
}