package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {

                   IconPlusText(modifier= Modifier
                       .background(Color(0xffD2E8D4))
                       .fillMaxSize())

            }
        }
    }
}

@Composable
fun IconPlusText(modifier: Modifier) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            AndroidImage()
            NameText()
            UserTitle()

        }
        Column(
            modifier=Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            ContactColumn()
            Spacer(modifier = Modifier.padding(bottom = 72.dp))
        }
    }


}
@Composable
fun AndroidImage(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(120.dp)
            .width(120.dp)
            .background(Color(0xff073402))
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = stringResource(id = R.string.profile_icon),

        )
    }
}

@Composable
fun NameText(){
    Text(
        text = stringResource(id = R.string.user_name),
        fontWeight = FontWeight.W300,
        fontSize = 44.sp
    )
}
@Composable
fun UserTitle(){
    Text(
        text = stringResource(id = R.string.user_title),
        fontWeight = FontWeight.Bold,
        fontSize = 16
            .sp
    )
}
@Composable
fun UserContact(){
    Row (verticalAlignment = Alignment.CenterVertically){
        Icon(
            Icons.Filled.Call ,
            contentDescription = stringResource(R.string.contact_icon),
            tint = Color(0xff006d3b)
        )
        Spacer(modifier = Modifier.padding(end = 30.dp))
        Text(text = stringResource(id = R.string.phone_number))
    }
}
@Composable
fun ShareContact(){
    Row (verticalAlignment = Alignment.CenterVertically){
        Icon(
            Icons.Filled.Share ,
            contentDescription = stringResource(R.string.contact_icon),
            tint = Color(0xff006d3b)
        )
        Spacer(modifier = Modifier.padding(end = 30.dp))
        Text(text = stringResource(id = R.string.website_info))
    }
}
@Composable
fun UserEmail(){
    Row (verticalAlignment = Alignment.CenterVertically){
        Icon(
            Icons.Filled.Email ,
            contentDescription = stringResource(R.string.contact_icon),
            tint = Color(0xff006d3b)
        )
        Spacer(modifier = Modifier.padding(end = 30.dp))
        Text(text = stringResource(id = R.string.email_info))
    }
}
@Composable
fun ContactColumn(){
    Column (verticalArrangement = Arrangement.Center){
        UserContact()
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        ShareContact()
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        UserEmail()
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        IconPlusText(modifier= Modifier
            .background(Color(0xffD2E8D4))
            .fillMaxSize())
    }
}