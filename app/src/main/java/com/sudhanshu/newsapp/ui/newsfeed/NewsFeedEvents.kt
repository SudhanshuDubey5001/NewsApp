package com.sudhanshu.newsapp.ui.newsfeed

import com.sudhanshu.newsapp.data.repository.News
import com.sudhanshu.newsapp.data.repository.newsTest

sealed class NewsFeedEvents {

    data class OnNewsClick(val news: newsTest): NewsFeedEvents()

    object OnRefreshNews: NewsFeedEvents()

    object OnSearchNewsClick: NewsFeedEvents()

}