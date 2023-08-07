package com.patriciafiona.marioworld.ui.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CircleRing(
    modifier: Modifier = Modifier,
    size: Int,
    color: Color,
) {
    Box(modifier = modifier
        .size(size.dp)
    ){
        Box(
            modifier = Modifier
                .size(size = size.dp)
                .border(
                    width = 70.dp,
                    color = color.copy(alpha = .3f),
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .size(size = size.dp)
                .padding(20.dp)
                .border(
                    width = 30.dp,
                    color = color.copy(alpha = .25f),
                    shape = CircleShape
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircleRingPreview() {
    CircleRing(Modifier, 100, Color.Gray)
}