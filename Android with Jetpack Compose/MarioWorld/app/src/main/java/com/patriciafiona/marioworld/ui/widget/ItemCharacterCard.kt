package com.patriciafiona.marioworld.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.patriciafiona.marioworld.data.entities.Character
import com.patriciafiona.marioworld.R

@Composable
fun ItemCharacterCard(
    character: Character
) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .width(300.dp)
            .padding(vertical = 10.dp, horizontal = 5.dp),
        backgroundColor = Color(
            red = character.backgroundColor[0],
            green = character.backgroundColor[1],
            blue = character.backgroundColor[2]
        ),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.pattern_character),
                contentDescription = "Pattern image",
                contentScale = ContentScale.Crop,
                alpha = 0.3f,
            )
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = character.imageClose),
                contentDescription = "Character image",
                contentScale = ContentScale.Crop
            )
        }
    }
}