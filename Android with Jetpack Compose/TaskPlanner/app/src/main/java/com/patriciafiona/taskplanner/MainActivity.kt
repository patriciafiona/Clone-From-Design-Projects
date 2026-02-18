package com.patriciafiona.taskplanner

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.patriciafiona.taskplanner.ui.screen.MainScreen
import com.patriciafiona.taskplanner.ui.theme.TaskPlannerTheme
import com.patriciafiona.taskplanner.viewmodel.TaskViewModel
import com.patriciafiona.taskplanner.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[TaskViewModel::class.java]

        setContent {
            TaskPlannerTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}
