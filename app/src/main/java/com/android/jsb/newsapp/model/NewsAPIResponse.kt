package com.android.jsb.newsapp.model

data class NewsAPIResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
