package com.sudhanshu.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sudhanshu.newsapp.data.NewsApi
import com.sudhanshu.newsapp.ui.navdrawer.BuildNavigationDrawer
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
//                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
                BuildNavigationDrawer(drawerState = drawerState)

            }
        }
    }
}