package aungbophyoe.github.io.basemvijc.core.uiComponents.shimmer

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

/**
 * Created by aungb on 1/22/2025.
 */

fun Modifier.shimmerEffect(
): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = -100f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
        ), label = ""
    )

    val shimmerColors = listOf(
        MaterialTheme.colorScheme.tertiary.copy(0.7f),
        MaterialTheme.colorScheme.tertiary.copy(0.9f),
        MaterialTheme.colorScheme.tertiary.copy(0.7f)
    )

    background(
        brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(translateAnim.value, 0f),
            end = Offset(translateAnim.value + size.width.toFloat(), 0f)
        )
    ).onGloballyPositioned {
        size = it.size
    }
}