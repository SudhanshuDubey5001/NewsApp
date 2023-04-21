package com.sudhanshu.newsapp.ui.search

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.sudhanshu.newsapp.ui.newsfeed.NewsFeedViewModel

@Composable
fun SearchNewsScreen (
    popBackStack: () -> Unit,
    viewModel: NewsFeedViewModel = hiltViewModel()
){

}