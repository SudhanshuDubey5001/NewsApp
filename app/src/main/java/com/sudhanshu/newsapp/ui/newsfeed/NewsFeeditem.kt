package com.sudhanshu.newsapp.ui.newsfeed

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.sudhanshu.newsapp.R
import com.sudhanshu.newsapp.data.repository.News

@Composable
fun NewsFeeditem(
    news: News,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        AsyncImage(
            model = news.media,
            contentDescription = "media image",
            modifier = Modifier.fillMaxSize(),
            error = painterResource(id = R.drawable.imageplaceholder)
        )
        Text(
            text = news.excerpt,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(7.dp))
    }
}
