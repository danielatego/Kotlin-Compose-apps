package com.example.courses

//import androidx.compose.foundation.lazy.grid.items
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.topic.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTheme {
                    TopicsApp()
                }
            }
        }
    }


@Preview
@Composable
private fun TopicItemPreview(){
    TopicGridItem(Topic(R.string.fashion,23, R.drawable.fashion))
}

@Composable
fun TopicsApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateEndPadding(layoutDirection)
            )
    ) {
        TopicGrids(DataSource().LoadTopics())
    }
}

@Composable
fun TopicGridItem(
    topic: Topic,
    modifier: Modifier = Modifier
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = colorResource(R.color.item_background))
            .fillMaxWidth()
    ){
        Image(
            painterResource(topic.categoryImage),
            null,
            modifier = Modifier
                .height(68.dp)
                .width(68.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
            )
        Column (modifier = Modifier.padding(top = 16.dp, start = 16.dp,end =16.dp)){
            Text(text = stringResource(topic.categoryName),style =MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.padding(bottom = 8.dp))
            Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start){
                Image(painterResource(R.drawable.ic_grain), null, colorFilter = ColorFilter.tint(Color.Black) )
                Spacer(modifier = Modifier.padding(end = 8.dp))
                Text("${topic.numberOfCourses}",style =MaterialTheme.typography.labelMedium)
            }
        }
    }
}
@Composable
fun TopicGrids(topicsList: List<Topic>,modifier: Modifier= Modifier){
    LazyVerticalGrid(GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(topicsList.size) { index->
            Box (){
                TopicGridItem(topicsList[index])
            }
        }
    }
}
