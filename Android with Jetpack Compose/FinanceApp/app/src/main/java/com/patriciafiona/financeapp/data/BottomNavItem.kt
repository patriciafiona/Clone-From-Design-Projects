package com.patriciafiona.financeapp.data

import com.patriciafiona.financeapp.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
    object Wallet: BottomNavItem("Wallet", R.drawable.ic_account_balance_wallet,"wallet")
    object Scan: BottomNavItem("Scan", R.drawable.ic_qr_code_scanner,"scan")
    object Chart: BottomNavItem("Chart", R.drawable.ic_bar_chart,"chart")
    object User: BottomNavItem("User", R.drawable.ic_person,"user")
}