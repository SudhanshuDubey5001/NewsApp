package com.sudhanshu.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sudhanshu.newsapp.data.NewsApi
import com.sudhanshu.newsapp.ui.news.NewsScreen
import com.sudhanshu.newsapp.ui.newsfeed.NewsFeedScreen
import com.sudhanshu.newsapp.ui.newsfeed.NewsFeedViewModel
import com.sudhanshu.newsapp.ui.theme.NewsAppTheme
import com.sudhanshu.newsapp.util.Routes
import com.sudhanshu.newsapp.util.UiEvent
import com.sudhanshu.newsapp.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.NEWS_FEED
                ) {
                    composable(Routes.NEWS_FEED) {
                        NewsFeedScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
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
    }
}