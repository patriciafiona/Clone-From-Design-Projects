package com.patriciafiona.plantyshop.navigation

import com.patriciafiona.plantyshop.R

sealed class BottomNavigationItem(var route: String, var icon: Int, var selectedIcon: Int, var title: String) {
    object Home : BottomNavigationItem("home_tab", R.drawable.ic_house, R.drawable.ic_house_green,"Home")
    object Plant : BottomNavigationItem("plant_tab", R.drawable.ic_tree, R.drawable.ic_tree_green,"Plant")
    object Scan : BottomNavigationItem("scan_tab", R.drawable.ic_qr_code_scan, R.drawable.ic_qr_code_scan_green, "Scan")
    object Cart : BottomNavigationItem("cart_tab", R.drawable.ic_shopping, R.drawable.ic_shopping_green, "Cart")
    object Setting : BottomNavigationItem("setting_tab", R.drawable.ic_settings, R.drawable.ic_settings_green,"Setting")
}