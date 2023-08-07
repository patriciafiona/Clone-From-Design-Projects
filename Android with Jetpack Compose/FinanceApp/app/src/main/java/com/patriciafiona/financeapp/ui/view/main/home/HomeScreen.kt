package com.patriciafiona.financeapp.ui.view.main.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patriciafiona.financeapp.R
import com.patriciafiona.financeapp.ui.theme.BlackOlive
import com.patriciafiona.financeapp.ui.theme.DarkGrey
import com.patriciafiona.financeapp.ui.theme.FinanceAppTheme
import com.patriciafiona.financeapp.ui.theme.RoyalBlue
import com.patriciafiona.financeapp.ui.widget.CircleImageIcon
import com.patriciafiona.financeapp.ui.widget.RoundedButtonLeftIcon
import com.patriciafiona.financeapp.ui.widget.RoundedButtonRightIcon

@Composable
fun HomeScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        Box{
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(bottomStartPercent = 20, bottomEndPercent = 20))
                        .background(DarkGrey)
                ) {
                    TopBar()
                }
                Spacer(modifier = Modifier.height(130.dp))
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth(.85f)
                    .padding(10.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(30.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = DarkGrey,
                ),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(30.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = RoyalBlue,
                        ),
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Main Balance",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            )
                            Text(
                                text = "\$289,300",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 38.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                            RoundedButtonRightIcon(
                                onClickLogic = {},
                                text = "9123456789012",
                                icon = R.drawable.ic_copy
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    RoundedButtonLeftIcon(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        onClickLogic = {},
                        text = "Request",
                        icon = R.drawable.icon_receive
                    )

                    RoundedButtonLeftIcon(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        onClickLogic = {},
                        text = "Send",
                        icon = R.drawable.icon_send
                    )

                    CircleImageIcon(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        onClickLogic = {},
                        bgColor = BlackOlive,
                        icon = R.drawable.ic_menu
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.avatar),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(12.dp)
                .size(64.dp)
                .clip(CircleShape)
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Good Morning,",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 12.sp
                )
            )
            Text(
                text = "Patricia Fiona",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        CircleImageIcon(
            onClickLogic = {},
            bgColor = Color.Gray,
            icon = R.drawable.ic_notification
        )

        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    FinanceAppTheme {
        HomeScreen()
    }
}