package com.example.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly

                    ) {
                        Section(
                            title = stringResource(R.string.text_composable),
                            body = stringResource(R.string.text_compose_body),
                            color = 0xFFEADDFF
                        )
                        Section(
                            title = stringResource(R.string.image_composable),
                            body = stringResource(R.string.image_composable_body),
                            color = 0xFFD0BCFF
                        )
                    }
                    Row() {
                        Section(
                            title = stringResource(R.string.text_composable),
                            body = stringResource(R.string.text_compose_body),
                            color = 0xFFEADDFF
                        )
                        Section(
                            title = stringResource(R.string.image_composable),
                            body = stringResource(R.string.image_composable_body),
                            color = 0xFFD0BCFF
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Section(title: String, body: String, color:Long){
    Surface(
        color = Color(color)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
        ){
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    bottom = 16.dp
                )
            )
            Text(
                text = body,
                textAlign = TextAlign.Justify
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeBasicsTheme {
        Column (verticalArrangement = Arrangement.SpaceEvenly){
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Section(
                    title = stringResource(R.string.text_composable),
                    body = stringResource(R.string.text_compose_body),
                    color = 0xFFEADDFF
                )
                Section(
                    title = stringResource(R.string.image_composable),
                    body = stringResource(R.string.image_composable_body),
                    color = 0xFFD0BCFF
                )
            }
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Section(
                    title = stringResource(R.string.text_composable),
                    body = stringResource(R.string.text_compose_body),
                    color = 0xFFB69DF8
                )
                Section(
                    title = stringResource(R.string.image_composable),
                    body = stringResource(R.string.image_composable_body),
                    color = 0xFFF6EDFF
                )
            }
        }

    }
}