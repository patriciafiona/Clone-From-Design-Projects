package com.patriciafiona.marioworld.ui.screen.characterDetail

import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.marioworld.R
import com.patriciafiona.marioworld.data.entities.Character
import com.patriciafiona.marioworld.ui.theme.BgGreen
import com.patriciafiona.marioworld.ui.theme.SuperMarioFont
import com.patriciafiona.marioworld.ui.widget.*
import com.patriciafiona.marioworld.utils.setNavigationBarColor
import com.patriciafiona.marioworld.utils.setStatusBarColor
import com.smarttoolfactory.ratingbar.RatingBar

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetail(
    navController: NavController,
    character: Character
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    val characterColor = Color(
        red = character.backgroundColor[0],
        green = character.backgroundColor[1],
        blue = character.backgroundColor[2]
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

    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            TopSection(characterColor, navController, screenWidth, character)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .background(Color.Black)
                    .padding(top = 20.dp, bottom = 30.dp)
            ) {
                Text(
                    text = character.name,
                    style = TextStyle(
                        fontFamily = SuperMarioFont,
                        fontSize = 36.sp,
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
                        fontSize = 12.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                SoundSection(sound01, characterColor, sound02, sound03)

                Spacer(modifier = Modifier.height(20.dp))

                TitleWithIcon(
                    text = stringResource(id = R.string.character_statistic),
                    textColor = Color.White,
                    iconImage = R.drawable.icon_01
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                ) {
                    StatisticData(
                        text = stringResource(id = R.string.acceleration),
                        rating = character.ability.acceleration.toFloat()/2
                    )
                    StatisticData(
                        text = stringResource(id = R.string.max_speed),
                        rating = character.ability.maxSpeed.toFloat()/2
                    )
                    StatisticData(
                        text = stringResource(id = R.string.technique),
                        rating = character.ability.technique.toFloat()/2
                    )
                    StatisticData(
                        text = stringResource(id = R.string.power),
                        rating = character.ability.power.toFloat()/2
                    )
                    StatisticData(
                        text = stringResource(id = R.string.stamina),
                        rating = character.ability.stamina.toFloat()/2
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                TitleWithIcon(
                    text = stringResource(id = R.string.description),
                    textColor = Color.White,
                    iconImage = R.drawable.icon_02
                )

                ExpandableText(
                    text = character.description,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    minimizedMaxLines = 3,
                    textStyle = TextStyle(
                        color = Color.LightGray,
                        fontSize = 16.sp,
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    DialogBalloon(
                        modifier = Modifier.width(300.dp),
                        text = character.dialog,
                        balloonColor = BgGreen,
                        textColor = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun StatisticData(
    text: String,
    rating: Float
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
                fontSize = 14.sp
            )
        )
        RatingBar(
            rating = rating,
            space = 2.dp,
            imageEmpty = imageBackground,
            imageFilled = imageForeground,
            animationEnabled = true,
            gestureEnabled = false,
            itemSize = 20.dp
        )
    }
}

@Composable
private fun TopSection(
    characterColor: Color,
    navController: NavController,
    screenWidth: Int,
    character: Character
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
        IconButton(
            modifier = Modifier
                .align(Alignment.TopStart)
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
    sound03: MediaPlayer
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(.25f)
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
                .fillMaxWidth(.25f)
                .align(Alignment.CenterEnd),
            painter = painterResource(id = R.drawable.pipe_left),
            contentDescription = "pipe image"
        )
    }
}