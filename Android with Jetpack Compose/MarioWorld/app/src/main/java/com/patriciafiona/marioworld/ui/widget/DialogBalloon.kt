package com.patriciafiona.marioworld.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patriciafiona.marioworld.ui.theme.BgGreen
import com.patriciafiona.marioworld.R

@Composable
fun DialogBalloon(
    modifier: Modifier = Modifier,
    text: String,
    balloonColor: Color,
    textColor: Color
) {
    Row(modifier = modifier
        .padding(10.dp)
        .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(.6f)
                .height(IntrinsicSize.Max)
        ) {
            Column (
                modifier = Modifier
                    .background(
                        color = balloonColor,
                        shape = RoundedCornerShape(4.dp, 4.dp, 0.dp, 4.dp)
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = text,
                    style = TextStyle(
                        color = textColor,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic
                    )
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        color = balloonColor,
                        shape = TriangleEdgeShape(22)
                    )
                    .width(20.dp)
                    .fillMaxHeight()
            )
        }

        Spacer(modifier = Modifier.width(5.dp))

        Image(
            painter = painterResource(id = R.drawable.lakitu_tips),
            contentDescription = "Lakitu tips",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Preview
@Composable
fun DialogBalloonPreview() {
    DialogBalloon(
        modifier = Modifier
            .width(300.dp),
        text = "My bananas and my buddy, Diddy Kong, they are gone! The Kremlings will pay! " +
                "I'll hunt them down through every corner of my island, until I have every last banana from my hoard back!",
        balloonColor = BgGreen,
        textColor = Color.White,
    )
}