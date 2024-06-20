package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp(){
    LemonadeAppImageAndText(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Composable
fun LemonadeAppImageAndText(modifier: Modifier =Modifier){
    //this code generates the random number of taps
    val numberOfSqueeze=(2..4).random()
    //counts the number of taps with the second screen on focus
    var numberOfTaps: Int by remember {
        mutableStateOf(0)
    }
    //this value is used to transition the images and the text underneath
    var imageInt: Int by remember {
        mutableStateOf(1)
    }
    val imageSelected = when(imageInt){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val textSelected = when(imageInt){
        1 -> R.string.tap_tree
        2 -> R.string.keep_tapping
        3 -> R.string.tap_lemonade
        else -> R.string.empty_glass
    }
    val imageDescriptionSelected = when(imageInt){
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.lemonade
        else -> R.string.glass_empty
    }

    Column {
        Surface(
            Modifier
                .height(80.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            color = colorResource(R.color.app_bar)


        ){
            Box (contentAlignment = Alignment.BottomCenter){
                Text(
                    text = "Lemonade",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = {
                          when(imageInt){
                            1->imageInt++
                            2->numberOfTaps++
                            3->imageInt++
                            4->imageInt=1
                          }
                    if (imageInt==2){
                        if(numberOfTaps==numberOfSqueeze){
                            imageInt++
                            numberOfTaps=0
                        }
                    }

                },
                shape = RoundedCornerShape(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.light_green))
            ) {
                Image(
                    painter = painterResource(
                        imageSelected
                    ),
                    contentDescription = stringResource(imageDescriptionSelected),
                )
            }

            Spacer(modifier = Modifier.padding(bottom = 16.dp))

            Text(text = stringResource(textSelected), fontSize = 18.sp)

        }
    }
    }


