package com.patriciafiona.marioworld.ui.screen.characterDetail

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.marioworld.R
import com.patriciafiona.marioworld.data.entities.Character
import com.patriciafiona.marioworld.data.resource.DataSource
import com.patriciafiona.marioworld.ui.theme.BgGreen
import com.patriciafiona.marioworld.ui.theme.SuperMarioFont
import com.patriciafiona.marioworld.ui.widget.*
import com.patriciafiona.marioworld.utils.ContentType
import com.patriciafiona.marioworld.utils.OnLifecycleEvent
import com.patriciafiona.marioworld.utils.setNavigationBarColor
import com.patriciafiona.marioworld.utils.setStatusBarColor
import com.smarttoolfactory.ratingbar.RatingBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CharacterDetail(
    navController: NavController,
    character: Character,
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
            ContentType.SIDE_BY_SIDE
        }
        else -> {
            ContentType.NORMAL
        }
    }

    if(contentType == ContentType.NORMAL) {
        LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    val characterColor = Color(
        red = character.backgroundColor[0],
        green = character.backgroundColor[1],
        blue = character.backgroundColor[2]
    )

    //Sound effect
    val maxVolume = 0.1f
    val currentPos = rememberSaveable{ mutableIntStateOf(0) }
    val bgmSound = remember { MediaPlayer.create(context, R.raw.bgm_super_mario_bos) }
    OnLifecycle(
        bgmSound = bgmSound,
        currentPos = currentPos,
        maxVolume = maxVolume,
        isMute = isMute
    )

    setStatusBarColor(color = characterColor)
    setNavigationBarColor(color = Color.Black)

    //Character sound
    val sound01 = remember { MediaPlayer.create(context, character.characterSound[0]) }
    val sound02 = remember { MediaPlayer.create(context, character.characterSound[1]) }
    val sound03 = remember { MediaPlayer.create(context, character.characterSound[2]) }

    sound01.isLooping = false
    sound02.isLooping = false
    sound03.isLooping = false

    if (contentType == ContentType.NORMAL) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                TopSectionNormal(
                    characterColor,
                    navController,
                    screenWidth,
                    character,
                    isMute,
                    bgmSound,
                    maxVolume
                )

                ScrollContentSection(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black), character, sound01, characterColor, sound02, sound03)
            }
        }
    }else{
        //SIDE by SIDE VIEW
        val configuration = LocalConfiguration.current
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    characterColor, Color.Black
                                )
                            )
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .height(50.dp)
                    ) {
                        IconButton(
                            modifier = Modifier
                                .padding(10.dp),
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back button",
                                tint = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(
                            modifier = Modifier
                                .padding(10.dp),
                            onClick = {
                                //mute or unmute sound
                                isMute.value = !isMute.value
                                if (isMute.value) {
                                    bgmSound.setVolume(0.0f, 0.0f)
                                } else {
                                    bgmSound.setVolume(maxVolume, maxVolume)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (isMute.value) {
                                    Icons.Default.VolumeMute
                                } else {
                                    Icons.Default.VolumeUp
                                },
                                contentDescription = "Mute button",
                                tint = Color.White,
                                modifier = Modifier.size(120.dp),
                            )
                        }
                    }
                    Image(
                        painter = painterResource(id = R.drawable.pattern_logos_characters),
                        contentDescription = "Pattern Background",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(.5f)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.97f)
                            .padding(50.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.weight(1f))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(.7f)
                                    .padding(30.dp)
                                    .fillMaxHeight(.6f)
                            ) {
                                CircleRing(
                                    modifier = Modifier
                                        .align(Alignment.Center),
                                    size = screenWidth - (screenWidth * 0.2).toInt(),
                                    color = Color.LightGray
                                )

                                Image(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(20.dp)
                                        .align(Alignment.Center),
                                    painter = painterResource(
                                        id = character.imageFull ?: character.imageOpen
                                    ),
                                    contentDescription = "Character image",
                                    contentScale = ContentScale.FillHeight
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        ScrollContentSection(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 20.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            character,
                            sound01,
                            characterColor,
                            sound02,
                            sound03,
                            isLarge = true
                        )
                    }
                }
            }
            else -> {
                //Same as Normal but in Bigger Size
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    characterColor, Color.Black
                                )
                            )
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .height(50.dp)
                    ) {
                        IconButton(
                            modifier = Modifier
                                .padding(10.dp),
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back button",
                                tint = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(
                            modifier = Modifier
                                .padding(10.dp),
                            onClick = {
                                //mute or unmute sound
                                isMute.value = !isMute.value
                                if (isMute.value) {
                                    bgmSound.setVolume(0.0f, 0.0f)
                                } else {
                                    bgmSound.setVolume(maxVolume, maxVolume)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (isMute.value) {
                                    Icons.Default.VolumeMute
                                } else {
                                    Icons.Default.VolumeUp
                                },
                                contentDescription = "Mute button",
                                tint = Color.White,
                                modifier = Modifier.size(120.dp),
                            )
                        }
                    }
                    Image(
                        painter = painterResource(id = R.drawable.pattern_logos_characters),
                        contentDescription = "Pattern Background",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(.5f)
                    )

                    Column(
                        modifier = Modifier.fillMaxSize(.97f)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.weight(1f))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(.6f)
                                    .padding(30.dp)
                                    .fillMaxHeight(.4f)
                            ) {
                                CircleRing(
                                    modifier = Modifier
                                        .align(Alignment.Center),
                                    size = screenWidth - (screenWidth * 0.2).toInt(),
                                    color = Color.LightGray
                                )

                                Image(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(20.dp)
                                        .align(Alignment.Center),
                                    painter = painterResource(
                                        id = character.imageFull ?: character.imageOpen
                                    ),
                                    contentDescription = "Character image",
                                    contentScale = ContentScale.FillHeight
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        ScrollContentSection(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 20.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            character,
                            sound01,
                            characterColor,
                            sound02,
                            sound03,
                            isLarge = true
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ScrollContentSection(
    modifier: Modifier,
    character: Character,
    sound01: MediaPlayer,
    characterColor: Color,
    sound02: MediaPlayer,
    sound03: MediaPlayer,
    isLarge: Boolean = false
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 20.dp, bottom = 30.dp)
    ) {
        Text(
            text = character.name,
            style = TextStyle(
                fontFamily = SuperMarioFont,
                fontSize = if(isLarge) 72.sp else 36.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 2.dp)
        )
        Text(
            text = "${character.fullName} - ${character.species}",
            style = TextStyle(
                fontSize = if(isLarge) 24.sp else 12.sp,
                color = if(isLarge) Color.LightGray else Color.Gray,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        SoundSection(sound01, characterColor, sound02, sound03, isLarge)

        Spacer(modifier = Modifier.height(20.dp))

        TitleWithIcon(
            text = stringResource(id = R.string.character_statistic),
            textColor = Color.White,
            iconImage = R.drawable.icon_01,
            textSize = if(isLarge) 40 else 20
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
        ) {
            StatisticData(
                text = stringResource(id = R.string.acceleration),
                rating = character.ability.acceleration.toFloat() / 2,
                isLarge = isLarge
            )
            StatisticData(
                text = stringResource(id = R.string.max_speed),
                rating = character.ability.maxSpeed.toFloat() / 2,
                isLarge = isLarge
            )
            StatisticData(
                text = stringResource(id = R.string.technique),
                rating = character.ability.technique.toFloat() / 2,
                isLarge = isLarge
            )
            StatisticData(
                text = stringResource(id = R.string.power),
                rating = character.ability.power.toFloat() / 2,
                isLarge = isLarge
            )
            StatisticData(
                text = stringResource(id = R.string.stamina),
                rating = character.ability.stamina.toFloat() / 2,
                isLarge = isLarge
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        TitleWithIcon(
            text = stringResource(id = R.string.description),
            textColor = Color.White,
            iconImage = R.drawable.icon_02,
            textSize = if(isLarge) 40 else 20
        )

        ExpandableText(
            text = character.description,
            modifier = Modifier
                .padding(horizontal = 20.dp),
            minimizedMaxLines = if(isLarge) 4 else 3,
            textStyle = TextStyle(
                color = Color.LightGray,
                fontSize = if(isLarge) 32.sp else 16.sp,
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Spacer(modifier = Modifier.weight(1f))
            DialogBalloon(
                modifier = Modifier.width(if(isLarge) 600.dp else 300.dp),
                text = "\"${character.dialog}\" - ${character.name}",
                balloonColor = BgGreen,
                textColor = Color.White,
                isLarge = isLarge
            )
        }
    }
}

@Composable
fun StatisticData(
    text: String,
    rating: Float,
    isLarge: Boolean
) {
    //Star rating
    val imageForeground = ImageBitmap.imageResource(id = R.drawable.star_foreground)
    val imageBackground = ImageBitmap.imageResource(id = R.drawable.star_background)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(.4f),
            text = text,
            style = TextStyle(
                color = Color.White,
                fontSize = if(isLarge) 28.sp else 14.sp
            )
        )
        RatingBar(
            rating = rating,
            space = if(isLarge) 4.dp else 2.dp,
            imageEmpty = imageBackground,
            imageFilled = imageForeground,
            animationEnabled = true,
            gestureEnabled = false,
            itemSize = if(isLarge) 40.dp else 20.dp
        )
    }
}

@Composable
private fun TopSectionNormal(
    characterColor: Color,
    navController: NavController,
    screenWidth: Int,
    character: Character,
    isMute: MutableState<Boolean>,
    bgmSound: MediaPlayer,
    maxVolume: Float

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.4f)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        characterColor, Color.Black
                    )
                )
            )
    ) {
        Row{
            IconButton(
                modifier = Modifier
                    .padding(10.dp),
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back button",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                modifier = Modifier
                    .padding(10.dp),
                onClick = {
                    //mute or unmute sound
                    isMute.value = !isMute.value
                    if(isMute.value) {
                        bgmSound.setVolume(0.0f, 0.0f)
                    }else{
                        bgmSound.setVolume(maxVolume, maxVolume)
                    }
                }
            ) {
                Icon(
                    imageVector = if(isMute.value) { Icons.Default.VolumeMute } else { Icons.Default.VolumeUp },
                    contentDescription = "Mute button",
                    tint = Color.White
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.pattern_logos_characters),
            contentDescription = "Pattern Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(.5f)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CircleRing(
                modifier = Modifier
                    .align(Alignment.Center),
                size = screenWidth - (screenWidth * 0.2).toInt(),
                color = Color.LightGray
            )

            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp)
                    .align(Alignment.Center),
                painter = painterResource(
                    id = character.imageFull ?: character.imageOpen
                ),
                contentDescription = "Character image",
                contentScale = ContentScale.FillHeight
            )
        }
    }
}

@Composable
private fun SoundSection(
    sound01: MediaPlayer,
    characterColor: Color,
    sound02: MediaPlayer,
    sound03: MediaPlayer,
    isLarge: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(if(isLarge) .5f else .25f)
                .align(Alignment.CenterStart),
            painter = painterResource(id = R.drawable.pipe_right),
            contentDescription = "pipe image",
        )
        Spacer(modifier = Modifier.fillMaxWidth(.5f))
        Row(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    sound01.start()
                },
                modifier = Modifier.size(50.dp),  //avoid the oval shape
                shape = CircleShape,
                border = BorderStroke(3.dp, Color.White),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.buttonColors(
                    containerColor = characterColor,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "Play Sound Button"
                )
            }

            Button(
                onClick = {
                    sound02.start()
                },
                modifier = Modifier.size(50.dp),  //avoid the oval shape
                shape = CircleShape,
                border = BorderStroke(3.dp, Color.White),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.buttonColors(
                    containerColor = characterColor,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "Play Sound Button"
                )
            }

            Button(
                onClick = {
                    sound03.start()
                },
                modifier = Modifier.size(50.dp),  //avoid the oval shape
                shape = CircleShape,
                border = BorderStroke(3.dp, Color.White),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.buttonColors(
                    containerColor = characterColor,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "Play Sound Button"
                )
            }
        }

        Image(
            modifier = Modifier
                .fillMaxWidth(if(isLarge) .5f else .25f)
                .align(Alignment.CenterEnd),
            painter = painterResource(id = R.drawable.pipe_left),
            contentDescription = "pipe image"
        )
    }
}

@Composable
private fun OnLifecycle(
    bgmSound: MediaPlayer,
    currentPos: MutableState<Int>,
    maxVolume: Float,
    isMute: MutableState<Boolean>
) {
    OnLifecycleEvent { _, event ->
        // do stuff on event
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                if(isMute.value) {
                    bgmSound.setVolume(0.0f, 0.0f)
                }else{
                    bgmSound.setVolume(maxVolume, maxVolume)
                }

                bgmSound.isLooping = true

                if (currentPos.value != 0) {
                    bgmSound.seekTo(currentPos.value)
                }
                bgmSound.start()
            }
            Lifecycle.Event.ON_PAUSE -> {
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

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview
@Composable
fun CharacterDetailPreview(){
    val navController: NavController = rememberNavController()
    val character: Character = DataSource.characters()[0]
    val isMute: MutableState<Boolean> = remember { mutableStateOf(false) }
    val windowSize: WindowWidthSizeClass = calculateWindowSizeClass(LocalContext.current as Activity).widthSizeClass

    CharacterDetail(
        navController = navController,
        character = character,
        isMute = isMute,
        windowSize = windowSize
    )
}