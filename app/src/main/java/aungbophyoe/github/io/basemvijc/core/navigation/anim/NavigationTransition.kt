package aungbophyoe.github.io.basemvijc.core.navigation.anim

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

/**
 * Created by aungb on 1/22/2025.
 */

fun AnimatedContentTransitionScope<NavBackStackEntry>.screenSlideIn(): EnterTransition =
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(700)
    )

fun screenFadeOut(): ExitTransition = fadeOut(
    animationSpec = tween(durationMillis = 300),
    targetAlpha = 0.99f
)

fun screenFadeIn(): EnterTransition =  fadeIn(
    animationSpec = tween(durationMillis = 300),
    initialAlpha = 0.99f
)

fun AnimatedContentTransitionScope<NavBackStackEntry>.screenSlideOut(): ExitTransition =
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(700)
    )

val slideIntoEnterTransition : AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(700)
    )
}

val slideOutExitTransition : AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(700)
    )
}