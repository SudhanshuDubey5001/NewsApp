package com.sudhanshu.newsapp.data.repository

data class News(
    val _id: String,
    val _score: Any,
    val author: String?,
    val authors: String?,
    val clean_url: String,
    val country: String,
    val excerpt: String?,
    val is_opinion: Boolean,
    val language: String?,
    val link: String,
    val media: String?,
    val published_date: String?,
    val published_date_precision: String,
    val rank: Int,
    val rights: String,
    val summary: String?,
    val title: String?,
    val topic: String?,
    val twitter_account: Any
)