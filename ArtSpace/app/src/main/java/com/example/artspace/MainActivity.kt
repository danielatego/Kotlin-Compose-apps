package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Preview
@Composable
fun ArtSpacePreview(){
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}

@Composable
fun ArtSpaceApp(
    modifier: Modifier = Modifier
        .statusBarsPadding()
        .fillMaxSize()
        .background(colorResource(R.color.back_ground))
){
    var imageSelected by remember {
        mutableStateOf(1)
    }

    val imageSelection =  when(imageSelected){
        1-> R.drawable.baobab
        2-> R.drawable.barcelona
        3-> R.drawable.library
        else-> R.drawable.yellow

    }
    val descriptionSelection =  when(imageSelected){
        1-> R.string.baobab_description
        2-> R.string.barcelona_description
        3-> R.string.library_description
        else-> R.string.yellow_description

    }
    val yearSelection =  when(imageSelected){
        1-> R.string.baobab_date
        2-> R.string.barcelona_date
        3-> R.string.library_date
        else-> R.string.yellow_date

    }
    val takerSelection =  when(imageSelected){
        1-> R.string.baobab_by
        2-> R.string.barcelona_by
        3-> R.string.library_by
        else-> R.string.yellow_by

    }
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier= modifier.padding(24.dp)
    ) {
        Surface(
            shadowElevation = 8.dp,
            modifier = Modifier
                .fillMaxHeight(0.5F)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(imageSelection),
                contentDescription = stringResource(descriptionSelection),
                modifier=Modifier.padding(20.dp)
            )
        }
        Surface(
            color = colorResource(R.color.text_box),
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth()


        ) {
            TextColumn(
                imageDescription = descriptionSelection,
                imageBy = takerSelection,
                imageDate = yearSelection,
                modifier = Modifier.padding(20.dp))

        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            ImageNavigation(buttonText = R.string.previous,{
                when(imageSelected){
                    1,2,3->imageSelected++
                    4->imageSelected=1
                }
            })
            ImageNavigation(buttonText = R.string.next,{
                when(imageSelected){
                  4,3,2->imageSelected--
                  1->imageSelected=4
                }

            })

        }
    }
}

@Composable
fun TextColumn(imageDescription:Int,
               imageBy: Int,
               imageDate:Int,
               modifier: Modifier=Modifier){
    Column(modifier) {
        Text(
            text = stringResource(imageDescription),
            fontSize = 20.sp,
            fontWeight = FontWeight.W300,
            color = colorResource(R.color.gray_text))
        val words = buildAnnotatedString {
            pushStyle(SpanStyle(fontWeight = FontWeight.Bold))

            append(stringResource(imageBy))

            pop()

            append(" (${stringResource(imageDate)})")


            // then style the last added word as red, exclamation mark will be red
            addStyle(SpanStyle(color = colorResource(R.color.gray_text)),5,this.length)

            toAnnotatedString()
        }
        Text(text = words)
    }
}

@Composable
fun ImageNavigation(
    buttonText: Int,
    voidCallBack: ()-> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = voidCallBack ,
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.button_color) ),
        modifier = Modifier.width(120.dp)
    ) {
        Text(text = stringResource(buttonText))
    }
}