package com.patriciafiona.marioworld.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
    clickLogic: () -> Unit,
    isExpand: Boolean = false
) {
    Box {
        Box(
            modifier = modifier
                .width(if (isExpand) (boxWidth * 2).dp else boxWidth.dp)
                .height(if (isExpand) (boxHeight * 2).dp else boxHeight.dp)
        ) {
            BoxCardPatternBackground(
                backgroundColor = backgroundColor,
                lineColor = Color.Black.copy(.3f),
                modifier = Modifier
                    .width(if (isExpand) 600.dp else 300.dp)
                    .height(if (isExpand) 600.dp else 300.dp)
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
                    .height(if (isExpand) (boxHeight - boxHeight / 3.6).dp else (boxHeight - boxHeight / 1.8).dp)
                    .padding(10.dp)
                    .align(Alignment.BottomCenter)
                    .offset {
                        IntOffset(0, if (isExpand) -(boxHeight / 2)-50 else -(boxHeight / 3))
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    headline,
                    style = TextStyle (
                        color = Color.White,
                        fontSize = if (isExpand) (textSize * 2).sp else textSize.sp,
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
                        textSize = if (isExpand) (buttonTextSize * 2) else buttonTextSize,
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