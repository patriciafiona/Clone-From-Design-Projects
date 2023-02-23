package com.patriciafiona.marioworld.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patriciafiona.marioworld.data.entities.News
import com.patriciafiona.marioworld.ui.theme.SuperMarioFont

@Composable
fun ItemNews(
    news: News
) {
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = Modifier
            .width(220.dp)
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .clickable {
                uriHandler.openUri(news.link)
            },
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = painterResource(id = news.image),
                contentDescription = "News Image",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = news.date,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 12.sp,
                    fontFamily = SuperMarioFont
                )
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = news.title,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = SuperMarioFont
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = news.headline,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 12.sp,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}