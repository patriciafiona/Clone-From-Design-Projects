package com.patriciafiona.plantyshop.ui.screens.main.bottomNavigation.cart

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.plantyshop.R
import com.patriciafiona.plantyshop.ui.screens.main.MainViewModel
import com.patriciafiona.plantyshop.ui.theme.Montserrat
import com.patriciafiona.plantyshop.ui.theme.lightGray01
import com.patriciafiona.plantyshop.ui.widgets.LottieAnimation
import com.patriciafiona.plantyshop.ui.widgets.Title

@Composable
fun CartTab(
    navController: NavController,
    viewModel: MainViewModel = MainViewModel()
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(title = "My Cart")

        Spacer(modifier = Modifier.weight(1f))

        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth(),
            anim = R.raw.empty_cart,
            isLooping = true
        )

        Text(
            text = "No Product in Cart",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = Montserrat,
                color = lightGray01,
                 textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}