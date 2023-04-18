package com.sudhanshu.newsapp.ui.navdrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sudhanshu.newsapp.R
import com.sudhanshu.newsapp.ui.NavDrawerItems
import com.sudhanshu.newsapp.ui.news.NewsScreen
import com.sudhanshu.newsapp.ui.newsfeed.NewsFeedScreen
import com.sudhanshu.newsapp.util.Routes
import com.sudhanshu.newsapp.util.Util

@Composable
fun MainCompose() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavDrawerContents()
        },
    ) {
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
fun NavigationDrawerBody(
    items: List<NavDrawerItems>,
    itemClick: (NavDrawerItems) -> Unit
) {
    Column(
        modifier = Modifier
            .requiredWidth(250.dp)
            .fillMaxHeight()
            .background(
                Color.White,
                RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primary
                )
                .padding(vertical = 60.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Topics",
                color = Color.White,
                fontSize = 40.sp,
                fontFamily = FontFamily(Font(R.font.inter_regular))
            )
        }
        LazyColumn(
            modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)
        ) {
            items(items) {
                Row(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clickable {
                        itemClick(it)
                    }) {
                    Icon(
                        painter = it.icon,
                        contentDescription = it.description,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = it.title,
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NavDrawerContents() {
    NavigationDrawerBody(
        items = listOf(
            NavDrawerItems(
                title = "News",
                icon = painterResource(id = R.drawable.news),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Sports",
                icon = painterResource(id = R.drawable.sports),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Technology",
                icon = painterResource(id = R.drawable.technology),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "International",
                icon = painterResource(id = R.drawable.international),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Finance",
                icon = painterResource(id = R.drawable.finance),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Politics",
                icon = painterResource(id = R.drawable.politics),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Business",
                icon = painterResource(id = R.drawable.business),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Economics",
                icon = painterResource(id = R.drawable.economics),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Entertainment",
                icon = painterResource(id = R.drawable.entertainment),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Beauty",
                icon = painterResource(id = R.drawable.beauty),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Travel",
                icon = painterResource(id = R.drawable.travel),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Music",
                icon = painterResource(id = R.drawable.music),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Food",
                icon = painterResource(id = R.drawable.food),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Science",
                icon = painterResource(id = R.drawable.science),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Gaming",
                icon = painterResource(id = R.drawable.gaming),
                description = "Show news"
            ),
            NavDrawerItems(
                title = "Energy",
                icon = painterResource(id = R.drawable.energy),
                description = "Show news"
            ),
        ),
        itemClick = {
            // TODO:
        })
}