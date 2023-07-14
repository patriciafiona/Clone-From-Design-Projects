package com.patriciafiona.marioworld.data

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItemContent(
    val menuType: MenuType,
    val icon: ImageVector,
    val text: String
)

enum class MenuType {
    News, Banner, Characters
}