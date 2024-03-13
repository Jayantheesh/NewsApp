package com.android.jsb.newsapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.android.jsb.newsapp.R
import com.android.jsb.newsapp.database.NewsAppDatabase
import com.android.jsb.newsapp.repository.NewsRepository
import com.android.jsb.newsapp.ui.theme.NewsAppTheme
import com.android.jsb.newsapp.ui.viewmodel.NewsViewModel
import com.android.jsb.newsapp.ui.viewmodel.ViewModelProviderFactory

class SplashActivity : ComponentActivity() {
    private lateinit var newsViewModel: NewsViewModel
    private val delay =  2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen()
        }

//        val newsRepository = NewsRepository(NewsAppDatabase.getAppDataBase(this))
//        val newsViewModelProvider = ViewModelProviderFactory(newsRepository)
//        newsViewModel = ViewModelProvider(this, newsViewModelProvider)[NewsViewModel::class.java]

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, delay)
    }
}

@Composable
fun SplashScreen() {
    NewsAppTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background) {

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .border(2.dp, color = Color.Gray, shape = RectangleShape)
                .shadow(2.dp),
                contentAlignment = Alignment.Center) {

                val imageId  = R.drawable.splash_screen_news_icon
                val imageResource : Painter = painterResource(id = imageId)

                Image(painter = imageResource,
                    contentDescription = "splash screen image",
                    contentScale = ContentScale.Fit)

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}