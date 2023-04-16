package com.sudhanshu.newsapp.ui.newsfeed

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.sudhanshu.newsapp.R
import com.sudhanshu.newsapp.data.repository.News

@Composable
fun NewsFeeditem(
    news: newsTest,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column() {
            AsyncImage(
                model = news.url,
                contentDescription = "media image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.imageplaceholder)
            )
            Row (modifier = Modifier.padding(12.dp)){
                Text(
                    text = news.age + " | ",
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.inter_regular))
                )

                Text(
                    text = "Africa",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontFamily = FontFamily(Font(R.font.inter_regular))
                )
            }
            Text(
                modifier = Modifier.padding(start = 12.dp, top = 0.dp, end = 12.dp, bottom = 12.dp),
                text = news.content,
                fontSize = 20.sp,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.abel_regular)),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(7.dp))
        }
    }
}
