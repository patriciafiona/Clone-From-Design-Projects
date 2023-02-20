package com.patriciafiona.marioworld.ui.screen.onboarding

import android.window.SplashScreen
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.marioworld.ui.theme.MarioRed
import com.patriciafiona.marioworld.R
import com.patriciafiona.marioworld.ui.theme.MarioRedDark
import com.patriciafiona.marioworld.ui.widget.CircleRing
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun OnboardingScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val coroutineScope = rememberCoroutineScope()

    val interactionSource = remember { MutableInteractionSource() }
    val offsetX = remember { Animatable(0f) }

    val screenWidth = configuration.screenWidthDp + 20
    val screenHeight = configuration.screenHeightDp + 20

    val buttonSize = 70
    val buttonBgWidth = remember { Animatable(70f) }

    Box (modifier = Modifier
        .fillMaxSize()
        .background(MarioRed)
    ) {
        Image(
            painter = painterResource(id = R.drawable.pattern_logos_characters),
            contentDescription = "Pattern Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(.5f)
        )

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            CircleRing(
                modifier = Modifier.align(Alignment.Center),
                size = screenWidth - (screenWidth * 0.2).toInt(),
                color = Color.LightGray
            )
            Image(
                painter = painterResource(id = R.drawable.mario_run),
                contentDescription = "Mario",
                modifier = Modifier
                    .fillMaxSize(.8f)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.2f)
                .background(Color.White)
                .align(Alignment.BottomCenter)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .height(buttonSize.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape)
                    .background(Color.LightGray.copy(.2f))

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                        .background(Color.LightGray.copy(.2f))
                )
                
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.get_started),
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )

                Box(
                    modifier = Modifier
                        .width(buttonBgWidth.value.dp)
                        .height(buttonSize.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(50))
                        .background(MarioRedDark)
                        .align(Alignment.CenterStart)
                )

                Box(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                x = offsetX.value.roundToInt(),
                                y = 0
                            )
                        }
                        .size(buttonSize.dp)
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(MarioRedDark)
                        .pointerInput(Unit) {
                            var interaction: DragInteraction.Start? = null
                            detectDragGestures(
                                onDragStart = {
                                    coroutineScope.launch {
                                        interaction = DragInteraction.Start()
                                        interaction?.run {
                                            interactionSource.emit(this)
                                        }

                                    }
                                },
                                onDrag = { _: PointerInputChange, dragAmount: Offset ->
                                    if (offsetX.value in 0.0..650.0) {
                                        coroutineScope.launch {
                                            buttonBgWidth.snapTo(buttonBgWidth.value + dragAmount.x * 0.29f)
                                            offsetX.snapTo(offsetX.value + dragAmount.x.toInt())
                                        }
                                    } else {
                                        coroutineScope.launch {
                                            offsetX.snapTo(650F)
                                        }
                                    }

                                },
                                onDragCancel = {
                                    coroutineScope.launch {
                                        interaction?.run {
                                            interactionSource.emit(DragInteraction.Cancel(this))
                                        }
                                    }
                                },
                                onDragEnd = {
                                    coroutineScope.launch {
                                        launch {
                                            offsetX.animateTo(
                                                targetValue = 0f,
                                                animationSpec = tween(
                                                    durationMillis = 1000,
                                                    delayMillis = 0
                                                )
                                            )
                                        }

                                        launch {
                                            buttonBgWidth.animateTo(
                                                targetValue = 70f,
                                                animationSpec = tween(
                                                    durationMillis = 1000,
                                                    delayMillis = 0
                                                )
                                            )
                                        }
                                    }
                                }
                            )
                        },
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Icon Button",
                        tint = Color.White,
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.mario_txt),
            contentDescription = "Mario Text",
            modifier = Modifier
                .fillMaxWidth(.8f)
                .align(Alignment.Center)
                .offset {
                    IntOffset(0, (screenHeight * 0.8).toInt())
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    val navController = rememberNavController()
    OnboardingScreen(navController = navController)
}