package aungbophyoe.github.io.basemvijc.core.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import aungbophyoe.github.io.basemvijc.core.navigation.graphs.ScreenRoute

/**
 * Created by aungb on 1/22/2025.
 */

class NavigationProvider (
    private val navController: NavController,
) {
    fun navigate(route: ScreenRoute) {
        navController.navigate(route)
    }

    fun goBack() {
        if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
            navController.popBackStack()
        }
    }
}

val LocalNavigationManager = staticCompositionLocalOf<NavigationProvider> {
    error("No NavigationManager provided")
}