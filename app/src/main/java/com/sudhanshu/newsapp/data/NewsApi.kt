package com.sudhanshu.newsapp.data

import com.sudhanshu.newsapp.data.repository.NewsBase
import com.sudhanshu.newsapp.util.Util
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface NewsApi {
    @Headers("x-api-key: " + Util.APIKEY)
    @GET(Util.endpoint_latest_headlines + "?lang=en&countries=UK")
    suspend fun getDeafaultNews(): Response<NewsBase>

    @Headers("x-api-key: " + Util.APIKEY)
    @GET(Util.endpoint_latest_headlines + "?lang=en")
    suspend fun getInternationalNews(): Response<NewsBase>

    @Headers("x-api-key: " + Util.APIKEY)
    @GET(Util.endpoint_latest_headlines + "?lang=en&topic={topic}")
    suspend fun getTopicNews(@Path("topic") topic: String): Response<NewsBase>

    @Headers("x-api-key: " + Util.APIKEY)
    @GET(Util.endpoint_search + "?q={query}&lang=en")
    suspend fun performSearchQuery(@Path("query") query: String): Response<NewsBase>
}