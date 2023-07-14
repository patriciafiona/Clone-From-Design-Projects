package com.patriciafiona.marioworld.ui.screen.main

import android.media.MediaPlayer
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.patriciafiona.marioworld.R
import com.patriciafiona.marioworld.navigation.MarioScreen
import com.patriciafiona.marioworld.ui.theme.*
import com.patriciafiona.marioworld.ui.widget.*
import com.patriciafiona.marioworld.utils.BackPress
import com.patriciafiona.marioworld.utils.ContentType
import com.patriciafiona.marioworld.utils.OnLifecycleEvent
import com.patriciafiona.marioworld.utils.setNavigationBarColor
import com.patriciafiona.marioworld.utils.setStatusBarColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, isMute: MutableState<Boolean>, windowSize: WindowWidthSizeClass) {
    //Adaptive view
    val viewModel = MainViewModel()

    val contentType: ContentType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            ContentType.NORMAL
        }
        WindowWidthSizeClass.Medium -> {
            ContentType.NORMAL
        }
        WindowWidthSizeClass.Expanded -> {
            ContentType.LIST_AND_DETAIL
        }
        else -> {
            ContentType.NORMAL
        }
    }

    if (contentType == ContentType.NORMAL) {
        CoreSection(
            navController = navController,
            isMute = isMute,
            viewModel = viewModel
        )
    }else{
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            CoreSection(
                navController = navController,
                isMute = isMute,
                viewModel = viewModel,
                isExpand = true
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CoreSection(
    navController: NavController,
    isMute: MutableState<Boolean>,
    viewModel: MainViewModel,
    isExpand: Boolean = false
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val uriHandler = LocalUriHandler.current

    val isLoading = remember{ mutableStateOf(true) }
    LaunchedEffect(key1 = true) {
        delay((500..2000).random().toLong())
        isLoading.value = false
    }

    val scrollState = rememberScrollState()
    val scrollPos = scrollState.value
    val parallaxLimit = if(isExpand) 850 else 750

    //Set status & Navigation Bar color
    val color01 = remember { Animatable(BgBlue) }
    val color02 = remember { Animatable(BgPurple) }

    setStatusBarColor(color =
        when (scrollPos) {
            in 0..850 -> {
                color01.value
            }
            in 850..2100 -> {
                Color.DarkGray
            }
            else -> {
                color01.value
            }
        }
    )
    setNavigationBarColor(color =
        if(scrollPos > 5000){
            MarioRedDark
        }else{
            color02.value
        }
    )

    //Animate color based on scroll position
    if(scrollPos in 0..199) {
        LaunchedEffect(key1 = true) {
            color01.animateTo(BgBlue, animationSpec = tween(1000))
            color02.animateTo(BgPurple, animationSpec = tween(1000))
        }
    }else if(scrollPos in 200..4000) {
        LaunchedEffect(key1 = true) {
            color01.animateTo(BgGreen, animationSpec = tween(1000))
            color02.animateTo(BgTosca, animationSpec = tween(1000))
        }
    } else if(scrollPos > 4000) {
        LaunchedEffect(key1 = true) {
            color01.animateTo(BgMagenta, animationSpec = tween(1000))
            color02.animateTo(BgOrange, animationSpec = tween(1000))
        }
    }

    //Sound effect
    val currentPos = rememberSaveable{ mutableStateOf(0) }
    val buttonSound = remember { MediaPlayer.create(context, R.raw.pause) }
    val bgmSound = remember { MediaPlayer.create(context, R.raw.bgm_overworld) }
    OnLifecycle(
        buttonSound = buttonSound,
        bgmSound = bgmSound,
        currentPos = currentPos,
        isMute = isMute
    )

    //Back press exit attributes
    var showToast by remember { mutableStateOf(false) }
    var backPressState by remember { mutableStateOf<BackPress>(BackPress.Idle) }
    if(showToast){
        Toast.makeText(context, stringResource(id = R.string.press_again_to_exit), Toast.LENGTH_SHORT).show()
        showToast= false
    }

    LaunchedEffect(key1 = backPressState) {
        if (backPressState == BackPress.InitialTouch) {
            delay(2000)
            backPressState = BackPress.Idle
        }
    }

    BackHandler(backPressState == BackPress.Idle) {
        backPressState = BackPress.InitialTouch
        showToast = true
    }

    Scaffold {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            color01.value, color02.value
                        )
                    )
                )
                .padding(it)
        ){
            Image(
                painter = painterResource(id = R.drawable.pattern_logos_characters),
                contentDescription = "Pattern Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(.5f)
            )

            if(scrollPos > 5800 && !isLoading.value) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .background(MarioRedDark)
                        .align(Alignment.BottomCenter)
                )
            }

            if(isLoading.value) {
                Loader()
            }else{
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                ) {
                    TopSection(
                        scrollPos,
                        parallaxLimit,
                        isMute,
                        bgmSound,
                        isExpand
                    )

                    Box {
                        BoxPatternBackground(
                            backgroundColor = Color.DarkGray,
                            lineColor = Color.Black.copy(.3f),
                            stepValue = 20,
                            modifier = Modifier
                                .height(if (isExpand) 440.dp else 220.dp)
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                        )

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(if(isExpand) 10.dp else 5.dp),
                            contentPadding = PaddingValues(horizontal = if(isExpand) 32.dp else 16.dp),
                        ) {
                            items(viewModel.getAllNews()) { news ->
                                ItemNews(news = news, isExpand = isExpand)
                            }
                        }
                    }

                    Box {
                        TrapezoidPatternBackground(
                            backgroundColor = Color.DarkGray,
                            lineColor = Color.Black.copy(.3f),
                            stepValue = 20,
                            modifier = Modifier
                                .height(if(isExpand) 280.dp else 140.dp)
                                .fillMaxWidth(),
                            isTop = false
                        )

                        Row(
                            modifier = Modifier
                                .padding(20.dp)
                                .align(Alignment.TopCenter)
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            RoundedButtonIcon(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f),
                                backgroundColor = Color.White,
                                textColor = Color.Black,
                                textSize = if(isExpand) 32 else 16,
                                text = stringResource(id = R.string.see_more_news),
                                icon = Icons.Default.ArrowForwardIos,
                                isBigIcon = true,
                                clickLogic = {
                                    coroutineScope.launch {
                                        launch {
                                            buttonSound.start()
                                        }
                                        delay(500)
                                        uriHandler.openUri("https://mario.nintendo.com/news/")
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }

                    Spacer(modifier = Modifier.height(80.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row{
                            Spacer(modifier = Modifier.weight(1f))

                            CardPattern(
                                boxWidth = 350,
                                boxHeight = 480,
                                imageDisplay = R.drawable.mario_dice,
                                imgOffsetX = 30,
                                imgOffsetY = -150,
                                headline = stringResource(id = R.string.home_banner_h_01),
                                textSize = 18,
                                buttonColor = Color.Yellow,
                                buttonText = stringResource(id = R.string.see_the_timeline),
                                buttonTextColor = Color.Black,
                                buttonTextSize= 16,
                                isExpand = isExpand,
                                clickLogic = {
                                    coroutineScope.launch {
                                        launch {
                                            buttonSound.start()
                                        }
                                        delay(500)
                                        uriHandler.openUri("https://mario.nintendo.com/history/")
                                    }
                                }
                            )

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Row{
                            Spacer(modifier = Modifier.weight(1f))

                            CardPattern(
                                boxWidth = 280,
                                boxHeight = 420,
                                imageDisplay = R.drawable.mario_and_friends,
                                imgOffsetX = 0,
                                imgOffsetY = -200,
                                headline = stringResource(id = R.string.home_banner_h_02),
                                textSize = 16,
                                buttonColor = Color.Yellow,
                                buttonText = stringResource(id = R.string.meet_the_characters),
                                buttonTextColor = Color.Black,
                                buttonTextSize= 12,
                                isExpand = isExpand,
                                clickLogic = {
                                    coroutineScope.launch {
                                        launch {
                                            buttonSound.start()
                                        }
                                        delay(500)
                                        navController.navigate(MarioScreen.ListCharacterScreen.route)
                                    }
                                }
                            )

                            Spacer(modifier = Modifier.width(20.dp))
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        Row{
                            Spacer(modifier = Modifier.width(20.dp))

                            CardPattern(
                                boxWidth = 280,
                                boxHeight = 350,
                                imageDisplay = R.drawable.play_nintendo,
                                imgOffsetX = 0,
                                imgOffsetY = -180,
                                headline = stringResource(id = R.string.home_banner_h_03),
                                textSize = 18,
                                buttonColor = Color.Yellow,
                                buttonText = stringResource(id = R.string.lets_play),
                                buttonTextColor = Color.Black,
                                buttonTextSize= 12,
                                isExpand = isExpand,
                                clickLogic = {
                                    coroutineScope.launch {
                                        launch {
                                            buttonSound.start()
                                        }
                                        delay(500)
                                        uriHandler.openUri("https://play.nintendo.com/themes/friends/mario/")
                                    }
                                }
                            )

                            Spacer(modifier = Modifier.weight(1f))
                        }

                    }

                    CharacterCardSlider(viewModel, navController, isExpand)

                    Spacer(modifier = Modifier.height(if (isExpand) 700.dp else 50.dp))

                    Box {
                        TrapezoidPatternBackground(
                            backgroundColor = Color.DarkGray,
                            lineColor = Color.Black.copy(.3f),
                            stepValue = 20,
                            modifier = Modifier
                                .height(120.dp)
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.footer_characters),
                            contentDescription = "All characters",
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset {
                                IntOffset(0, -150)
                            }
                    ) {
                        TrapezoidBackground(
                            backgroundColor = MarioRedDark,
                            lineColor = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                        )
                    }

                    FooterSection(
                        modifier = Modifier
                            .offset {
                                IntOffset(0, -150)
                            },
                        uriHandler = uriHandler,
                        isExpand = isExpand
                    )
                }
            }
        }
    }
}

@Composable
private fun TopSection(
    scrollPos: Int,
    parallaxLimit: Int,
    isMute: MutableState<Boolean>,
    bgmSound: MediaPlayer,
    isExpand: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isExpand) 750.dp else 350.dp)
            .padding(top = 30.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.header_luigi),
            contentDescription = "Header Luigi",
            modifier = Modifier
                .size(if (isExpand) 400.dp else 200.dp)
                .align(Alignment.CenterStart)
                .offset {
                    IntOffset(
                        if (isExpand) -40 else -80,
                        if (scrollPos < parallaxLimit) {
                            (scrollPos * 1.1).toInt()
                        } else {
                            (parallaxLimit * 1.1).toInt()
                        }
                    )
                }
        )
        Image(
            painter = painterResource(id = R.drawable.header_mario),
            contentDescription = "Header Mario",
            modifier = Modifier
                .size(if (isExpand) 900.dp else 300.dp)
                .align(Alignment.TopCenter)
                .offset {
                    IntOffset(
                        if (isExpand) -20 else -40,
                        if (scrollPos < parallaxLimit) {
                            -50 + (scrollPos * 1.2).toInt()
                        } else {
                            -50 + (parallaxLimit * 1.2).toInt()
                        }
                    )
                }
        )

        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
                .offset {
                    IntOffset(
                        -40,
                        if (scrollPos < parallaxLimit) {
                            -50 + (scrollPos * 1.3).toInt()
                        } else {
                            -50 + (parallaxLimit * 1.3).toInt()
                        }
                    )
                },
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
            Icon(
                imageVector = if(isMute.value) { Icons.Default.VolumeMute } else { Icons.Default.VolumeUp },
                contentDescription = "Back button",
                tint = Color.White
            )
        }

        TrapezoidPatternBackground(
            backgroundColor = Color.DarkGray,
            lineColor = Color.Black.copy(.3f),
            stepValue = 20,
            modifier = Modifier
                .height(if (isExpand) 200.dp else 100.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )

        TitleWithIcon(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = stringResource(id = R.string.mario_in_the_news),
            textColor = Color.White,
            iconImage = R.drawable.icon_01,
            isExpand = isExpand
        )
    }
}

@Composable
private fun FooterSection(
    modifier: Modifier = Modifier,
    uriHandler: UriHandler,
    isExpand: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MarioRedDark)
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.weight(if(isExpand) 0.7f else 0.5f)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.follow_nintendo),
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = if(isExpand) 40.sp else 20.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                uriHandler.openUri("https://www.facebook.com/NintendoAmerica")
                            },
                        painter = painterResource(id = R.drawable.icon_sm_fb),
                        contentDescription = "icon social media"
                    )

                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                uriHandler.openUri("https://twitter.com/NintendoAmerica")
                            },
                        painter = painterResource(id = R.drawable.icon_sm_tw),
                        contentDescription = "icon social media"
                    )

                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                uriHandler.openUri("https://www.youtube.com/nintendo")
                            },
                        painter = painterResource(id = R.drawable.icon_sm_yt),
                        contentDescription = "icon social media"
                    )

                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                uriHandler.openUri("https://www.instagram.com/nintendoamerica/")
                            },
                        painter = painterResource(id = R.drawable.icon_sm_ig),
                        contentDescription = "icon social media"
                    )
                }
            }

            Row(
                modifier = Modifier.weight(if(isExpand) 0.7f else 0.5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.en_e_e10),
                    contentDescription = "Logo everyone to everyone 10+",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .weight(if(isExpand) 0.3f else 0.5f)
                        .padding(10.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.esrb_privacy_certified),
                    contentDescription = "Logo esrb privacy certified",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(if(isExpand) 0.7f else 0.5f)
                        .padding(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(if(isExpand) 200.dp else 100.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.copyright),
            style = TextStyle(
                fontSize = if(isExpand) 20.sp else 10.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(if(isExpand) 20.dp else 10.dp))
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CharacterCardSlider(
    viewModel: MainViewModel,
    navController: NavController,
    isExpand: Boolean
) {
    Column {
        Text(
            text = stringResource(id = R.string.characters),
            style = TextStyle(
                fontFamily = SuperMarioFont,
                fontSize = if(isExpand) 72.sp else 36.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )

        HorizontalPager(
            count = viewModel.getAllCharacters().size,
            contentPadding = PaddingValues(horizontal = if(isExpand) 84.dp else 42.dp),
            modifier = Modifier.fillMaxSize()
        ) { page ->
            ItemCharacterCard(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        // We animate the scaleX + scaleY, between 85% and 100%
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .fillMaxWidth(),
                character = viewModel.getAllCharacters()[page],
                navController = navController,
                widthCard = 250,
                isExpand = isExpand
            )
        }
    }
}

@Composable
private fun OnLifecycle(
    buttonSound: MediaPlayer,
    bgmSound: MediaPlayer,
    currentPos: MutableState<Int>,
    isMute: MutableState<Boolean>
) {
    OnLifecycleEvent { _, event ->
        // do stuff on event
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                buttonSound.isLooping = false
                bgmSound.isLooping = true

                if(isMute.value) {
                    bgmSound.setVolume(0.0f, 0.0f)
                }else{
                    bgmSound.setVolume(1.0f, 1.0f)
                }

                if (currentPos.value != 0) {
                    bgmSound.seekTo(currentPos.value)
                }
                buttonSound.seekTo(0)
                bgmSound.start()
            }
            Lifecycle.Event.ON_PAUSE -> {
                currentPos.value = bgmSound.currentPosition
                bgmSound.pause()
            }
            Lifecycle.Event.ON_DESTROY -> {
                currentPos.value = 0

                bgmSound.stop()
                bgmSound.release()
            }
            else -> {}
        }
    }
}