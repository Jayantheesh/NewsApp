package com.android.jsb.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.android.jsb.newsapp.database.NewsAppDatabase
import com.android.jsb.newsapp.repository.NewsRepository
import com.android.jsb.newsapp.ui.theme.NewsAppTheme
import com.android.jsb.newsapp.ui.viewmodel.NewsViewModel
import com.android.jsb.newsapp.ui.viewmodel.ViewModelProviderFactory

class MainActivity : ComponentActivity() {
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        val newsRepository = NewsRepository(NewsAppDatabase.getAppDataBase(this))
        val newsViewModelProvider = ViewModelProviderFactory(newsRepository)
        newsViewModel = ViewModelProvider(this, newsViewModelProvider)[NewsViewModel::class.java]

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsAppTheme {
        Greeting("Android")
    }
}