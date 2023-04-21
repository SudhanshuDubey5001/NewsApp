package com.sudhanshu.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import com.sudhanshu.newsapp.ui.navdrawer.MainCompose
import com.sudhanshu.newsapp.ui.theme.NewsAppTheme
import com.sudhanshu.newsapp.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                MainCompose()
            }
        }
    }
}

