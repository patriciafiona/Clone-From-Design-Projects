package com.patriciafiona.plantyshop.ui.screens.main.bottomNavigation.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.plantyshop.R
import com.patriciafiona.plantyshop.data.entity_and_enum.Explore
import com.patriciafiona.plantyshop.navigation.BottomNavigationItem
import com.patriciafiona.plantyshop.ui.screens.main.MainViewModel
import com.patriciafiona.plantyshop.ui.theme.Montserrat
import com.patriciafiona.plantyshop.ui.theme.lightGray02
import com.patriciafiona.plantyshop.ui.theme.lightGreen02
import com.patriciafiona.plantyshop.ui.widgets.PlantItem
import com.patriciafiona.plantyshop.ui.widgets.Title

@Composable
fun HomeTab(
    navController: NavController,
    bottomNavController: NavController,
    viewModel: MainViewModel = MainViewModel()
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(lightGray02)
    ) {
        MainSection(viewModel, navController, bottomNavController)
    }
}

@Composable
private fun MainSection(
    viewModel: MainViewModel,
    navController: NavController,
    bottomNavController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Title(title = "Shop")
        ShopList(viewModel, navController = navController, bottomNavController = bottomNavController)

        HomeBanner()

        Title(title = "Explore")
        ExploreList(viewModel)
    }
}

@Composable
private fun ExploreList(viewModel: MainViewModel) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .height(500.dp)
    ) {
        items(viewModel.getExplore()) { explore ->
            ExploreItem(explore)
        }
    }
}

@Composable
private fun ShopList(
    viewModel: MainViewModel,
    navController: NavController,
    bottomNavController: NavController
) {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(viewModel.getRecommendationShop()) { plant ->
            PlantItem(navController, plant, viewModel.getColors())
        }

        item {
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .height(270.dp)
                    .clickable {
                        // Go to Plant Tab
                        bottomNavController.navigate(BottomNavigationItem.Plant.route) {
                            bottomNavController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .width(200.dp)
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
                        )
                    }

                    Text(
                        text = "See All",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = lightGreen02
                        ),
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(150.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.home_banner_bg),
                contentDescription = "Home banner",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .width(200.dp)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Get 50% Discount Today",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = Montserrat,
                        color = Color.DarkGray,
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .height(200.dp)
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.home_banner_plant),
                contentDescription = "Banner plant",
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .offset(y = 15.dp, x = 40.dp)
                    .clipToBounds(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun ExploreItem(explore: Explore) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(explore.url)) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        backgroundColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painterResource(id = explore.image),
                contentDescription = "Explore image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clipToBounds()
            )

            Text(
                text = explore.title,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = Montserrat,
                    color = Color.DarkGray
                ),
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            )

            Text(
                text = explore.description,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    color = Color.Gray
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
            )
        }
    }
}