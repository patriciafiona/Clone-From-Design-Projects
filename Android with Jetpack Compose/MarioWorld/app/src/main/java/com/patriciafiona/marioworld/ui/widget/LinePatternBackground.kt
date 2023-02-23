package com.patriciafiona.marioworld.ui.widget

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.patriciafiona.marioworld.ui.theme.MarioRedDark
import com.patriciafiona.marioworld.utils.drawColoredShadow
import java.util.Collections.rotate
import java.util.Collections.singleton
import kotlin.math.ceil
import kotlin.math.roundToInt

@Composable
fun TrapezoidPatternBackground(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    lineColor: Color,
    stepValue: Int = 10,
    isTop: Boolean = true
) {
    Canvas(
        modifier = modifier
            .clip(RectangleShape)
    ) {
        val step = stepValue.dp
        val angleDegrees = 45f
        val stepPx = step.toPx()
        val stepsCount = (size.width / stepPx).roundToInt()
        val actualStep = size.width / stepsCount
        val dotSize = Size(width = actualStep / 2, height = size.height * 2)

        //Crop top section
        clipPath(
            path = trianglePath(size = size, isTop = isTop),
            clipOp = ClipOp.Difference
        ) {
            drawPath(
                color = backgroundColor,
                path = if(isTop) { trapezoidPath(size = size) } else { trapezoidBottomPath(size = size) }
            )

            for (i in -30..stepsCount + 30) {
                val rect = Rect(
                    offset = Offset(x = i * actualStep, y = (size.height - dotSize.height) / 2),
                    size = dotSize,
                )
                rotate(angleDegrees, pivot = rect.center) {
                    drawRect(
                        lineColor,
                        topLeft = rect.topLeft,
                        size = rect.size,
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TrapezoidBackground(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    lineColor: Color
) {
    var canvasSize by remember { mutableStateOf(IntSize.Zero) }

    Canvas(
        modifier = modifier
            .clip(RectangleShape)
            .onGloballyPositioned { coordinates ->
                canvasSize = coordinates.size
            }
            .shadow(elevation = (-20).dp, shape = TrapezoidShape(), ambientColor = Color.DarkGray )
    ) {
        clipPath(
            path = trianglePath(size = size),
            clipOp = ClipOp.Difference,
        ) {
            drawPath(
                color = backgroundColor,
                path = trapezoidPath(size = size)
            )

            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            drawLine(
                color = lineColor,
                start = Offset(0f, size.height / 3 + 30),
                end = Offset(size.width, 0f + 30f),
                pathEffect = pathEffect
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WaveTopBackground(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
) {
    var canvasSize by remember { mutableStateOf(IntSize.Zero) }

    Canvas(
        modifier = modifier
            .clip(RectangleShape)
            .onGloballyPositioned { coordinates ->
                canvasSize = coordinates.size
            }
            .shadow(elevation = (-20).dp, shape = TrapezoidShape(), ambientColor = Color.DarkGray )
    ) {
        clipPath(
            path = waveTopPath(size = size),
            clipOp = ClipOp.Difference,
        ) {
            drawRect(
                backgroundColor
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WaveBottomBackground(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
) {
    var canvasSize by remember { mutableStateOf(IntSize.Zero) }

    Canvas(
        modifier = modifier
            .clip(RectangleShape)
            .onGloballyPositioned { coordinates ->
                canvasSize = coordinates.size
            }
            .shadow(elevation = (-20).dp, shape = TrapezoidShape(), ambientColor = Color.DarkGray )
    ) {
        clipPath(
            path = waveBottomPath(size = size),
            clipOp = ClipOp.Difference,
        ) {
            drawRect(
                backgroundColor
            )
        }
    }
}

@Composable
fun BoxPatternBackground(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    lineColor: Color,
    stepValue: Int = 10
) {
    Canvas(
        modifier = modifier
            .clip(RectangleShape)
            .background(backgroundColor)
    ) {
        val step = stepValue.dp
        val angleDegrees = 45f
        val stepPx = step.toPx()
        val stepsCount = (size.width / stepPx).roundToInt()
        val actualStep = size.width / stepsCount
        val dotSize = Size(width = actualStep / 2, height = size.height * 2)

        for (i in -30..stepsCount + 30) {
            val rect = Rect(
                offset = Offset(x = i * actualStep, y = (size.height - dotSize.height) / 2),
                size = dotSize,
            )
            rotate(angleDegrees, pivot = rect.center) {
                drawRect(
                    lineColor,
                    topLeft = rect.topLeft,
                    size = rect.size,
                )
            }
        }
    }
}

@Composable
fun BoxCardPatternBackground(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    lineColor: Color,
    stepValue: Int = 10
) {
    Canvas(
        modifier = modifier
            .clip(RectangleShape)
            .background(backgroundColor)
    ) {
        val step = stepValue.dp
        val angleDegrees = 45f
        val stepPx = step.toPx()
        val stepsCount = (size.width / stepPx).roundToInt()
        val actualStep = size.width / stepsCount
        val dotSize = Size(width = actualStep / 2, height = size.height * 2)

        for (i in -30..stepsCount + 30) {
            val rect = Rect(
                offset = Offset(x = i * actualStep, y = (size.height - dotSize.height) / 2),
                size = dotSize,
            )
            rotate(angleDegrees, pivot = rect.center) {
                drawRect(
                    lineColor,
                    topLeft = rect.topLeft,
                    size = rect.size,
                )
            }
        }

        //Draw dot in the corner
        val padding = 40f
        drawCircle(
            Color.White, radius = 10f,
            center = Offset(padding, padding)
        )
        drawCircle(
            Color.White, radius = 10f,
            center = Offset(size.width - padding, padding)
        )
        drawCircle(
            Color.White, radius = 10f,
            center = Offset(padding, size.height - padding)
        )
        drawCircle(
            Color.White, radius = 10f,
            center = Offset(size.width - padding, size.height - padding)
        )
    }
}

private fun trianglePath(size: Size, isTop: Boolean = true): Path {
    return if(isTop) {
        Path().apply {
            moveTo(size.width, 0f)
            lineTo(0f, size.height / 3)
            lineTo(0f, 0f)
        }
    }else{
        Path().apply {
            moveTo(size.width, size.height - (size.height / 3))
            lineTo(0f,  size.height)
            lineTo(size.width, size.height)
        }
    }
}

private fun trapezoidPath(size: Size): Path {
    return Path().apply {
        moveTo(size.width, 0f)
        lineTo(0f, size.height / 3)
        lineTo(0f, size.height)
        lineTo(size.width, size.height)
    }
}
private fun trapezoidBottomPath(size: Size): Path {
    return Path().apply {
        moveTo(size.width, size.height - (size.height / 3))
        lineTo(0f, size.height)
        lineTo(0f, 0f)
        lineTo(size.width, 0f)
    }
}

private fun waveTopPath(size: Size): Path {
    val period = 150f
    val amplitude = 15f

    val wavyPath = Path().apply {
        val halfPeriod = period / 2
        moveTo(x = -halfPeriod / 2, y = size.height / 3)
        repeat(ceil(size.width / halfPeriod + 1).toInt()) { i ->
            relativeQuadraticBezierTo(
                dx1 = halfPeriod / 2,
                dy1 = 2 * amplitude * (if (i % 2 == 0) 1 else -1),
                dx2 = halfPeriod,
                dy2 = -15f,
            )
        }
        lineTo(size.width, 0f)
        lineTo(0f, 0f)
    }

    return wavyPath
}

private fun waveBottomPath(size: Size): Path {
    val period = 50f
    val amplitude = 10f

    val wavyPath = Path().apply {
        val halfPeriod = period / 2
        moveTo(x = -halfPeriod / 2, y = size.height)
        repeat(ceil(size.width / halfPeriod + 1).toInt()) { i ->
            relativeQuadraticBezierTo(
                dx1 = halfPeriod / 2,
                dy1 = 2 * amplitude * (if (i % 2 == 0) 1 else -1),
                dx2 = halfPeriod,
                dy2 = -5f,
            )
        }
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
    }

    return wavyPath
}

@Preview(showBackground = true)
@Composable
fun TrapezoidTopPatternBackgroundPreview() {
    TrapezoidPatternBackground(
        backgroundColor = Color.DarkGray,
        lineColor = Color.Black.copy(.3f),
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun TrapezoidBottomPatternBackgroundPreview() {
    TrapezoidPatternBackground(
        backgroundColor = Color.DarkGray,
        lineColor = Color.Black.copy(.3f),
        modifier = Modifier
            .width(300.dp)
            .height(200.dp),
        isTop = false
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TrapezoidBackgroundPreview() {
    TrapezoidBackground(
        backgroundColor = MarioRedDark,
        lineColor = Color.White,
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun WaveTopBackgroundPreview() {
    WaveTopBackground(
        backgroundColor = MarioRedDark,
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun WaveBottomBackgroundPreview() {
    WaveBottomBackground(
        backgroundColor = MarioRedDark,
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun BoxPatternBackgroundPreview() {
    BoxPatternBackground(
        backgroundColor = Color.DarkGray,
        lineColor = Color.Black.copy(.3f),
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun BoxCardPatternBackgroundPreview() {
    BoxCardPatternBackground(
        backgroundColor = Color.DarkGray,
        lineColor = Color.Black.copy(.3f),
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
    )
}

class TrapezoidShape: Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ) = Outline.Generic(
        Path().apply {
            moveTo(size.width, -15f)
            lineTo(0f, (size.height / 3) - 15)
            lineTo(0f, size.height - 15)
            lineTo(size.width, size.height - 15)
        }
    )
}