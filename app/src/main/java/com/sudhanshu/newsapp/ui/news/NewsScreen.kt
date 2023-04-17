package com.sudhanshu.newsapp.ui.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sudhanshu.newsapp.util.UiEvent
import com.sudhanshu.newsapp.util.Util
import kotlinx.coroutines.flow.collectLatest
import com.sudhanshu.newsapp.R
import com.sudhanshu.newsapp.data.repository.News
import com.sudhanshu.newsapp.data.repository.newsTest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    popBackStack: () -> Unit,
    viewModel: NewsViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {


        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                UiEvent.popBackStack -> {
                    popBackStack()
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        contentColor = Color.White
    ) {
        Column {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = Util.APP_TITLE,
                        fontFamily = FontFamily(Font(R.font.abel_regular))
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back arrow",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(7.dp, 0.dp, 0.dp, 0.dp)
                            .clickable {
                                viewModel.OnNewsEvent(NewsEvent.popBackStack)
                            }
                    )
                }
            )
            Column {
                AsyncImage(
                    model = viewModel.newsFlowObj?.url,
                    contentDescription = "news related iamge",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentScale = ContentScale.Crop
                )
                Box(modifier = Modifier.weight(2f)) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        //title ---------------------->
                        Text(
                            text = viewModel.newsFlowObj?.content!!,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }

    }
}


