package com.patriciafiona.plantyshop.ui.screens.main

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.plantyshop.R
import com.patriciafiona.plantyshop.data.entity_and_enum.Explore
import com.patriciafiona.plantyshop.ui.theme.Montserrat
import com.patriciafiona.plantyshop.ui.theme.lightGray01
import com.patriciafiona.plantyshop.ui.theme.lightGray02
import com.patriciafiona.plantyshop.ui.theme.lightGreen02
import com.patriciafiona.plantyshop.ui.widgets.PlantItem
import com.patriciafiona.plantyshop.ui.widgets.TextInput
import com.patriciafiona.plantyshop.ui.widgets.Title

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = MainViewModel()
){
    val searchInput = remember{ mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(lightGray02)
        ) {
            TopSection(searchInput)

            MainSection(viewModel)
        }
    }
}

@Composable
fun HomeBanner() {
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
private fun MainSection(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Title(title = "Shop")

        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(viewModel.getRecommendationShop()) { plant ->
                PlantItem(plant, viewModel.getColors())
            }
        }

        HomeBanner()

        Title(title = "Explore")

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
}

@Composable
fun ExploreItem(explore: Explore) {
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

@Composable
private fun TopSection(
    searchInput: MutableState<String>
) {
    val userName = "Taylor Swift"

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

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    val navController = rememberNavController()
    MainScreen(navController = navController)
}

