package com.patriciafiona.plantyshop.ui.screens.main.bottomNavigation.setting

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.plantyshop.R
import com.patriciafiona.plantyshop.ui.theme.Montserrat
import com.patriciafiona.plantyshop.ui.theme.lightGray01
import com.patriciafiona.plantyshop.ui.theme.lightGreen02
import com.patriciafiona.plantyshop.ui.theme.lightGreen03
import com.patriciafiona.plantyshop.ui.widgets.LottieAnimation
import com.patriciafiona.plantyshop.ui.widgets.Title

@Composable
fun SettingTab(navController: NavController){
    val isQuickLogin =  remember{
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserProfile()

        Divider()

        Text(
            text = "Settings",
            style = TextStyle(
                color = Color.Black,
                fontSize = 22.sp,
                fontFamily = Montserrat,
                 textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            ProfileRowItem(icon = Icons.Default.List, title = "My Orders", additionalDetail = "See ongoing & history", navController = navController)
            ProfileRowItem(icon = Icons.Default.CreditCard, title = "Payment Methods", navController = navController)
            ProfileRowItem(icon = Icons.Default.HelpCenter, title = "Help center", navController = navController)
            ProfileRowItem(icon = Icons.Default.Language, title = "Change Language", navController = navController)
            ProfileRowItem(icon = Icons.Default.Bookmark, title = "Saved addresses", navController = navController)
            ProfileRowItem(icon = Icons.Default.Group, title = "Invite friends", navController = navController)
            ProfileRowItem(icon = Icons.Default.Fingerprint, title = "Quick login", isQuickLogin = isQuickLogin, navController = navController)
            ProfileRowItem(icon = Icons.Default.AccountCircle, title = "Manage accounts", navController = navController)
            ProfileRowItem(icon = Icons.Default.Security, title = "Account safety", navController = navController)
        }

        Text(
            text = "General",
            style = TextStyle(
                color = Color.Black,
                fontSize = 22.sp,
                fontFamily = Montserrat,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp)
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            ProfileRowItem(icon = Icons.Default.PrivacyTip, title = "Terms & privacy", additionalDetail = "Accepted", isShowBadge = true, navController = navController)
            ProfileRowItem(icon = Icons.Default.Smartphone, title = "Version", additionalDetail = "v 1.0.0", navController = navController)
        }
    }
}

@Composable
private fun UserProfile() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "User Photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(16.dp)
                .size(80.dp)
                .clip(CircleShape)                       // clip to the circle shape
                .border(3.dp, lightGreen03, CircleShape)
        )
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = "Taylor Swift",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = lightGreen02
                )
            )

            Text(
                text = "taylorSwift@gmail.com",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun ProfileRowItem(
    icon: ImageVector,
    title: String,
    additionalDetail: String? = null,
    isQuickLogin: MutableState<Boolean>? = null,
    isShowBadge: Boolean = false,
    navigateRoute: String? = null,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .clickable {
                if (!navigateRoute.isNullOrEmpty()) {
                    navController.navigate(navigateRoute)
                }
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(!isShowBadge){
                Icon(
                    imageVector = icon,
                    contentDescription = "Icon",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 8.dp)
                )
            }else{
                BadgedBox(
                    modifier = Modifier
                        .padding(end = 16.dp),
                    badge = {
                        Badge{}
                    }
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Icon",
                    )
                }
            }

            Text(
                title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                ),
                modifier = Modifier.weight(1f)
            )
            if(additionalDetail != null){
                Text(
                    additionalDetail,
                    style = TextStyle(
                        fontSize = 12.sp,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 3.dp)
                )
            }

            if(isQuickLogin == null) {
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "Icon Next",
                    modifier = Modifier
                        .size(15.dp)
                )
            }else{
                Switch(
                    checked = isQuickLogin.value,
                    onCheckedChange = {
                        isQuickLogin.value = !isQuickLogin.value
                    }
                )
            }
        }

        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}