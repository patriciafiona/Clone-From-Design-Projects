package com.patriciafiona.financeapp.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = color
    )
}

@Composable
fun SetNavigationBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setNavigationBarColor(
        color = color
    )
}

@Composable
fun SetSystemBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = color
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Modifier.drawColoredShadow(
    color: Color,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = this.drawBehind {
    val transparentColor = android.graphics.Color.toArgb(color.copy(alpha = 0.0f).value.toLong())
    val shadowColor = android.graphics.Color.toArgb(color.copy(alpha = alpha).value.toLong())
    this.drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            borderRadius.toPx(),
            borderRadius.toPx(),
            paint
        )
    }
}

@SuppressLint("SimpleDateFormat")
fun getCurrentDateTime(): String {
    val calendar: Calendar = Calendar.getInstance()
    val sdf = SimpleDateFormat("MM/dd/yyyy")
    return sdf.format(calendar.time)
}

fun getThisYear(): String {
    val cal: Calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"))
    return cal[Calendar.YEAR].toString()
}

@SuppressLint("SimpleDateFormat")
fun DateFormater(date: String): String {
    val firstApiFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val d = LocalDate.parse(date , firstApiFormat)
    return SimpleDateFormat("MMM, dd yyyy")
        .format(
            Date.from(d.atStartOfDay(ZoneId.of("Asia/Bangkok")).toInstant())
        )
}