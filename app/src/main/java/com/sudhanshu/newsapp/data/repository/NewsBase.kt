package com.sudhanshu.newsapp.data.repository

data class NewsBase(
    val page: Int,
    val page_size: Int,
    val status: String,
    val total_hits: Int,
    val total_pages: Int,
    val articles: List<News>
)