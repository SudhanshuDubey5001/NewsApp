package com.sudhanshu.newsapp.util

import android.util.Log

object Util {
    const val APIKEY = "Zc3ML3mu2S_lF4iruadYXb4y-zVxjIu87VID507Dp2E"
    const val baseURL = "https://api.newscatcherapi.com/"

    const val endpoint_latest_headlines = "v2/latest_headlines"
    const val endpoint_search = "v2/search"

    const val NEWS_ID = "newsID"

    fun log(s: String) = Log.d("mylog", s)
}