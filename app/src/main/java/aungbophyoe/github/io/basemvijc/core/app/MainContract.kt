package aungbophyoe.github.io.basemvijc.core.app

import androidx.annotation.Keep

/**
 * Created by aungb on 5/10/2025.
 */

@Keep
data class MainUiState(val isLoading: Boolean = false)

sealed interface MainAction {
    data object FetchSearchAPI : MainAction
}