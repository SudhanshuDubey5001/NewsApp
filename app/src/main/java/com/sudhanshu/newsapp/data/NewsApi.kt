package com.sudhanshu.newsapp.data

import com.sudhanshu.newsapp.data.repository.News
import com.sudhanshu.newsapp.data.repository.NewsBase
import com.sudhanshu.newsapp.util.Util
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {
    @Headers("x-api-key: " + Util.APIKEY)
    @GET(Util.endpoint_latest_headlines + "?lang=en&countries=UK")
    suspend fun getDeafaultNews(): Response<NewsBase>

    @Headers("x-api-key: " + Util.APIKEY)
    @GET(Util.endpoint_latest_headlines)
    suspend fun getTopicNews(
        @Query("topic") topic: String,
        @Query("lang") language: String
    ): Response<NewsBase>

    @Headers("x-api-key: " + Util.APIKEY)
    @GET(Util.endpoint_search + "lang=en")
    suspend fun performSearchQuery(@Query("query") query: String): Response<NewsBase>


    /** Test API **/
    @GET("J8SiIW/defaultnews")
    suspend fun _getdefaultNews(): Response<List<News>>

    @GET("9qgXzd/topic_news")
    suspend fun _getTopicNews(): Response<List<News>>
}