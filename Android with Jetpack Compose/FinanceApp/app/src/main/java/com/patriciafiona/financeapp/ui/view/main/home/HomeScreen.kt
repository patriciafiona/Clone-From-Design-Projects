package com.patriciafiona.financeapp.ui.view.main.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.input.key.Key.Companion.D
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patriciafiona.financeapp.R
import com.patriciafiona.financeapp.data.DataStore.recentSend
import com.patriciafiona.financeapp.data.DataStore.transactionsHistory
import com.patriciafiona.financeapp.data.entities.Transaction
import com.patriciafiona.financeapp.data.entities.TransactionType
import com.patriciafiona.financeapp.ui.theme.BlackOlive
import com.patriciafiona.financeapp.ui.theme.DarkGrey
import com.patriciafiona.financeapp.ui.theme.FinanceAppTheme
import com.patriciafiona.financeapp.ui.theme.Green
import com.patriciafiona.financeapp.ui.theme.LightGray
import com.patriciafiona.financeapp.ui.theme.RoyalBlue
import com.patriciafiona.financeapp.ui.widget.CircleImage
import com.patriciafiona.financeapp.ui.widget.CircleImageIcon
import com.patriciafiona.financeapp.ui.widget.FooterText
import com.patriciafiona.financeapp.ui.widget.ItemTransaction
import com.patriciafiona.financeapp.ui.widget.RoundedButtonLeftIcon
import com.patriciafiona.financeapp.ui.widget.RoundedButtonRightIcon
import com.patriciafiona.financeapp.utils.DateFormater
import com.patriciafiona.financeapp.utils.getThisYear
import java.util.Locale

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
    ) {
        Box {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(bottomStartPercent = 20, bottomEndPercent = 20))
                        .background(DarkGrey)
                        .safeContentPadding()
                ) {
                    TopBar()
                }
                Spacer(modifier = Modifier.height(130.dp))
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth(.9f)
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
                    ) {
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
                                icon = R.drawable.ic_copy,
                                innerPadding = 3
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 10.dp, bottom = 60.dp, start = 10.dp, end = 10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RecentSend()
            Spacer(modifier = Modifier.height(18.dp))
            TransactionHistories()
            Spacer(modifier = Modifier.height(20.dp))
            FooterText()
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
private fun TransactionHistories() {
    Card(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .clip(RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row {
                Text(
                    text = "Transactions",
                    style = TextStyle(
                        fontSize = 20.sp,
                    )
                )
                Spacer(modifier = Modifier.weight(1f))

                RoundedButtonRightIcon(
                    onClickLogic = {},
                    text = "View All",
                    icon = R.drawable.ic_arrow_right_alt,
                    bgColor = RoyalBlue.copy(alpha = .2f),
                    textColor = RoyalBlue,
                    innerPadding = 3
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
            ) {
                items(transactionsHistory) { transaction ->
                    ItemTransaction(transaction)
                }
            }
        }
    }
}

@Composable
private fun RecentSend() {
    Card(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .clip(RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row {
                Text(
                    text = "Recent Send",
                    style = TextStyle(
                        fontSize = 20.sp,
                    )
                )
                Spacer(modifier = Modifier.weight(1f))

                RoundedButtonRightIcon(
                    onClickLogic = {},
                    text = "View All",
                    icon = R.drawable.ic_arrow_right_alt,
                    bgColor = RoyalBlue.copy(alpha = .2f),
                    textColor = RoyalBlue,
                    innerPadding = 3
                )
            }

            LazyRow {
                items(recentSend) { user ->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircleImage(
                            img = user.profilePicture,
                            desc = "avatar",
                            size = 50,
                            padding = 8
                        )
                        Text(
                            text = user.firstName,
                            style = TextStyle(
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
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
        CircleImage(
            img = R.drawable.avatar,
            desc = "avatar",
            size = 50
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

        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    FinanceAppTheme {
        HomeScreen()
    }
}