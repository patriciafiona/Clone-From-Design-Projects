package com.patriciafiona.firewatchparallax.utils

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterAnimationSlide(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        content = content,
        initiallyVisible = false
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterAnimationFadeIn(durationInMillis: Int = 1550, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(durationMillis = durationInMillis), initialAlpha = 0.3f),
        exit = fadeOut(animationSpec = tween(durationMillis = 350)),
        content = content,
        initiallyVisible = false
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterAnimationScaleIn(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = scaleIn(animationSpec = tween(durationMillis = 1550)) + expandVertically(expandFrom = Alignment.CenterVertically),
        exit = scaleOut(animationSpec = tween(durationMillis = 850)) + shrinkVertically(shrinkTowards = Alignment.CenterVertically),
        content = content,
        initiallyVisible = false
    )
}