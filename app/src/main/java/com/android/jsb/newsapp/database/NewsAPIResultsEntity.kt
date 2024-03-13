package com.android.jsb.newsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.jsb.newsapp.model.Article
import com.android.jsb.newsapp.model.NewsAPIResponse

@Entity(tableName = "news_results_table")
data class NewsAPIResultsEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    val newsCategory: String
)
