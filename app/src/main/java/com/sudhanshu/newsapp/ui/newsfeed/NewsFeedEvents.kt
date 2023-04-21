package com.sudhanshu.newsapp.ui.newsfeed

import com.sudhanshu.newsapp.data.repository.News

sealed class NewsFeedEvents {

    data class OnNewsClick(val news: News): NewsFeedEvents()

    object OnRefreshNews: NewsFeedEvents()

    data class OnSearchNewsClick(val query: String): NewsFeedEvents()

    data class OnNavigationDrawerItemClicked(val topic: String): NewsFeedEvents()

}