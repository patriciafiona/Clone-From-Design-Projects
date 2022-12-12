package com.patriciafiona.plantyshop.ui.screens.main.bottomNavigation.plant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.plantyshop.ui.screens.main.MainViewModel
import com.patriciafiona.plantyshop.ui.theme.lightGray02
import com.patriciafiona.plantyshop.ui.widgets.PlantItem
import com.patriciafiona.plantyshop.ui.widgets.Title

@Composable
fun PlantTab(
    navController: NavController,
    viewModel: MainViewModel = MainViewModel()
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Title(title = "All Plants")
        
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .weight(1f)
                .testTag("PlantsList")
        ) {
            items(viewModel.getAllPlants()) { plant ->
                PlantItem(plant, viewModel.getColors())
            }
        }
    }
}

@Preview(
    device = Devices.PIXEL_2_XL,
    showBackground = true
)
@Composable
fun PlantTabPreview01() {
    val navController = rememberNavController()
    PlantTab(navController)
}

@Preview(
    device = Devices.NEXUS_10,
    showBackground = true
)
@Composable
fun PlantTabPreview02() {
    val navController = rememberNavController()
    PlantTab(navController)
}

@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true
)
@Composable
fun PlantTabPreview03() {
    val navController = rememberNavController()
    PlantTab(navController)
}