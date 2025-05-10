package aungbophyoe.github.io.basemvijc.presentation.webView.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import aungbophyoe.github.io.basemvijc.core.navigation.graphs.ScreenRoute
import aungbophyoe.github.io.basemvijc.presentation.webView.WebViewRoute

/**
 * Created by aungb on 5/10/2025.
 */


internal fun NavGraphBuilder.webViewGraph() {
    composable<ScreenRoute.WebViewScreen> {
        WebViewRoute()
    }
}