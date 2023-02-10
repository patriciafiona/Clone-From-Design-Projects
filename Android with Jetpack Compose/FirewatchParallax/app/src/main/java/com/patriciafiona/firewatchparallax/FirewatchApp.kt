package com.patriciafiona.firewatchparallax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.patriciafiona.firewatchparallax.navigation.NavigationBuilder
import com.patriciafiona.firewatchparallax.ui.theme.FirewatchParallaxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirewatchParallaxTheme {
                with(WindowCompat.getInsetsController(window, window.decorView)) {
                    systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    hide(WindowInsetsCompat.Type.systemBars())
                }

                NavigationBuilder()
            }
        }
    }
}