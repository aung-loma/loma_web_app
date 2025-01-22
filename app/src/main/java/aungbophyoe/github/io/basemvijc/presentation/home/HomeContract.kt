package aungbophyoe.github.io.basemvijc.presentation.home

import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aungbophyoe.github.io.basemvijc.core.base.BaseScreen
import aungbophyoe.github.io.basemvijc.core.navigation.LocalNavigationManager
import aungbophyoe.github.io.basemvijc.core.navigation.graphs.ScreenRoute
import aungbophyoe.github.io.basemvijc.core.uiComponents.dialog.OnDialogEvents

/**
 * Created by aungb on 1/22/2025.
 */

@Composable
fun HomeRoute() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val navigation = LocalNavigationManager.current
    BaseScreen(
        notifyEventsChannel = viewModel.notifyEventsChannel,
        dialogEvents = { event ->
            when (event) {
                OnDialogEvents.ON_RETRY -> {
                    viewModel.onAction(HomeAction.OnRefresh)
                }

                else -> Unit
            }
        }
    ) {
        HomeScreen(
            uiState = uiState,
            onNavigate = { route ->
                navigation.apply {
                    if (route is ScreenRoute.GoBack) {
                        goBack()
                    } else {
                        navigate(route = route)
                    }
                }
            },
            onAction = { action ->
                viewModel.onAction(action)
            }
        )
    }
}

sealed interface HomeAction {
    data object OnRefresh : HomeAction
}

@Keep
data class HomeUiState(val data : String? = null)