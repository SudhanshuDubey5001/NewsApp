package com.sudhanshu.newsapp.ui.newsfeed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sudhanshu.newsapp.data.repository.News
import com.sudhanshu.newsapp.util.UiEvent
import com.sudhanshu.newsapp.util.Util
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


data class newsTest(
    val content: String,
    val url: String,
    val age: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsFeedScreen(
    onNavigate: (UiEvent.navigate) -> Unit,
    viewModel: NewsFeedViewModel = hiltViewModel()
) {
    //trial run--->
    val newsTestList = listOf(
        newsTest("You got your wife pregnant you son of a bitch",
        "https://thumbs.dreamstime.com/b/environment-earth-day-hands-trees-growing-seedlings-bokeh-green-background-female-hand-holding-tree-nature-field-gra-130247647.jpg",
        "1h ago"),
        newsTest("You will achieve success if you keep your head down and keep moving",
        "https://us.123rf.com/450wm/arthonmeekodong/arthonmeekodong2011/arthonmeekodong201100103/159303909-hand-holding-trees-on-blurred-green-nature-background-replacement-tree-planting-idea-deforestation.jpg?ver=6",
        "2h ago"),
    )


    val snackbarHostState = remember { SnackbarHostState() }

    //observing the changes---->
    val newsFeed = viewModel.newsList.collectAsState()

    LaunchedEffect(key1 = true) {

        viewModel.uiEvent.collectLatest {
            when (it) {
                is UiEvent.navigate -> {
                    onNavigate(it)
                }
                UiEvent.navigationDrawer -> {

                }
                UiEvent.refreshNews -> {
                    viewModel.getLatestNews()
                }

                is UiEvent.showSnackbar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = it.content,
                        actionLabel = it.action,
                        duration = SnackbarDuration.Short
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.getLatestNews()
                    }
                }
                else -> {}
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column {

            //app bar--------------------------->
            CenterAlignedTopAppBar(
                title = { Text(text = "NewsApp") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "back arrow",
                        tint = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        viewModel.onNewsFeedEvent(NewsFeedEvents.OnSearchNewsClick)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search icon",
                            tint = Color.White
                        )
                    }
                }
            )

            //news feed---------------------->

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(newsTestList) {
                    NewsFeeditem(
                        news = it,
                        modifier = Modifier.padding(10.dp))
//                        modifier = Modifier.clickable {
//                            viewModel.onNewsFeedEvent(NewsFeedEvents.OnNewsClick(it))
//                        })
                    }
            }
        }
    }
}

@Preview
@Composable
fun PreviewView(){
    NewsFeedScreen(onNavigate = { UiEvent.navigate ("")})
}