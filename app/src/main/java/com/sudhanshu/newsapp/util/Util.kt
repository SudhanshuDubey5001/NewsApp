package com.sudhanshu.newsapp.util

import android.os.Build
import android.util.Log
import com.google.gson.Gson
import com.sudhanshu.newsapp.data.repository.News
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*


object Util {
    const val APIKEY = "lCRxq5i4wyr62v7kc1bpb6nBierhGr5dHtHY9GrXTD0"

        const val baseURL = "https://api.newscatcherapi.com/"
    //test----->
//    const val baseURL = "https://retoolapi.dev/"
//    const val baseURL = "https://newsapitest.free.beeceptor.com"

    const val endpoint_latest_headlines = "v2/latest_headlines"
    const val endpoint_search = "v2/search"

    const val NEWS_ID = "newsID"
    const val APP_TITLE = "Flick News"

    const val LATEST_NEWS = "latest_news"
    const val TOPIC_NEWS = "topic_news"
    const val SEARCH_NEWS = "search_news"

    fun log(s: String) = Log.d("mylog", s)


    //for converting news object to/from JSON string
    fun toJson(news: News) = Gson().toJson(news)
    fun fromJson(json: String): News = Gson().fromJson(json, News::class.java)

    //for getting country names from ISO-3166 ->
    fun getCountryName(code: String): String = Locale("", code).getDisplayCountry()

    //change date and time to "x hours ago"
    fun getTimeAgo(dateString: String): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val then = LocalDateTime
                .parse(dateString.replace(" ", "T"))
                .atOffset(ZoneOffset.UTC)
                .toInstant()
            val now = LocalDateTime.now().atOffset(ZoneOffset.UTC).toInstant()
            val diff = Duration.between(then, now).seconds
            val s: String
            return when {
                diff < 60 -> {
                    s = if (diff == 1L) " second ago" else " seconds ago"
                    diff.toString() + s
                }
                diff < 3600 -> {
                    s = if (diff / 60 == 1L) " minute ago" else " minutes ago"
                    (diff / 60).toString() + s
                }
                diff < 86400 -> {
                    s = if (diff / 3600 == 1L) " hour ago" else " hours ago"
                    (diff / 3600).toString() + s
                }
                else -> {
                    s = if (diff / 86400 == 1L) " day ago" else " days ago"
                    (diff / 86400).toString() + s
                }
            }
        } else {
            return dateString
        }
    }
}