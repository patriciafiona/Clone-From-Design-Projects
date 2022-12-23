package com.patriciafiona.plantyshop.ui.screens.detail

import android.content.pm.ActivityInfo
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.plantyshop.data.entity_and_enum.Plant
import com.patriciafiona.plantyshop.ui.theme.*
import com.patriciafiona.plantyshop.ui.widgets.LockScreenOrientation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    plant: Plant,
    viewModel: DetailViewModel = DetailViewModel()
){
    //Lock in portrait
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            BottomSheet(plant)
        }, sheetPeekHeight = 20.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Card(
                    modifier = Modifier
                        .width(300.dp)
                        .height(300.dp)
                        .offset(y = (-40).dp, x = (-40).dp),
                    shape = RoundedCornerShape(50.dp),
                    elevation = 10.dp
                ) {
                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .height(300.dp)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        lightGreen03,
                                        lightGreen02
                                    ),
                                    center = Offset(0F, 50F)
                                )
                            )
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(300.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Button(
                        onClick = {
                            navController.navigateUp()
                        },
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                            .size(40.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        shape = CircleShape,
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite button",
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            navController.navigateUp()
                        },
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                            .size(40.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        shape = CircleShape,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share button",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Image(
                painter = painterResource(id = plant.image),
                contentDescription = "Plant Image",
                modifier = Modifier
                    .fillMaxHeight(.6f)
                    .align(Alignment.CenterEnd)
                    .offset(y = 70.dp, x = (80).dp),
                contentScale = ContentScale.FillHeight
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TopSection(navController, plant)

                NameAndDetailSection(plant, coroutineScope, bottomSheetScaffoldState)

                Spacer(modifier = Modifier.height(50.dp))

                DetailCardsInfoSection(plant)

                Spacer(modifier = Modifier.weight(1.0f))

                ColorsSection(viewModel)

                Button(
                    onClick = { },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = lightGreen02),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Add to Cart | \$${plant.price}",
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = Montserrat,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomSheet(plant: Plant) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(300.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(.4f)
                .background(Color.LightGray)
                .height(5.dp)
        )

        Spacer(modifier = Modifier
            .height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = plant.image),
                contentDescription = "Plant Image",
                modifier = Modifier
                    .size(200.dp),
                contentScale = ContentScale.FillHeight
            )

            Text(
                text = plant.name,
                style = TextStyle(
                    fontSize = 30.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Montserrat,
                     textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            Text(
                text = "Family: ${plant.family} | Genus: ${plant.genus}",
                style = TextStyle(
                    fontFamily = Montserrat,
                    fontStyle = FontStyle.Italic,
                    color = lightGreen02,
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            Text(
                text = plant.description,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontFamily = Montserrat,
                    textAlign = TextAlign.Center
                )
            )
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun NameAndDetailSection(
    plant: Plant,
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    Column(
        modifier = Modifier
            .width(230.dp)
            .padding(8.dp)
    ) {
        Text(
            text = plant.name,
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                color = lightGray03
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = plant.description,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = Montserrat,
                color = lightGray03
            ),
            maxLines = 6,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }
                }
        )
    }
}

@Composable
private fun ColorsSection(viewModel: DetailViewModel) {
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(viewModel.getAllColors()) { color ->
            Card(
                modifier = Modifier
                    .size(42.dp)
                    .border(width = 1.dp, shape = CircleShape, color = Color.LightGray)
                    .padding(3.dp),
                elevation = 5.dp,
                backgroundColor = color,
                shape = CircleShape
            ) {}
        }
    }
}

@Composable
private fun DetailCardsInfoSection(plant: Plant) {
    Column {
        Card(
            modifier = Modifier
                .width(150.dp)
                .height(80.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                lightGray02,
                                Color.White
                            )
                        )
                    )
            ) {
                Text(
                    text = plant.category,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Montserrat
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center),
                )
            }
        }

        PlantDetailCard(
            "Size",
            "${plant.size}\"",
            Brush.verticalGradient(
                colors = listOf(
                    lightGray02,
                    Color.White
                )
            )
        )

        PlantDetailCard(
            "Height",
            "${plant.height}\"",
            Brush.verticalGradient(
                colors = listOf(
                    lightGray02,
                    Color.White
                )
            )
        )

        PlantDetailCard(
            "Light",
            plant.plant_light.name,
            Brush.verticalGradient(
                colors = listOf(
                    lightGray02,
                    Color.White
                )
            )
        )
    }
}

@Composable
private fun TopSection(
    navController: NavController,
    plant: Plant
) {
    Row(
        modifier = Modifier.width(230.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Button(
            onClick = {
                navController.navigateUp()
            },
            contentPadding = PaddingValues(),
            modifier = Modifier
                .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                .size(40.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = CircleShape,
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back button",
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = "Shop / ${plant.category} / ${plant.name}",
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = Montserrat,
                color = lightGray02
            ),
            modifier = Modifier
                .padding(horizontal = 8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun PlantDetailCard(
    title: String,
    value: String,
    brushCustom: Brush
) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
                .background(
                    brush = brushCustom
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = Montserrat,
                        color = lightGreen02
                    ),
                )
                Text(
                    text = value,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Montserrat,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 3.dp),
                )
            }
        }
    }
}