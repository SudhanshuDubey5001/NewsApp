package com.sudhanshu.newsapp.util

import android.util.Log
import com.google.gson.Gson
import com.sudhanshu.newsapp.data.repository.News
import com.sudhanshu.newsapp.data.repository.newsTest

object Util {
    const val APIKEY = "VAVVzTYsFm9B11qlWXFNWJ0shU97xU560T7t1IOXiJc"
    const val baseURL = "https://api.newscatcherapi.com/"

    const val endpoint_latest_headlines = "v2/latest_headlines"
    const val endpoint_search = "v2/search"

    const val NEWS_ID = "newsID"
    const val APP_TITLE = "Flick News"

    fun log(s: String) = Log.d("mylog", s)


    //for converting news object to/from JSON string
    fun toJson(news: newsTest) = Gson().toJson(news)
    fun fromJson(json: String): newsTest = Gson().fromJson(json, newsTest::class.java)
}