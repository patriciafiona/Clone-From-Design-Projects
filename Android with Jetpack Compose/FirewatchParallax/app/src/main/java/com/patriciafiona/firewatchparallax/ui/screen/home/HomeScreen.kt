package com.patriciafiona.firewatchparallax.ui.screen.home

import android.annotation.SuppressLint
import android.graphics.Paint.Align
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.firewatchparallax.R
import com.patriciafiona.firewatchparallax.ui.theme.AmericanBronze
import com.patriciafiona.firewatchparallax.ui.theme.RusticRed
import com.patriciafiona.firewatchparallax.ui.theme.VividOrange
import com.patriciafiona.firewatchparallax.utils.SensorData
import com.patriciafiona.firewatchparallax.utils.SensorDataManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.stream.Collectors

@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val scope = rememberCoroutineScope()

    val scrollState = rememberScrollState()
    val scrollPos = scrollState.value
    val screenWidth = configuration.screenWidthDp.dp + 20.dp
    val scale = 1.3f
    val parallaxLimit = 350

    //Sensor for x position
    var data by remember { mutableStateOf<SensorData?>(null) }

    DisposableEffect(Unit) {
        val dataManager = SensorDataManager(context)
        dataManager.init()

        val job = scope.launch {
            dataManager.data
                .receiveAsFlow()
                .onEach { data = it }
                .collect(collector = { })
        }

        onDispose {
            dataManager.cancel()
            job.cancel()
        }
    }

    val depthMultiplier = 20
    val pitch by remember { derivedStateOf { (data?.pitch ?: 0f) * depthMultiplier } }
    val roll by remember { derivedStateOf { (data?.roll ?: 0f) * depthMultiplier } }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(RusticRed)
            .verticalScroll(scrollState)
    ) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(VividOrange)
            )

           Image(
               painter = painterResource(id = R.drawable.parallax0),
               contentDescription = "parallax 0",
               modifier = Modifier
                   .align(Alignment.Center)
                   .width(screenWidth + (scrollPos * 1.03).dp)
                   .scale(scale)
                   .offset {
                       IntOffset(
                           (pitch * 1.03).dp.roundToPx(),
                           if (scrollPos < parallaxLimit) {
                               (scrollPos * 1.03).toInt() + (roll * 0.1).dp.roundToPx()
                           } else {
                               (parallaxLimit * 1.03).toInt() + (roll * 0.1).dp.roundToPx()
                           }
                       )
                   }
           )

            Image(
                painter = painterResource(id = R.drawable.parallax1),
                contentDescription = "parallax 1",
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.05).dp)
                    .scale(scale)
                    .offset {
                        IntOffset(
                            (pitch * 1.05).dp.roundToPx(),
                            if (scrollPos < parallaxLimit) {
                                (scrollPos * 1.05).toInt() + (roll * 0.2).dp.roundToPx()
                            } else {
                                (parallaxLimit * 1.05).toInt() + (roll * 0.2).dp.roundToPx()
                            }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax2),
                contentDescription = "parallax 2",
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.08).dp)
                    .scale(scale)
                    .offset {
                        IntOffset(
                            (pitch * 1.08).dp.roundToPx(),
                            if (scrollPos < parallaxLimit) {
                                (scrollPos * 1.08).toInt() + (roll * 0.3).dp.roundToPx()
                            } else {
                                (parallaxLimit * 1.08).toInt() + (roll * 0.3).dp.roundToPx()
                            }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax3),
                contentDescription = "parallax 3",
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.2).dp)
                    .scale(scale)
                    .offset {
                        IntOffset(
                            (pitch * 1.2).dp.roundToPx(),
                            if (scrollPos < parallaxLimit) {
                                (scrollPos * 1.2).toInt() + (roll * 0.4).dp.roundToPx()
                            } else {
                                (parallaxLimit * 1.2).toInt() + (roll * 0.4).dp.roundToPx()
                            }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax4),
                contentDescription = "parallax 4",
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.3).dp)
                    .scale(scale)
                    .offset {
                        IntOffset(
                            (pitch * 1.3).dp.roundToPx(),
                            if (scrollPos < parallaxLimit) {
                                (scrollPos * 1.3).toInt() + (roll * 0.5).dp.roundToPx()
                            } else {
                                (parallaxLimit * 1.3).toInt() + (roll * 0.5).dp.roundToPx()
                            }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax5),
                contentDescription = "parallax 5",
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.4).dp)
                    .scale(scale)
                    .offset {
                        IntOffset(
                            (pitch * 1.4).dp.roundToPx(),
                            if (scrollPos < parallaxLimit) {
                                (scrollPos * 1.4).toInt() + (roll * 0.6).dp.roundToPx()
                            } else {
                                (parallaxLimit * 1.4).toInt() + (roll * 0.6).dp.roundToPx()
                            }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax6),
                contentDescription = "parallax 6",
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.5).dp)
                    .scale(scale)
                    .offset {
                        IntOffset(
                            (pitch * 1.5).dp.roundToPx(),
                            if (scrollPos < parallaxLimit) {
                                (scrollPos * 1.5).toInt() + (roll * 0.7).dp.roundToPx()
                            } else {
                                (parallaxLimit * 1.5).toInt() + (roll * 0.7).dp.roundToPx()
                            }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax7),
                contentDescription = "parallax 7",
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.6).dp)
                    .scale(scale * 1.2f)
                    .offset {
                        IntOffset(
                            (pitch * 1.6).dp.roundToPx(),
                            if (scrollPos < parallaxLimit) {
                                (scrollPos * 1.6).toInt() + (roll * 0.8).dp.roundToPx()
                            } else {
                                (parallaxLimit * 1.6).toInt() + (roll * 0.8).dp.roundToPx()
                            }
                        )
                    }
            )

            Column(
                modifier = Modifier
                    .width(screenWidth + (scrollPos * 1.7).dp)
                    .align(Alignment.Center)
                    .scale(scale * 1.6f)
                    .offset {
                        IntOffset(
                            (pitch * 1.7).dp.roundToPx(),
                            if (scrollPos < parallaxLimit) {
                                (scrollPos * 1.7).toInt() + (roll * 0.9).dp.roundToPx()
                            } else {
                                (parallaxLimit * 1.7).toInt() + (roll * 0.9).dp.roundToPx()
                            }
                        )
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.parallax8),
                    contentDescription = "parallax 8",
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(RusticRed)
                        .offset {
                            IntOffset(0, 200)
                        }
                )
            }
        }

        //Background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .offset {
                    IntOffset(0, -300)
                }
        )
    }
}