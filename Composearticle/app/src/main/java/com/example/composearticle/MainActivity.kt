package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            ComposeArticleTheme {

                Column() {
                    ShowImage()
                    BodyText(
                        title = stringResource(R.string.title),
                        heading = stringResource(R.string.heading),
                        body = stringResource(R.string.body),
                        modifier = Modifier.padding(16.dp)
                        )

                }

            }
        }
    }
}

//this composable function is to display an image on the app
@Composable
fun ShowImage(){
    Image(
        painter = painterResource(id = R.drawable.bg_compose_background),
        contentDescription = stringResource(R.string.header_image_with_navigation_icon),
        contentScale = ContentScale.FillWidth
    )
}


@Composable
fun BodyText(title: String, heading: String, body: String ,modifier: Modifier=Modifier){
    Column {
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = modifier
        )
        Text(
            text = heading,
            fontSize = 16.sp,
            modifier = modifier,
            textAlign = TextAlign.Justify

        )
        Text(
            text = body,
            fontSize = 16.sp,
            modifier = modifier,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeArticleTheme {
        Column {
            ShowImage()
            BodyText(title = stringResource(id = R.string.title), heading = stringResource(id = R.string.heading) , body = stringResource(
                id = R.string.body
            ), modifier = Modifier.padding(16.dp))
        }
    }
}