package com.android.jsb.newsapp.model

import com.android.jsb.newsapp.database.NewsAPIResultsEntity

data class NewsAPIResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

fun NewsAPIResponse.asDomainModel(newsCategory : String): List<NewsAPIResultsEntity> {
    return articles.map {
        NewsAPIResultsEntity(
            description = it.description,
            author = it.author,
            content = it.content,
            publishedAt = it.publishedAt,
            title = it.title,
            url = it.url,
            urlToImage = it.urlToImage,
            newsCategory = newsCategory
        )
    }
}
