package com.patriciafiona.firewatchparallax.ui.screen.tower

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.patriciafiona.firewatchparallax.R
import com.patriciafiona.firewatchparallax.ui.theme.Negroni
import com.patriciafiona.firewatchparallax.ui.theme.VividOrange
import com.patriciafiona.firewatchparallax.utils.OnLifecycleEvent
import com.patriciafiona.firewatchparallax.utils.SensorData
import com.patriciafiona.firewatchparallax.utils.SensorDataManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Composable
fun TowerScreen(navController: NavController){
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val scope = rememberCoroutineScope()

    val scrollState = rememberScrollState()
    val scrollPos = scrollState.value
    val screenWidth = configuration.screenWidthDp.dp + 20.dp
    val scale = 1.1f
    val parallaxLimit = 330

    //Music section
    val mMediaPlayerCamera = MediaPlayer.create(context, R.raw.camera_shutter)
    val mMediaPlayerWind = MediaPlayer.create(context, R.raw.windy_forest_with_woodpeckers)
    val mMediaPlayerPaper = MediaPlayer.create(context, R.raw.paper_slide)
    mMediaPlayerCamera.isLooping = false
    mMediaPlayerPaper.isLooping = false
    mMediaPlayerWind.isLooping = true

    OnLifecycle(
        mMediaPlayerCamera = mMediaPlayerCamera,
        mMediaPlayerWind = mMediaPlayerWind
    )

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
            .background(Negroni)
            .verticalScroll(scrollState)
    ) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp)
                    .background(Negroni)
            )

            Image(
                painter = painterResource(id = R.drawable.parallax02_00),
                contentDescription = "parallax 0",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.03).dp)
                    .scale(scale + (scrollPos * 0.00006f))
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
                painter = painterResource(id = R.drawable.parallax02_01),
                contentDescription = "parallax 1",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.05).dp)
                    .scale(scale + (scrollPos * 0.00008f))
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
                painter = painterResource(id = R.drawable.parallax02_02),
                contentDescription = "parallax 2",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.08).dp)
                    .scale(scale + (scrollPos * 0.0001f))
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
                painter = painterResource(id = R.drawable.parallax02_03),
                contentDescription = "parallax 3",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.2).dp)
                    .scale(scale + (scrollPos * 0.0002f))
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
                painter = painterResource(id = R.drawable.parallax02_04),
                contentDescription = "parallax 4",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.3).dp)
                    .scale(scale + (scrollPos * 0.0003f))
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
                painter = painterResource(id = R.drawable.parallax02_05),
                contentDescription = "parallax 5",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.4).dp)
                    .scale(scale + (scrollPos * 0.0004f))
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

            // Bird section
            Image(
                painter = painterResource(id = R.drawable.parallax02_06),
                contentDescription = "parallax 6",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.5).dp)
                    .scale(scale + (scrollPos * 0.0005f))
                    .offset {
                        IntOffset(
                            (pitch * 2.3).dp.roundToPx(),
                            if (scrollPos < parallaxLimit) {
                                (scrollPos * 1.5).toInt() + (roll * 0.9).dp.roundToPx()
                            } else {
                                (parallaxLimit * 1.5).toInt() + (roll * 0.9).dp.roundToPx()
                            }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax02_07),
                contentDescription = "parallax 7",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.6).dp)
                    .scale(scale + (scrollPos * 0.0006f))
                    .offset {
                        IntOffset(
                            (-pitch * 1.8).dp.roundToPx(),
                            if (scrollPos < parallaxLimit) {
                                (scrollPos * 1.6).toInt() - (roll * 0.8).dp.roundToPx()
                            } else {
                                (parallaxLimit * 1.6).toInt() - (roll * 0.8).dp.roundToPx()
                            }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax02_08),
                contentDescription = "parallax 8",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.8).dp)
                    .scale(scale + (scrollPos * 0.0007f))
                    .offset {
                        IntOffset(
                            (pitch * 2.5).dp.roundToPx(),
                            if (scrollPos < parallaxLimit) {
                                (scrollPos * 1.8).toInt() + (roll * 1.2).dp.roundToPx()
                            } else {
                                (parallaxLimit * 1.8).toInt() + (roll * 1.2).dp.roundToPx()
                            }
                        )
                    }
            )
            // End of Bird section

            Image(
                painter = painterResource(id = R.drawable.parallax02_09),
                contentDescription = "parallax 9",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 1.4).dp)
                    .scale(scale + (scrollPos * 0.0008f))
                    .offset {
                        IntOffset(
                            (pitch * 1.4).dp.roundToPx(),
                            if (scrollPos < parallaxLimit) {
                                (scrollPos * 1.4).toInt() + (roll * 0.7).dp.roundToPx()
                            } else {
                                (parallaxLimit * 1.4).toInt() + (roll * 0.7).dp.roundToPx()
                            }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax02_10),
                contentDescription = "parallax 10",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(screenWidth + (scrollPos * 2.2).dp)
                    .scale(scale + (scrollPos * 0.002f))
                    .offset {
                        IntOffset(
                            (pitch * 2.7).dp.roundToPx(),
                            if (scrollPos < parallaxLimit) {
                                (scrollPos * 2.2).toInt() + (roll * 0.99).dp.roundToPx()
                            } else {
                                (parallaxLimit * 2.2).toInt() + (roll * 0.99).dp.roundToPx()
                            }
                        )
                    }
            )

            IconButton(onClick = {
                scope.launch {
                    mMediaPlayerPaper.start()

                    delay(500)

                    mMediaPlayerCamera.stop()
                    mMediaPlayerWind.stop()
                    mMediaPlayerPaper.stop()

                    delay(100)

                    navController.navigateUp()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back button",
                    tint = Color.White
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .offset {
                    IntOffset(0, -500)
                }
        ){
            Text(
                stringResource(id = R.string.interact_by_scrolling_or_rotating),
                style = TextStyle(
                    color = VividOrange,
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(
                        if (scrollPos <= 100) {
                            (100 - (scrollPos * 100 / 100f)) / 100f
                        } else {
                            0f
                        }
                    )
            )
        }
    }
}

@Composable
private fun OnLifecycle(
    mMediaPlayerCamera: MediaPlayer,
    mMediaPlayerWind: MediaPlayer,
) {
    OnLifecycleEvent { _, event ->
        // do stuff on event
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                mMediaPlayerCamera.start()
            }
            Lifecycle.Event.ON_RESUME -> {
                mMediaPlayerWind.start()
            }
            Lifecycle.Event.ON_PAUSE -> {
                mMediaPlayerCamera.pause()
                mMediaPlayerWind.pause()
            }
            Lifecycle.Event.ON_DESTROY -> {
                mMediaPlayerCamera.stop()
                mMediaPlayerWind.stop()
            }
            else -> {}
        }
    }
}