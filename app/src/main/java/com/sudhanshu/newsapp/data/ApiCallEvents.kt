package com.sudhanshu.newsapp.data

sealed class ApiCallEvents{
    object getLatestNews : ApiCallEvents()

    data class getTopicNews(val topic: String): ApiCallEvents()
}
