package com.patriciafiona.marioworld.ui.screen.onboarding

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.media.MediaPlayer
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
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
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.IconButton
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
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
import com.patriciafiona.marioworld.R
import com.patriciafiona.marioworld.navigation.MarioScreen
import com.patriciafiona.marioworld.ui.theme.MarioRed
import com.patriciafiona.marioworld.ui.theme.MarioRedDark
import com.patriciafiona.marioworld.ui.widget.CircleRing
import com.patriciafiona.marioworld.ui.widget.LockScreenOrientation
import com.patriciafiona.marioworld.utils.ContentType
import com.patriciafiona.marioworld.utils.OnLifecycleEvent
import com.patriciafiona.marioworld.utils.setNavigationBarColor
import com.patriciafiona.marioworld.utils.setStatusBarColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@SuppressLint("RememberReturnType")
@Composable
fun OnboardingScreen(
    navController: NavController,
    isMute: MutableState<Boolean>,
    windowSize: WindowWidthSizeClass
) {
    val contentType: ContentType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            ContentType.NORMAL
        }
        WindowWidthSizeClass.Medium -> {
            ContentType.NORMAL
        }
        WindowWidthSizeClass.Expanded -> {
            ContentType.LARGE
        }
        else -> {
            ContentType.NORMAL
        }
    }
    val isLarge = contentType == ContentType.LARGE

    if(contentType == ContentType.NORMAL) {
        LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val scope = rememberCoroutineScope()
    val coroutineScope = rememberCoroutineScope()

    //haptic feedback
    val haptic = LocalHapticFeedback.current

    //Set status & Navigation Bar color
    setStatusBarColor(color = MarioRed)
    setNavigationBarColor(color = Color.White)

    val isChangeScreen = remember{ mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }
    val offsetX = remember { Animatable(0f) }

    val screenWidth = configuration.screenWidthDp + if (isLarge) 40 else 20
    val screenHeight = configuration.screenHeightDp + if (isLarge) 40 else 20

    val buttonSize = if (isLarge) 140 else 70
    val buttonBgWidth = remember { Animatable(if (isLarge) 140f else 70f) }
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

    val depthMultiplier = if (isLarge) 40 else 20
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
    val buttonSound = remember { MediaPlayer.create(context, R.raw.continue_sound) }
    val bgmSound = remember { MediaPlayer.create(context, R.raw.bgm_opening) }
    OnLifecycle(
        buttonSound = buttonSound,
        bgmSound = bgmSound,
        isMute = isMute
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

        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .scale(scale01.value)
                .padding(if (isLarge) 30.dp else 10.dp),
            onClick = {
                //mute or unmute sound
                isMute.value = !isMute.value
                if(isMute.value) {
                    bgmSound.setVolume(0.0f, 0.0f)
                }else{
                    bgmSound.setVolume(1.0f, 1.0f)
                }
            }
        ) {
            androidx.compose.material3.Icon(
                modifier = Modifier.size(if (isLarge) 120.dp else 20.dp),
                imageVector = if(isMute.value) { Icons.Default.VolumeMute } else { Icons.Default.VolumeUp },
                contentDescription = "Back button",
                tint = Color.White
            )
        }
        
        if(isLarge){
            val configuration = LocalConfiguration.current
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    //LARGE IN BIG SCREEN - LANDSCAPE
                    MarioImage(screenWidth, scale01, roll, isLarge)
                }
                else -> {
                    //LARGE IN BIG SCREEN - PORTRAIT
                    MarioImage(screenWidth, scale01, roll)
                }
            }
        }else{
            //NORMAL VIEW
            MarioImage(screenWidth, scale01, roll)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.3f)
                .background(Color.White)
                .align(Alignment.BottomCenter)
                .scale(scale03.value)
        ) {
            Box(
                modifier = Modifier
                    .width(if (isLarge) 600.dp else 300.dp)
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
                        fontSize = if (isLarge) 32.sp else 16.sp
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
                                    if (offsetX.value in 0.0..670.0) {
                                        if (offsetX.value >= 620) {
                                            coroutineScope.launch {
                                                isMoveScreen.value = true

                                                launch {
                                                    if (!isChangeScreen.value) {
                                                        isChangeScreen.value = true

                                                        haptic.performHapticFeedback(
                                                            HapticFeedbackType.LongPress)

                                                        buttonSound.start()

                                                        delay(1000)

                                                        navController.navigate(MarioScreen.MainScreen.route) {
                                                            popUpTo(MarioScreen.OnboardingScreen.route) {
                                                                inclusive = true
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        coroutineScope.launch {
                                            buttonBgWidth.snapTo(buttonBgWidth.value + if (isLarge) (dragAmount.x * 0.6f) else (dragAmount.x * 0.3f))
                                            offsetX.snapTo(offsetX.value + dragAmount.x.toInt())
                                        }
                                    } else {
                                        coroutineScope.launch {
                                            offsetX.snapTo(670F)
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
                                        if (!isMoveScreen.value) {
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

        if(isLarge){
            val configuration = LocalConfiguration.current
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    //LARGE IN BIG SCREEN - LANDSCAPE
                    Image(
                        painter = painterResource(id = R.drawable.mario_txt),
                        contentDescription = "Mario Text",
                        modifier = Modifier
                            .fillMaxWidth(.3f)
                            .align(Alignment.Center)
                            .scale(scale02.value)
                            .offset {
                                IntOffset(0, (screenHeight * 0.25).toInt())
                            }
                    )
                }
                Configuration.ORIENTATION_PORTRAIT -> {
                    //LARGE IN BIG SCREEN - PORTRAIT
                    Image(
                        painter = painterResource(id = R.drawable.mario_txt),
                        contentDescription = "Mario Text",
                        modifier = Modifier
                            .fillMaxWidth(.8f)
                            .align(Alignment.Center)
                            .scale(scale02.value)
                            .offset {
                                IntOffset(0, (screenHeight * 0.3).toInt())
                            }
                    )
                }
            }
        }else{
            //NORMAL VIEW
            Image(
                painter = painterResource(id = R.drawable.mario_txt),
                contentDescription = "Mario Text",
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .align(Alignment.Center)
                    .scale(scale02.value)
                    .offset {
                        IntOffset(0, (screenHeight * 0.55).toInt())
                    }
            )
        }
    }
}

@Composable
private fun MarioImage(
    screenWidth: Int,
    scale01: Animatable<Float, AnimationVector1D>,
    roll: Float,
    isLarge: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        CircleRing(
            modifier = Modifier.align(Alignment.Center),
            size = if(isLarge) screenWidth - (screenWidth * 0.65).toInt() else screenWidth - (screenWidth * 0.2).toInt(),
            color = Color.LightGray
        )

        Image(
            painter = painterResource(id = R.drawable.mario_run),
            contentDescription = "Mario",
            modifier = Modifier
                .fillMaxSize(if(isLarge) .6f else .8f)
                .align(Alignment.Center)
                .scale(scale01.value)
                .offset {
                    IntOffset(
                        (roll * 0.5).dp.roundToPx(), 0
                    )
                }
        )
    }
}

@Composable
private fun OnLifecycle(
    buttonSound: MediaPlayer,
    bgmSound: MediaPlayer,
    isMute: MutableState<Boolean>
) {
    OnLifecycleEvent { _, event ->
        // do stuff on event
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                buttonSound.isLooping = false
                bgmSound.isLooping = true
                bgmSound.start()
            }
            Lifecycle.Event.ON_RESUME -> {
                if(isMute.value) {
                    bgmSound.setVolume(0.0f, 0.0f)
                }else{
                    bgmSound.setVolume(1.0f, 1.0f)
                }
            }
            Lifecycle.Event.ON_PAUSE -> {
                buttonSound.pause()
                bgmSound.pause()
            }
            Lifecycle.Event.ON_DESTROY -> {
                buttonSound.stop()
                buttonSound.release()

                bgmSound.stop()
                bgmSound.release()
            }
            else -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview
@Composable
fun OnboardingScreenPreview(){
    val navController: NavController = rememberNavController()
    val isMute: MutableState<Boolean> = remember { mutableStateOf(false) }
    val windowSize: WindowWidthSizeClass = calculateWindowSizeClass(LocalContext.current as Activity).widthSizeClass

    OnboardingScreen(
        navController = navController,
        isMute = isMute,
        windowSize = windowSize
    )
}