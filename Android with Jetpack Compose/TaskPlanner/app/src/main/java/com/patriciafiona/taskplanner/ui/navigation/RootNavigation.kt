package com.patriciafiona.taskplanner.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.patriciafiona.taskplanner.ui.screen.main.MainScreen
import com.patriciafiona.taskplanner.viewmodel.TaskViewModel
import com.patriciafiona.taskplanner.viewmodel.ViewModelFactory

@Composable
fun RootNavigation() {
    val viewModel: TaskViewModel = viewModel(
        factory = ViewModelFactory.getInstance(androidx.compose.ui.platform.LocalContext.current)
    )
    MainScreen(viewModel = viewModel)
}
