package com.patriciafiona.marioworld.ui.screen.listCharacters

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.patriciafiona.marioworld.R
import com.patriciafiona.marioworld.navigation.MarioScreen
import com.patriciafiona.marioworld.ui.screen.main.MainViewModel
import com.patriciafiona.marioworld.ui.theme.*
import com.patriciafiona.marioworld.ui.widget.*
import com.patriciafiona.marioworld.utils.setNavigationBarColor
import com.patriciafiona.marioworld.utils.setStatusBarColor
import kotlin.math.absoluteValue

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListCharacters(
    navController: NavController
) {
    val uriHandler = LocalUriHandler.current
    val viewModel = MainViewModel()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    val scrollState = rememberScrollState()
    val scrollPos = scrollState.value

    //Set Navigation Bar color
    setNavigationBarColor(color =
        if(scrollPos < 200){
            MarioRed
        }else if (scrollPos in 200..910){
            Color.White
        }else{
            BgGreenGrass
        }
    )

    setStatusBarColor(color = BgOrange)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            BgOrange, MarioRed
                        )
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.pattern_logos_characters),
                contentDescription = "Pattern Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(.5f)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
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

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Column (
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.list_of),
                            style = TextStyle(
                                fontFamily = SuperMarioFont,
                                fontSize = 32.sp,
                                color = Color.White,
                            )
                        )

                        Text(
                            text = stringResource(id = R.string.characters),
                            style = TextStyle(
                                fontFamily = SuperMarioFont,
                                fontSize = 24.sp,
                                color = Color.White,
                            )
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.goomba),
                        contentDescription = "Goomba",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .weight(.7f)
                            .padding(10.dp)
                    )
                }

                Text(
                    text = stringResource(id = R.string.list_of_characters_headline),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.White,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .padding(20.dp)
                ) {
                    items(viewModel.getAllCharacters()) { character ->
                        ItemCharacterCard(
                            modifier = Modifier
                                .fillMaxWidth(),
                            character = character,
                            navController = navController,
                            widthCard = screenWidth - 40
                        )
                    }
                }

                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val image = ImageBitmap.imageResource(R.drawable.grass)
                    val brush = remember(image) { ShaderBrush(ImageShader(image, TileMode.Repeated, TileMode.Repeated)) }

                    WaveTopBackground(
                        backgroundColor = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .align(Alignment.BottomCenter)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color.White)
                            .align(Alignment.BottomCenter)
                    )

                    CardPattern(
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.CenterStart),
                        boxWidth = (screenWidth * 0.7 - 30).toInt(),
                        boxHeight = 400,
                        imageDisplay = R.drawable.question_block,
                        imgOffsetX = 0,
                        imgOffsetY = 0,
                        headline = stringResource(id = R.string.quiz_headline),
                        textSize = 14,
                        buttonColor = Color.Yellow,
                        buttonText = stringResource(id = R.string.take_the_quiz),
                        buttonTextColor = Color.Black,
                        buttonTextSize= 12,
                        clickLogic = {
                            uriHandler.openUri("https://mario.nintendo.com/quiz/")
                        }
                    )

                    Image(
                        painter = painterResource(id = R.drawable.mario_flag),
                        contentDescription = "Mario with Flag image",
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .align(Alignment.BottomEnd)
                            .offset {
                                IntOffset(30, 0)
                            }
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(brush)
                            .align(Alignment.BottomCenter)
                    )
                }

                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BgGreenGrass)
                        .padding(10.dp)
                ){
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
        }
    }
}