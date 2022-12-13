package com.patriciafiona.plantyshop.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.patriciafiona.plantyshop.R
import com.patriciafiona.plantyshop.navigation.BottomNavigationItem
import com.patriciafiona.plantyshop.ui.theme.lightGray03
import com.patriciafiona.plantyshop.ui.theme.lightGreen02

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Plant,
        BottomNavigationItem.Scan,
        BottomNavigationItem.Cart,
        BottomNavigationItem.Setting
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = lightGray03,
        contentColor = Color.Gray
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Image(
                        painterResource(id = if(currentRoute == item.route) { item.selectedIcon } else{ item.icon }),
                        contentDescription = item.title,
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if(currentRoute == item.route) { Color.DarkGray } else{ Color.Transparent }
                    )
                },
                selectedContentColor = lightGreen02,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = false,
                selected = true,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
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