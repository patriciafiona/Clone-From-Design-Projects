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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardPattern(
    modifier: Modifier = Modifier,
    boxHeight: Int,
    boxWidth: Int,
    backgroundColor: Color = Color.DarkGray,
    imageDisplay: Int,
    imgOffsetX: Int,
    imgOffsetY: Int,
    headline: String,
    textSize: Int,
    buttonText: String,
    buttonColor: Color,
    buttonTextColor: Color,
    buttonTextSize: Int,
    clickLogic: () -> Unit
) {
    Box {
        Box(
            modifier = modifier
                .width(boxWidth.dp)
                .height(boxHeight.dp)
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
                    .fillMaxWidth(.8f)
                    .align(Alignment.TopCenter)
                    .offset {
                        IntOffset(imgOffsetX, imgOffsetY)
                    }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .height((boxHeight - boxHeight / 1.8).dp)
                    .padding(10.dp)
                    .align(Alignment.BottomCenter)
                    .offset {
                        IntOffset(0, -(boxHeight / 3))
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    headline,
                    style = TextStyle (
                        color = Color.White,
                        fontSize = textSize.sp,
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
                        textSize = buttonTextSize,
                        text = buttonText,
                        icon = Icons.Default.ArrowForwardIos,
                        clickLogic = clickLogic
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}