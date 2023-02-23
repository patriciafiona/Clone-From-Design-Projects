package com.patriciafiona.marioworld.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patriciafiona.marioworld.R

@Composable
fun CardPattern(
    backgroundColor: Color = Color.DarkGray,
    imageDisplay: Int,
    headline: String,
    buttonText: String,
    buttonColor: Color,
    buttonTextColor: Color
) {
    Box {
        Box(
            modifier = Modifier
                .width(350.dp)
                .height(480.dp)
        ) {
            BoxCardPatternBackground(
                backgroundColor = backgroundColor,
                lineColor = Color.Black.copy(.3f),
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .align(Alignment.Center)
            )

            Image(
                painter = painterResource(id = imageDisplay),
                contentScale = ContentScale.FillWidth,
                contentDescription = "image",
                modifier = Modifier
                    .width(280.dp)
                    .align(Alignment.TopCenter)
                    .offset {
                        IntOffset(30, -150)
                    }
            )

            Column(
                modifier = Modifier
                    .width(300.dp)
                    .height(180.dp)
                    .padding(10.dp)
                    .align(Alignment.BottomCenter)
                    .offset {
                        IntOffset(0, -220)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    headline,
                    style = TextStyle (
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    RoundedButtonIcon(
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        backgroundColor = buttonColor,
                        textColor = buttonTextColor,
                        textSize = 16,
                        text = buttonText,
                        icon = Icons.Default.ArrowForwardIos,
                        clickLogic = { }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}