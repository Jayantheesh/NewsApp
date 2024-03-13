package com.android.jsb.newsapp.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.android.jsb.newsapp.database.NewsAPIResultsEntity
import com.android.jsb.newsapp.database.NewsAppDatabase
import com.android.jsb.newsapp.model.Article
import com.android.jsb.newsapp.repository.NewsRepository
import com.android.jsb.newsapp.ui.components.NewsCardWithConstraintLayout
import com.android.jsb.newsapp.ui.theme.NewsAppTheme
import com.android.jsb.newsapp.ui.viewmodel.NewsViewModel
import com.android.jsb.newsapp.ui.viewmodel.ViewModelProviderFactory
import com.android.jsb.newsapp.utils.Resource

class MainActivity : ComponentActivity() {

    private lateinit var newsViewModel: NewsViewModel
    private val TAG : String = "MainActivity"
    private var articleList: List<Article>? = null
    private var latestNews: LiveData<List<NewsAPIResultsEntity>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newsRepository = NewsRepository(NewsAppDatabase.getAppDataBase(this))
        val newsViewModelProvider = ViewModelProviderFactory(newsRepository)
        newsViewModel = ViewModelProvider(this, newsViewModelProvider)[NewsViewModel::class.java]
        newsViewModel.topNewsResponse.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { newsAPIResponse ->
                        articleList = newsAPIResponse.articles
                        println(articleList)
                        println("Success response received")
                        setContent {
                            TopNewsScreen()
                        }
                    }
                }
                is Resource.Error -> println("Error occurred")
                is Resource.Loading -> println("Loading in progress")
            }
        }
        newsViewModel.latestNews.observe(this) {
           println(it)
        }
//        setContent {
//            TopNewsScreen()
//        }
    }

    @Composable
    fun TopNewsScreen() {
        NewsAppTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                NewsCard()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        TopNewsScreen()
    }

    @Composable
    fun NewsCard() {
        Surface(
            modifier = Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight(align = Alignment.Top)
                .padding(5.dp)
                .border(1.dp, color = Color.Gray, shape = RectangleShape)
                .shadow(1.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn {
                for (article in articleList!!) {
                    item {
                        NewsCardWithConstraintLayout(article)
                    }
                }
            }
        }
    }
}