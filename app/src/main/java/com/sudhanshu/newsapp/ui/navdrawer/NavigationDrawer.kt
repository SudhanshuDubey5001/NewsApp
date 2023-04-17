package com.sudhanshu.newsapp.ui.navdrawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sudhanshu.newsapp.ui.news.NewsScreen
import com.sudhanshu.newsapp.ui.newsfeed.NewsFeedScreen
import com.sudhanshu.newsapp.util.Routes
import com.sudhanshu.newsapp.util.Util

@Composable
fun BuildNavigationDrawer(
    drawerState: DrawerState,
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavDrawerContents()
        }) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.NEWS_FEED
        ) {
            composable(Routes.NEWS_FEED) {
                NewsFeedScreen(
                    onNavigate = {
                        navController.navigate(it.route)
                    },
                    drawerState = drawerState
                )
            }
            composable(
                route = Routes.NEWS + "?newsJSON={${Util.NEWS_ID}}",
                arguments = listOf(
                    navArgument(name = Util.NEWS_ID) {
                        type = NavType.StringType
                        defaultValue = "-1"
                    }
                )
            ) {
                NewsScreen(
                    popBackStack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}


@Composable
fun NavigationDrawerHeader() {
    Box(
        modifier = Modifier
            .padding(vertical = 60.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Topics")
    }
}

@Composable
fun NavigationDrawerBody(
    modifier: Modifier = Modifier,
    items: List<NavDrawerItems>,
    itemClick: (NavDrawerItems) -> Unit
) {
    LazyColumn(modifier) {
        items(items) {
            Row(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable {
                    itemClick(it)
                }) {
                Icon(
                    imageVector = it.icon,
                    contentDescription = it.description
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = it.title, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun NavDrawerContents(){
    NavigationDrawerHeader()
    NavigationDrawerBody(
        items = listOf(
            NavDrawerItems(
                title = "News",
                icon = Icons.Default.Add,
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Sports",
                icon = Icons.Default.Add,
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Technology",
                icon = Icons.Default.Add,
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Finance",
                icon = Icons.Default.Add,
                description = "Show news"
            )
        ),
        itemClick = {
            // TODO:
        })
}
