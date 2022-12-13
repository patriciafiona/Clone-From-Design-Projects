package com.patriciafiona.plantyshop.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.patriciafiona.plantyshop.R

@Composable
fun LottieAnimation(
    modifier: Modifier = Modifier,
    anim: Int,
    isLooping: Boolean = false
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(anim)
    )

    Box(
        modifier = modifier,
    ){
        if(isLooping) {
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }else{
            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}