package com.patriciafiona.marioworld.ui.screen.onboarding

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.view.animation.OvershootInterpolator
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.firewatchparallax.utils.SensorData
import com.patriciafiona.firewatchparallax.utils.SensorDataManager
import com.patriciafiona.marioworld.ui.theme.MarioRed
import com.patriciafiona.marioworld.R
import com.patriciafiona.marioworld.navigation.MarioScreen
import com.patriciafiona.marioworld.ui.theme.MarioRedDark
import com.patriciafiona.marioworld.ui.widget.CircleRing
import com.patriciafiona.marioworld.utils.OnLifecycleEvent
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@SuppressLint("RememberReturnType")
@Composable
fun OnboardingScreen(navController: NavController) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val scope = rememberCoroutineScope()
    val coroutineScope = rememberCoroutineScope()

    val interactionSource = remember { MutableInteractionSource() }
    val offsetX = remember { Animatable(0f) }

    val screenWidth = configuration.screenWidthDp + 20
    val screenHeight = configuration.screenHeightDp + 20

    val buttonSize = 70
    val buttonBgWidth = remember { Animatable(70f) }
    val isMoveScreen = remember{ mutableStateOf(false) }

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
    val roll by remember { derivedStateOf { (data?.roll ?: 0f) * depthMultiplier } }

    //Scale Animation
    val scale01 = remember { Animatable(0.0f) }
    val scale02 = remember { Animatable(0.0f) }
    val scale03 = remember { Animatable(0.0f) }

    LaunchedEffect(key1 = true) {
        launch {
            scale01.animateTo(
                targetValue = 1f,
                animationSpec = tween(800, easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
            )
        }
        launch {
            delay(100)
            scale02.animateTo(
                targetValue = 1f,
                animationSpec = tween(800, easing = {
                    OvershootInterpolator(3f).getInterpolation(it)
                })
            )
        }
        launch {
            delay(250)
            scale03.animateTo(
                targetValue = 1f,
                animationSpec = tween(800, easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
            )
        }
    }

    //Music section
    val mMediaPlayer = remember { MediaPlayer.create(context, R.raw.interface_sound) }
    OnLifecycle(
        mMediaPlayer = mMediaPlayer
    )

    //MainView
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
                    .scale(scale01.value)
                    .offset {
                        IntOffset(
                            (roll * 0.5).dp.roundToPx(), 0
                        )
                    }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.2f)
                .background(Color.White)
                .align(Alignment.BottomCenter)
                .scale(scale03.value)
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
                                        if(offsetX.value >= 600) {
                                            coroutineScope.launch {
                                                isMoveScreen.value = true

                                                launch {
                                                    mMediaPlayer.start()
                                                }

                                                launch {
                                                    delay(1000)

                                                    navController.navigate(MarioScreen.MainScreen.route) {
                                                        popUpTo(MarioScreen.OnboardingScreen.route) {
                                                            inclusive = true
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        coroutineScope.launch {
                                            buttonBgWidth.snapTo(buttonBgWidth.value + dragAmount.x * 0.3f)
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
                                        if(!isMoveScreen.value) {
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
                .scale(scale02.value)
                .offset {
                    IntOffset(0, (screenHeight * 0.8).toInt())
                }
        )
    }
}

@Composable
private fun OnLifecycle(
    mMediaPlayer: MediaPlayer
) {
    OnLifecycleEvent { _, event ->
        // do stuff on event
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                mMediaPlayer.isLooping = false
            }
            Lifecycle.Event.ON_PAUSE -> {
                mMediaPlayer.pause()
            }
            Lifecycle.Event.ON_DESTROY -> {
                mMediaPlayer.stop()
            }
            else -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    val navController = rememberNavController()
    OnboardingScreen(navController = navController)
}