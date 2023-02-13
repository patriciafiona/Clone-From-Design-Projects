package com.patriciafiona.firewatchparallax.ui.screen.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.firewatchparallax.R
import com.patriciafiona.firewatchparallax.navigation.FirewatchScreen
import com.patriciafiona.firewatchparallax.ui.theme.AmericanBronze
import com.patriciafiona.firewatchparallax.ui.theme.FirewatchParallaxTheme
import com.patriciafiona.firewatchparallax.ui.theme.SherpaBlue
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    val scale01 = remember { Animatable(0.0f) }
    val scale02 = remember { Animatable(0.0f) }
    val scale03 = remember { Animatable(0.0f) }

    val color = remember { Animatable(AmericanBronze) }

    LaunchedEffect(key1 = true) {
        scale01.animateTo(
            targetValue = 1f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )
        delay(1000)
        scale01.animateTo(
            targetValue = 0f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )

        delay(1000)
        color.animateTo(Color.Black, animationSpec = tween(1000))
        scale02.animateTo(
            targetValue = 1f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )
        delay(1000)
        scale02.animateTo(
            targetValue = 0f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )

        delay(1000)
        color.animateTo(SherpaBlue, animationSpec = tween(1000))
        scale03.animateTo(
            targetValue = 1f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )

        delay(1000)
        navController.navigate(FirewatchScreen.HomeScreen.route) {
            popUpTo(FirewatchScreen.SplashScreen.route) {
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color.value),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.firewatch_logo),
                contentDescription = "Splash image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .align(Alignment.Center)
                    .scale(scale01.value)
            )
            Image(
                painter = painterResource(id = R.drawable.mixkit),
                contentDescription = "Splash image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .align(Alignment.Center)
                    .scale(scale02.value)
            )
            Image(
                painter = painterResource(id = R.drawable.android_jetpack_compose),
                contentDescription = "Splash image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(.7f)
                    .align(Alignment.Center)
                    .scale(scale03.value)
            )
        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun SplashScreen_Preview() {
    val navController = rememberNavController()

    FirewatchParallaxTheme {
        SplashScreen(navController = navController)
    }
}