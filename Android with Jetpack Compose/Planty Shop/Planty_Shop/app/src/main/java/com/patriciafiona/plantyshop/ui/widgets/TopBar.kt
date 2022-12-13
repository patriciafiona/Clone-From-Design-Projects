package com.patriciafiona.plantyshop.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patriciafiona.plantyshop.R
import com.patriciafiona.plantyshop.ui.theme.Montserrat
import com.patriciafiona.plantyshop.ui.theme.lightGreen02

@Composable
fun TopBar(
    searchInput: MutableState<String>
) {
    val userName = "Taylor Swift"

    Column {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(R.drawable.user),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .shadow(5.dp, CircleShape, true)

                )
            }

            Column(
                modifier = Modifier
                    .weight(5f)
            ) {
                Text(
                    "Hello",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontFamily = Montserrat
                    )
                )
                Text(
                    userName,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = lightGreen02,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Montserrat
                    )
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu button"
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp), clip = true)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
                TextInput(
                    modifier = Modifier
                        .weight(1f),
                    inputValue = searchInput,
                    placeholder = "Try \'Succulent\'",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Mic,
                        contentDescription = "Mic Button",
                        tint = Color.Gray,
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                }
            }
        }
    }
}