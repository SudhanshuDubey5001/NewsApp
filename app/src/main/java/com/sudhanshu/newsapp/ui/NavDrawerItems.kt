package com.sudhanshu.newsapp.ui

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class NavDrawerItems(
    val id: String,
    val title: String,
    val icon: Painter,
    val description: String
)