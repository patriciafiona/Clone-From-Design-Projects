package com.patriciafiona.financeapp.ui.widget

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.patriciafiona.financeapp.ui.theme.DarkGrey
import com.patriciafiona.financeapp.utils.getThisYear

@Composable
fun FooterText() {
    Text(
        text = "© ${getThisYear()} Patricia Fiona, all rights reserved.\nMade with ❤ for a better mobile app.",
        style = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            color = Color.Gray
        )
    )
}