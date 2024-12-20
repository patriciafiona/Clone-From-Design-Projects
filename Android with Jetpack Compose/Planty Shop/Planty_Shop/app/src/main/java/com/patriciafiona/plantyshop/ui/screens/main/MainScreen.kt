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
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.plantyshop.R
import com.patriciafiona.plantyshop.data.entity_and_enum.Explore
import com.patriciafiona.plantyshop.navigation.BottomNavigationBuilder
import com.patriciafiona.plantyshop.navigation.BottomNavigationItem
import com.patriciafiona.plantyshop.navigation.PlantScreen
import com.patriciafiona.plantyshop.ui.theme.Montserrat
import com.patriciafiona.plantyshop.ui.theme.lightGray01
import com.patriciafiona.plantyshop.ui.theme.lightGray02
import com.patriciafiona.plantyshop.ui.theme.lightGreen02
import com.patriciafiona.plantyshop.ui.widgets.*
import java.io.File

@Composable
fun MainScreen(
    navController: NavController,
    getDirectory: File
){
    val searchInput = remember{ mutableStateOf("") }
    val bottomNavController = rememberNavController()

    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if(currentRoute == BottomNavigationItem.Home.route || currentRoute == BottomNavigationItem.Plant.route) {
                TopBar(searchInput)
            }
        },
        bottomBar = { if(currentRoute != BottomNavigationItem.Scan.route) { BottomNavigationBar(navController = bottomNavController )} },
        content = { padding ->
            Column(modifier = Modifier
                .padding(padding)
            ) {
                BottomNavigationBuilder(
                    bottomNavigationController = bottomNavController,
                    navController = navController,
                    getDirectory = getDirectory
                )
            }
        },
        backgroundColor = Color.White
    )
}

