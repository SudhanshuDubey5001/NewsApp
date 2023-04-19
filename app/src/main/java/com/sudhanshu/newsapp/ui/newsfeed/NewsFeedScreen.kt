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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sudhanshu.newsapp.R
import com.sudhanshu.newsapp.data.repository.News
import com.sudhanshu.newsapp.util.UiEvent
import com.sudhanshu.newsapp.util.Util
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsFeedScreen(
    onNavigate: (UiEvent.navigate) -> Unit,
    drawerState: DrawerState,
    viewModel: NewsFeedViewModel = hiltViewModel()
) {

    //trial run--->
//    val newsTestList = listOf(
//        newsTest(
//            "1",
//            "2",
//            "3",
//            "4",
//            "", "", ""
//        ),
//        newsTest(
//            "You will achieve success if you keep your head down and keep moving",
//            "A French court has cleared Air France and Airbus of charges of involuntary manslaughter over a deadly crash in June 2009 which killed all 228 people on board.\n" +
//                    "The Airbus A330 operated by Air France stalled during a storm and plunged into the Atlantic Ocean.\n" +
//                    "The court said even if errors had been committed, a causal link between them and the crash could not be proved.\n" +
//                    "Families of the victims reacted angrily to the acquittal.\n" +
//                    "They appeared stunned when the verdict was read out at the end of the lengthy public trial.\n" +
//                    "Danièle Lamy, the president of the association which represents the victims, said the families were \"disgusted\" that their long fight for justice had come to nothing.\n" +
//                    "\"All that remains of these 14 years of waiting is despair, dismay and anger,\" Ms Lamy said.\n" +
//                    "Claire Durousseau, whose niece died in the crash, said the verdict was a severe blow to those left behind: \"Our lost ones have died a second time. I feel sick.\"\n" +
//                    "It was the first trial for corporate involuntary manslaughter to be held in France.\n" +
//                    "Air France and Airbus had always denied the charges, for which they were facing a maximum fine of €225,000 (£200,000; \$247,000).\n",
//            "https://us.123rf.com/450wm/arthonmeekodong/arthonmeekodong2011/arthonmeekodong201100103/159303909-hand-holding-trees-on-blurred-green-nature-background-replacement-tree-planting-idea-deforestation.jpg?ver=6",
//            "2h ago",
//            "newstest item 2",
//            "Sudhanshu Dubey",
//            "United Kingdom"
//        ),
//    )

    val snackbarHostState = remember { SnackbarHostState() }

    //observing the changes---->
    val newsFeed = viewModel.newsList.collectAsState()

    LaunchedEffect(key1 = true) {

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
                        when(it.action){
                        }
                    }
                }
                else -> {}
            }
        }
    }


    val scope = rememberCoroutineScope()

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
//                        viewModel.onNewsFeedEvent(NewsFeedEvents.OnSearchNewsClick)
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
        }
    }
}