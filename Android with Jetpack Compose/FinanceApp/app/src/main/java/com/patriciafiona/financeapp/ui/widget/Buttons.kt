package com.patriciafiona.financeapp.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.patriciafiona.financeapp.R
import com.patriciafiona.financeapp.ui.theme.BlackOlive

@Composable
fun RoundedButtonLeftIcon(
    modifier: Modifier = Modifier,
    onClickLogic: () -> Unit,
    bgColor: Color = BlackOlive,
    text: String,
    icon: Int
){
    Button (
        modifier = modifier,
        onClick = onClickLogic,
        shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(
            vertical = 0.dp,
            horizontal = 8.dp
        ),  //avoid the little icon
        colors = ButtonDefaults.buttonColors(containerColor = bgColor)
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Wallet ID",
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = text,
                style = TextStyle(
                    color = Color.White,

                    )
            )
        }
    }
}

@Composable
fun RoundedButtonRightIcon(
    modifier: Modifier = Modifier,
    onClickLogic: () -> Unit,
    bgColor: Color = BlackOlive,
    textColor: Color = Color.White,
    text: String,
    icon: Int
){
    Button (
        modifier = modifier,
        onClick = onClickLogic,
        shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(
            vertical = 0.dp,
            horizontal = 8.dp
        ),  //avoid the little icon
        colors = ButtonDefaults.buttonColors(containerColor = bgColor)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = text,
                style = TextStyle(
                    color = textColor,
                    )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = icon),
                contentDescription = "Wallet ID",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun CircleImageIcon(
    modifier: Modifier = Modifier,
    onClickLogic: () -> Unit,
    bgColor: Color = BlackOlive,
    icon: Int
) {
    Button(
        onClick = onClickLogic,
        modifier = modifier.size(50.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),  //avoid the little icon
        colors = ButtonDefaults.buttonColors(containerColor = bgColor)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "Notification button",
            modifier = Modifier.size(20.dp)
        )
    }

}