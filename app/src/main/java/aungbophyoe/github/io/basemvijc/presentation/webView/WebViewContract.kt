package aungbophyoe.github.io.basemvijc.presentation.webView

import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import aungbophyoe.github.io.basemvijc.core.base.BaseScreen
import aungbophyoe.github.io.basemvijc.core.navigation.LocalNavigationManager
import aungbophyoe.github.io.basemvijc.core.navigation.graphs.ScreenRoute
import aungbophyoe.github.io.basemvijc.core.providers.DataManager

/**
 * Created by aungb on 5/10/2025.
 */

@Composable
fun WebViewRoute() {
    val navigation = LocalNavigationManager.current
    val viewModel: WebViewViewModel = hiltViewModel()
    BaseScreen(
        notifyEventsChannel = viewModel.notifyEventsChannel,
        dialogEvents = {},
        content = {
            WebViewScreen(
                url = DataManager.getSearchProductData()?.h5_url ?: "https://h5-dev.yjhx88819.com/",
                onNavigate = { route ->
                    navigation.apply {
                        if (route is ScreenRoute.GoBack) {
                            goBack()
                        } else {
                            navigate(route = route)
                        }
                    }
                }
            )
        })
}

@Keep
data class WebViewState(
    val isLoading: Boolean = true,
)

sealed interface WebViewAction