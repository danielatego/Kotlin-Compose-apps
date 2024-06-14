package com.example.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicsTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(Modifier.weight(1f)) {
                        Surface (
                            color = Color(0xFFEADDFF),
                            modifier=Modifier.weight(1f)
                        ){
                            TextColumn(title = stringResource(id = R.string.text_composable),
                                body = stringResource(id = R.string.text_compose_body) )
                        }
                        Surface(
                            color = Color(0xFFD0BCFF),
                            modifier = Modifier.weight(1f)
                        ){
                            TextColumn(title = stringResource(id = R.string.image_composable),
                                body = stringResource(id = R.string.image_composable_body) )
                        }
                    }
                    Row(modifier = Modifier.weight(1f)) {
                        Surface (
                            color = Color(0xFFB69DF8),
                            modifier=Modifier.weight(1f)
                        ){
                            TextColumn(title = stringResource(id = R.string.row_composable),
                                body = stringResource(id = R.string.row_composable_body) )
                        }
                        Surface(
                            color = Color(0xFFF6EDFF),
                            modifier = Modifier.weight(1f)
                        ){
                            TextColumn(title = stringResource(id = R.string.column_composable),
                                body = stringResource(id = R.string.column_composable_body) )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun TextColumn(title:String,body:String){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = body,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(16.dp)
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeBasicsTheme {

        }

        }


