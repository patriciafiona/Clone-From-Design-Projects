package com.patriciafiona.financeapp.ui.view.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.financeapp.navigation.NavigationGraph
import com.patriciafiona.financeapp.ui.BottomNav
import com.patriciafiona.financeapp.ui.theme.DarkGrey
import com.patriciafiona.financeapp.ui.theme.LightGray
import com.patriciafiona.financeapp.utils.SetNavigationBarColor
import com.patriciafiona.financeapp.utils.SetStatusBarColor
import com.patriciafiona.financeapp.utils.SetSystemBarColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val navController = rememberNavController()

    SetStatusBarColor(color = DarkGrey)
    SetSystemBarColor(color = DarkGrey)
    SetNavigationBarColor(color = DarkGrey)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { BottomNav(navController = navController) }
    ) {
        NavigationGraph(navController = navController)
    }
}