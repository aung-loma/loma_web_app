package aungbophyoe.github.io.basemvijc.core.navigation.graphs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import aungbophyoe.github.io.basemvijc.core.navigation.anim.slideIntoEnterTransition
import aungbophyoe.github.io.basemvijc.core.navigation.anim.slideOutExitTransition
import aungbophyoe.github.io.basemvijc.core.navigation.anim.screenSlideIn
import aungbophyoe.github.io.basemvijc.core.navigation.anim.screenSlideOut
import aungbophyoe.github.io.basemvijc.core.uiComponents.dialog.DialogHost
import aungbophyoe.github.io.basemvijc.presentation.home.navigation.homeGraph

/**
 * Created by aungb on 1/22/2025.
 */

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .navigationBarsPadding(),
        navController = navController,
        startDestination = ScreenRoute.HomeScreen,
        enterTransition = slideIntoEnterTransition,
        exitTransition = slideOutExitTransition,
        popEnterTransition = { screenSlideIn() },
        popExitTransition = { screenSlideOut() }
    ) {
        homeGraph()
    }
    DialogHost()
}