package com.sudhanshu.newsapp.util

import android.util.Log

object Util {
    const val APIKEY = "VAVVzTYsFm9B11qlWXFNWJ0shU97xU560T7t1IOXiJc"
    const val baseURL = "https://api.newscatcherapi.com/"

    const val endpoint_latest_headlines = "v2/latest_headlines"
    const val endpoint_search = "v2/search"

    const val NEWS_ID = "newsID"

    fun log(s: String) = Log.d("mylog", s)
}