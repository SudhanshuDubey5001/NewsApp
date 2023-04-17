package com.sudhanshu.newsapp.ui.navdrawer

import android.graphics.drawable.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class NavDrawerItems(
    val title: String,
    val icon: ImageVector,
    val description: String
)