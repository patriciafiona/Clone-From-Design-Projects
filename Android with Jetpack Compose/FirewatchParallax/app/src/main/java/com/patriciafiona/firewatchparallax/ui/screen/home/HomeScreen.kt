package com.patriciafiona.firewatchparallax.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.firewatchparallax.R
import com.patriciafiona.firewatchparallax.ui.theme.RusticRed
import com.patriciafiona.firewatchparallax.ui.theme.VividOrange

@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val scrollPos = scrollState.value

    val parallaxLimit = 400

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(RusticRed)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(VividOrange)
        )
        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(VividOrange)
            )

           Image(
               painter = painterResource(id = R.drawable.parallax0),
               contentDescription = "parallax 0",
               modifier = Modifier.fillMaxWidth()
                   .offset {
                       IntOffset(
                           0,
                           if(scrollPos < parallaxLimit) { (scrollPos * 1.03).toInt() } else { (parallaxLimit * 1.03).toInt() }
                       )
                   }
           )

            Image(
                painter = painterResource(id = R.drawable.parallax1),
                contentDescription = "parallax 1",
                modifier = Modifier.fillMaxWidth()
                    .offset {
                        IntOffset(
                            0,
                            if(scrollPos < parallaxLimit) { (scrollPos * 1.05).toInt() } else { (parallaxLimit * 1.05).toInt() }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax2),
                contentDescription = "parallax 2",
                modifier = Modifier.fillMaxWidth()
                    .offset {
                        IntOffset(
                            0,
                            if(scrollPos < parallaxLimit) { (scrollPos * 1.08).toInt() } else { (parallaxLimit * 1.08).toInt() }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax3),
                contentDescription = "parallax 3",
                modifier = Modifier.fillMaxWidth()
                    .offset {
                        IntOffset(
                            0,
                            if(scrollPos < parallaxLimit) { (scrollPos * 1.2).toInt() } else { (parallaxLimit * 1.2).toInt() }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax4),
                contentDescription = "parallax 4",
                modifier = Modifier.fillMaxWidth()
                    .offset {
                        IntOffset(
                            0,
                            if(scrollPos < parallaxLimit) { (scrollPos * 1.3).toInt() } else { (parallaxLimit * 1.3).toInt() }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax5),
                contentDescription = "parallax 5",
                modifier = Modifier.fillMaxWidth()
                    .offset {
                        IntOffset(
                            0,
                            if(scrollPos < parallaxLimit) { (scrollPos * 1.4).toInt() } else { (parallaxLimit * 1.4).toInt() }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax6),
                contentDescription = "parallax 6",
                modifier = Modifier.fillMaxWidth()
                    .offset {
                        IntOffset(
                            0,
                            if(scrollPos < parallaxLimit) { (scrollPos * 1.5).toInt() } else { (parallaxLimit * 1.5).toInt() }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax7),
                contentDescription = "parallax 7",
                modifier = Modifier.fillMaxWidth()
                    .offset {
                        IntOffset(
                            0,
                            if(scrollPos < parallaxLimit) { (scrollPos * 1.6).toInt() } else { (parallaxLimit * 1.6).toInt() }
                        )
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.parallax8),
                contentDescription = "parallax 8",
                modifier = Modifier.fillMaxWidth()
                    .offset {
                        IntOffset(
                            0,
                            if(scrollPos < parallaxLimit) { (scrollPos * 1.7).toInt() } else { (parallaxLimit * 1.7).toInt() }
                        )
                    }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1000.dp)
                .offset {
                    IntOffset(0, -300)
                }
        )
    }
}