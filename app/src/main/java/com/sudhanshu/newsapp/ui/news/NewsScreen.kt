package com.sudhanshu.newsapp.ui.news

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.sudhanshu.newsapp.util.UiEvent

@Composable
fun NewsScreen(
    onNavigate: (UiEvent.navigate) -> Unit,
    viewModel: NewsViewModel = hiltViewModel()
) {
}