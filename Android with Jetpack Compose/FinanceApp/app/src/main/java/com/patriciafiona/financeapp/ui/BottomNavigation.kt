package com.patriciafiona.financeapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.patriciafiona.financeapp.data.BottomNavItem
import com.patriciafiona.financeapp.ui.theme.DarkGrey
import com.patriciafiona.financeapp.ui.theme.RoyalBlue


@Composable
fun BottomNav(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Wallet,
        BottomNavItem.Scan,
        BottomNavItem.Chart,
        BottomNavItem.User
    )
    BottomNavigation(
        backgroundColor = DarkGrey,
        contentColor = RoyalBlue,
        modifier = Modifier
            .padding(bottom = 50.dp, start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(100))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                selectedContentColor = RoyalBlue,
                unselectedContentColor = Color.White,
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}