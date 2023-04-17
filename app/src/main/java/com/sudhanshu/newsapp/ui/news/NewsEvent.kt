package com.sudhanshu.newsapp.ui.news

sealed class NewsEvent {
    object popBackStack: NewsEvent()
}