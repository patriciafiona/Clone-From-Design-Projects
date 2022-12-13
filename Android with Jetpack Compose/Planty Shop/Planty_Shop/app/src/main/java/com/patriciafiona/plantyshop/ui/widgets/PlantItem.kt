package com.patriciafiona.plantyshop.ui.widgets

import android.R.attr.maxLines
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.plantyshop.data.entity_and_enum.Plant
import com.patriciafiona.plantyshop.navigation.PlantScreen
import com.patriciafiona.plantyshop.ui.theme.Montserrat
import com.patriciafiona.plantyshop.ui.theme.eerieBlack
import com.patriciafiona.plantyshop.ui.theme.lightGray02


@Composable
fun PlantItem(
    navController: NavController,
    plant: Plant,
    colors: ArrayList<Color>
) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(270.dp)
            .clickable {
                navController.navigate(PlantScreen.DetailScreen.route)
                navController.currentBackStackEntry?.arguments?.putParcelable("plant", plant)
            }
    ) {
        Box(
            modifier = Modifier
                .width(200.dp)
        ) {
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(220.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = 10.dp
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
                ){
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(colors.size - 1){ index ->
                            Card(
                                modifier = Modifier
                                    .size(22.dp),
                                elevation = 5.dp,
                                backgroundColor = colors[index],
                                shape = CircleShape
                            ){}
                        }

                        item {
                            Card(
                                modifier = Modifier
                                    .size(22.dp),
                                elevation = 5.dp,
                                backgroundColor = colors[colors.size - 1],
                                shape = CircleShape
                            ){
                                Text(
                                    text = "3+",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 12.sp
                                    ),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(2.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    Image(
                        painter = painterResource(id = plant.image),
                        contentDescription = "Plant image",
                        modifier = Modifier
                            .fillMaxSize()
                            .align(alignment = Alignment.CenterEnd)
                            .offset(y = 40.dp, x = 40.dp)
                            .clipToBounds(),
                        contentScale = ContentScale.Crop,
                    )

                    Text(
                        text = "${plant.price}$",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 22.sp
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(bottom = 16.dp, start = 16.dp)
                    )

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(bottom = 16.dp, end = 16.dp)
                            .align(alignment = Alignment.BottomEnd)
                            .size(40.dp)
                            .clip(RoundedCornerShape(15.dp)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = eerieBlack),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add button",
                            modifier = Modifier.size(25.dp),
                            tint = Color.White
                        )
                    }
                }
            }
        }

        Text(
            text = plant.name,
            style = TextStyle(
                fontFamily = Montserrat,
                fontSize = 16.sp,
                color = Color.DarkGray
             ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                 .padding(start = 8.dp, top = 12.dp, end = 8.dp)
        )

        Text(
            text = plant.category,
            style = TextStyle(
                fontFamily = Montserrat,
                fontSize = 12.sp,
                color = Color.LightGray,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = 8.dp, bottom = 8.dp, end = 8.dp)
        )
    }
}