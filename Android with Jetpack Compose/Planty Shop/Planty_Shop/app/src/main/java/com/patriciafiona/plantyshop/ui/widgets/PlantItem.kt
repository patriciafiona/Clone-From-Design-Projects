package com.patriciafiona.plantyshop.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.patriciafiona.plantyshop.data.entity_and_enum.Plant
import com.patriciafiona.plantyshop.ui.theme.lightGray02


@Composable
fun PlantItem(
    plant: Plant
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
                Image(
                    painter = painterResource(id = plant.image),
                    contentDescription = "Plant image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(300.dp)
                        .align(alignment = Alignment.CenterEnd)
                        .offset(y = 0.dp, x = 60.dp)
                        .clipToBounds(),
                )
            }
        }
    }
}