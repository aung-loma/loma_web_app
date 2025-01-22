package aungbophyoe.github.io.basemvijc.presentation.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import aungbophyoe.github.io.basemvijc.core.navigation.graphs.ScreenRoute
import aungbophyoe.github.io.basemvijc.presentation.home.HomeRoute

/**
 * Created by aungb on 1/22/2025.
 */

internal fun NavGraphBuilder.homeGraph() {
    composable<ScreenRoute.HomeScreen> {
        HomeRoute()
    }
}