package com.sudhanshu.newsapp.ui.newsfeed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sudhanshu.newsapp.R
import com.sudhanshu.newsapp.data.repository.News
import com.sudhanshu.newsapp.ui.SearchDialogBox
import com.sudhanshu.newsapp.ui.navdrawer.progressHUD
import com.sudhanshu.newsapp.util.UiEvent
import com.sudhanshu.newsapp.util.Util
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsFeedScreen(
    onNavigate: (UiEvent.navigate) -> Unit,
    drawerState: DrawerState,
    listState: LazyListState,
    viewModel: NewsFeedViewModel = hiltViewModel()
) {

    //observing changes in newsFeed ---->
    val newsFeed = newsList.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val dialogBoxState = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {

        Util.log("Assigning it to local list")

        viewModel.uiEvent.collectLatest {
            when (it) {
                is UiEvent.navigate -> {
                    onNavigate(it)
                }
                is UiEvent.showSnackbar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = it.content,
                        actionLabel = it.action,
                        duration = SnackbarDuration.Short
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        when (it.action) {
                        }
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
                title = {
                    Text(
                        text = Util.APP_TITLE,
                        fontFamily = FontFamily(Font(R.font.abel_regular))
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "nav drawer",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(start = 7.dp, 0.dp, 0.dp, 0.dp)
                            .clickable {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        searchDialogBoxState.value = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search icon",
                            tint = Color.White,
                        )
                    }
                }
            )

            //news feed---------------------->
            Box {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = listState
                ) {
                    Util.log("Lazy list called....")
                    items(newsFeed.value) {
                        NewsFeeditem(
                            news = it,
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    viewModel.onNewsFeedEvent(NewsFeedEvents.OnNewsClick(it))
                                }
                        )
                    }
                }
                progressHUD()
                SearchDialogBox()
            }
        }
    }
}