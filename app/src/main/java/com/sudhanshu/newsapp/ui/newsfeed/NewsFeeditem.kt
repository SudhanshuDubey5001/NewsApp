package com.sudhanshu.newsapp.ui.newsfeed

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sudhanshu.newsapp.R
import com.sudhanshu.newsapp.data.repository.News
import com.sudhanshu.newsapp.util.Util
import java.util.*

@Composable
fun NewsFeeditem(
    news: News,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column() {
            news.media.let {
                AsyncImage(
                    model = it,
                    contentDescription = "media image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.imageplaceholder)
                )
            }
            Row(modifier = Modifier.padding(12.dp)) {
                news.published_date.let {
                    Text(
                        text = Util.getTimeAgo(it!!) + " | ",
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.inter_regular))
                    )
                }
                Text(
                    text = Util.getCountryName(news.country),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontFamily = FontFamily(Font(R.font.inter_regular))
                )
            }
            news.title?.let {
                Text(
                    modifier = Modifier.padding(
                        start = 12.dp,
                        top = 0.dp,
                        end = 12.dp,
                        bottom = 12.dp
                    ),
                    text = it,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.abel_regular)),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
        }
    }
}