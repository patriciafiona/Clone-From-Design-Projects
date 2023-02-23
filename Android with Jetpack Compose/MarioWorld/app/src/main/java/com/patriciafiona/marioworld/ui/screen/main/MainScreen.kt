package com.patriciafiona.marioworld.ui.screen.main

import android.graphics.Paint
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.marioworld.R
import com.patriciafiona.marioworld.ui.widget.*
import com.patriciafiona.marioworld.utils.BackPress
import com.patriciafiona.marioworld.utils.setStatusBarColor
import kotlinx.coroutines.delay
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.patriciafiona.marioworld.ui.theme.*
import com.patriciafiona.marioworld.utils.setNavigationBarColor
import kotlin.math.absoluteValue

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val viewModel = MainViewModel()

    val isLoading = remember{ mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val scrollPos = scrollState.value
    val parallaxLimit = 750

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
        if(scrollPos > 1650){
            MarioRedDark
        }else{
            color02.value
        }
    )

    //Animate color based on scroll position
    if(scrollPos in 0..200) {
        LaunchedEffect(key1 = true) {
            color01.animateTo(BgBlue, animationSpec = tween(1000))
            color02.animateTo(BgPurple, animationSpec = tween(1000))
        }
    }else if(scrollPos > 200) {
        LaunchedEffect(key1 = true) {
            color01.animateTo(BgGreen, animationSpec = tween(1000))
            color02.animateTo(BgTosca, animationSpec = tween(1000))
        }
    }

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

            if(scrollPos > 2200) {
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
                    TopSection(scrollPos, parallaxLimit)

                    Box {
                        BoxPatternBackground(
                            backgroundColor = Color.DarkGray,
                            lineColor = Color.Black.copy(.3f),
                            stepValue = 20,
                            modifier = Modifier
                                .height(220.dp)
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                        )

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp),
                        ) {
                            items(viewModel.getAllNews()) { news ->
                                ItemNews(news = news)
                            }
                        }
                    }

                    Box {
                        TrapezoidPatternBackground(
                            backgroundColor = Color.DarkGray,
                            lineColor = Color.Black.copy(.3f),
                            stepValue = 20,
                            modifier = Modifier
                                .height(140.dp)
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
                                textSize = 16,
                                text = stringResource(id = R.string.see_more_news),
                                icon = Icons.Default.ArrowForwardIos,
                                clickLogic = { }
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    characterCardSlider(viewModel)

                    Spacer(modifier = Modifier.height(200.dp))

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
                        uriHandler = uriHandler
                    )
                }
            }
        }
    }
}

@Composable
private fun TopSection(scrollPos: Int, parallaxLimit: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(top = 30.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.header_luigi),
            contentDescription = "Header Luigi",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopStart)
                .offset {
                    IntOffset(
                        -80,
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
                .size(300.dp)
                .align(Alignment.TopCenter)
                .offset {
                    IntOffset(
                        -40,
                        if (scrollPos < parallaxLimit) {
                            -50 + (scrollPos * 1.2).toInt()
                        } else {
                            -50 + (parallaxLimit * 1.2).toInt()
                        }
                    )
                }
        )

        TrapezoidPatternBackground(
            backgroundColor = Color.DarkGray,
            lineColor = Color.Black.copy(.3f),
            stepValue = 20,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )

        TitleWithIcon(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = stringResource(id = R.string.mario_in_the_news),
            textColor = Color.White,
            iconImage = R.drawable.icon_01
        )
    }
}

@Composable
private fun FooterSection(
    modifier: Modifier = Modifier,
    uriHandler: UriHandler
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
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.follow_nintendo),
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
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
                modifier = Modifier.weight(0.5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.en_e_e10),
                    contentDescription = "Logo everyone to everyone 10+",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(10.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.esrb_privacy_certified),
                    contentDescription = "Logo esrb privacy certified",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.copyright),
            style = TextStyle(
                fontSize = 10.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun characterCardSlider(viewModel: MainViewModel) {
    Column {
        Text(
            text = stringResource(id = R.string.characters),
            style = TextStyle(
                fontFamily = SuperMarioFont,
                fontSize = 36.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )

        HorizontalPager(
            count = viewModel.getAllCharacters().size,
            contentPadding = PaddingValues(horizontal = 32.dp),
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
                character = viewModel.getAllCharacters()[page]
            )
        }
    }
}