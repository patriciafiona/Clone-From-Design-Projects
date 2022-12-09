package com.patriciafiona.plantyshop.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patriciafiona.plantyshop.data.entity_and_enum.Plant
import com.patriciafiona.plantyshop.ui.theme.eerieBlack
import com.patriciafiona.plantyshop.ui.theme.lightGray02


@Composable
fun PlantItem(
    plant: Plant,
    colors: ArrayList<Color>
) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(250.dp),
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

                Image(
                    painter = painterResource(id = plant.image),
                    contentDescription = "Plant image",
                    modifier = Modifier
                        .size(400.dp)
                        .align(alignment = Alignment.CenterEnd)
                        .offset(y = 0.dp, x = 60.dp)
                        .clipToBounds(),
                    contentScale = ContentScale.Crop,
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
}