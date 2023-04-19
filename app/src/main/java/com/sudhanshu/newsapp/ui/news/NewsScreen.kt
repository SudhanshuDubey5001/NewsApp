package com.sudhanshu.newsapp.ui.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sudhanshu.newsapp.util.UiEvent
import com.sudhanshu.newsapp.util.Util
import kotlinx.coroutines.flow.collectLatest
import com.sudhanshu.newsapp.R

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

    /**-------------------News screen UI------------------------**/
    Scaffold(
        contentColor = Color.White
    ) {
        Column {

            // Top app bar-------->
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
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
            ) {
                //Image-------------------->
                AsyncImage(
                    model = viewModel.newsObj?.media,
                    contentDescription = "news related image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                //news details ----------------->
                //title ---------------------->
                val padding = 12.dp
                val bottomPadding = 9.dp
                Text(
                    text = viewModel.newsObj?.title!!,
                    fontSize = 27.sp,
                    fontFamily = FontFamily(Font(R.font.domine_regular)),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(padding, padding, padding, bottomPadding),
                    lineHeight = 32.sp
                )
                Text(
                    text = "By " + viewModel.newsObj.author,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.abel_regular)),
                    color = Color.DarkGray,
                    modifier = Modifier.padding(start = padding, 0.dp, padding, bottomPadding)
                )
                Row {
                    Text(
                        text = viewModel.newsObj.published_date + " | ",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(padding, 0.dp, 0.dp, bottomPadding)
                    )
                    Text(
                        text = Util.getCountryName(viewModel.newsObj.country),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(0.dp, 0.dp, padding, bottomPadding)
                    )
                }
                viewModel.newsObj.summary?.let { it1 ->
                    Text(
                        text = it1,
                        fontSize = 19.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(padding, 0.dp, padding, 0.dp)
                    )
                }
            }

        }

    }
}


