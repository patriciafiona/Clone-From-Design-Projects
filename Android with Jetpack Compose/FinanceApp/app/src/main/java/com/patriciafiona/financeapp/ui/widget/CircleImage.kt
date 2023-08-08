package com.patriciafiona.financeapp.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.patriciafiona.financeapp.R

@Composable
fun CircleImage(
    img: Int,
    desc: String = "image",
    size: Int,
    padding: Int = 12
){
    Image(
        painter = painterResource(img),
        contentDescription = desc,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(padding.dp)
            .size(size.dp)
            .clip(CircleShape)
    )
}