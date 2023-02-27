package com.patriciafiona.marioworld.ui.widget

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoundedButtonIcon(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    textColor: Color,
    textSize: Int,
    text: String,
    icon: ImageVector,
    clickLogic: () -> Unit
) {
    Button(
        onClick = clickLogic,
        shape = CircleShape,
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        modifier = modifier
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = textColor,
                fontSize = textSize.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            imageVector = icon,
            "Button Icon",
            tint = textColor,
            modifier = Modifier.size(15.dp)
        )
    }
}