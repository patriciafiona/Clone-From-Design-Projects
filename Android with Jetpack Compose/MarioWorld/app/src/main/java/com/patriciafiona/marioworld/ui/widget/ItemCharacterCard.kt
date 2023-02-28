package com.patriciafiona.marioworld.ui.widget

import android.media.MediaPlayer
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.marioworld.R
import com.patriciafiona.marioworld.data.entities.Character
import com.patriciafiona.marioworld.navigation.MarioScreen
import com.patriciafiona.marioworld.ui.theme.MarioRed
import com.patriciafiona.marioworld.ui.theme.SuperMarioFont
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ItemCharacterCard(
    modifier: Modifier = Modifier,
    character: Character,
    navController: NavController,
    widthCard: Int
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var expanded by rememberSaveable { mutableStateOf(false) }

    val buttonSound = remember { MediaPlayer.create(context, R.raw.pause) }
    val slideDown = remember { MediaPlayer.create(context, R.raw.slide_down) }
    val slideUp = remember { MediaPlayer.create(context, R.raw.slide_up) }

    Card(
        modifier = modifier
            .width(widthCard.dp)
            .padding(vertical = 10.dp, horizontal = 5.dp),
        backgroundColor = Color(
            red = character.backgroundColor[0],
            green = character.backgroundColor[1],
            blue = character.backgroundColor[2]
        ),
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Crossfade(targetState = expanded) { expanded ->
                    Image(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(end = 10.dp)
                            .offset {
                                IntOffset(-50, 0)
                            },
                        painter = painterResource(
                            id = if (expanded) {
                                character.imageOpen
                            } else {
                                character.imageClose
                            }
                        ),
                        contentDescription = "Character image",
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .offset {
                            IntOffset(-50, 0)
                        }
                ) {
                    Text(
                        text = character.name,
                        style = androidx.compose.ui.text.TextStyle(
                            fontFamily = SuperMarioFont,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    )

                    Text(
                        text = character.description,
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 12.sp,
                            color = Color.White,
                            textAlign = TextAlign.Justify
                        ),
                        maxLines = if(expanded) { 4 } else{ 2 },
                        overflow = TextOverflow.Ellipsis
                    )

                    if(expanded) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    buttonSound.start()

                                    delay(500)

                                    navController.navigate(MarioScreen.DetailCharacterScreen.route)
                                    navController.currentBackStackEntry?.arguments?.putParcelable("character", character)
                                }

                            },
                            shape = CircleShape,
                            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 3.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                        ) {
                            Text(
                                text = "See details",
                                style = androidx.compose.ui.text.TextStyle(
                                    color = MarioRed,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
                ItemButton(
                    expanded = expanded,
                    onClick = {
                        coroutineScope.launch {
                            expanded = !expanded

                            if (expanded) {
                                slideDown.start()
                            } else {
                                slideUp.start()
                            }
                        }
                    },
                    modifier = Modifier
                        .offset {
                            IntOffset(-50, 0)
                        }
                )

            }
        }
    }
}

@Composable
private fun ItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = MaterialTheme.colors.onPrimary,
            contentDescription = stringResource(R.string.expand_button_content_description)
        )
    }
}