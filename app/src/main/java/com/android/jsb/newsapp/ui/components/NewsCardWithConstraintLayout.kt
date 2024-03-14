package com.android.jsb.newsapp.ui.components

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.android.jsb.newsapp.model.Article

@Composable
fun NewsCardWithConstraintLayout(article : Article) {
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier.wrapContentHeight()
            .fillMaxWidth()
            .padding(5.dp)
            .border(2.dp, color = Color.Gray, shape = RectangleShape)
            .shadow(2.dp)
            .clickable { Toast.makeText(context, article.url, Toast.LENGTH_SHORT).show() }
    ) {
        val (image, title, content) = createRefs()

        AsyncImage(model = article.urlToImage,
            contentScale = ContentScale.Fit,
            contentDescription = "news image",
            modifier =Modifier.fillMaxWidth(1f)
                .wrapContentHeight(align = Alignment.Top)
                .padding(2.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })

        article.title?.let {
            Text(text = it,
                modifier = Modifier
                    .padding(5.dp)
                    .constrainAs(title) {
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(image.end)
                    },
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        article.description?.let {
            Text(text = it,
                modifier = Modifier
                    .padding(5.dp)
                    .constrainAs(content) {
                        top.linkTo(title.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(image.end)
                    },
                textAlign = TextAlign.Left,
                fontSize = 18.sp
            )
        }

    }
}