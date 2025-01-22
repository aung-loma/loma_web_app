package aungbophyoe.github.io.basemvijc.core.events

import aungbophyoe.github.io.basemvijc.core.navigation.graphs.ScreenRoute
import aungbophyoe.github.io.basemvijc.core.uiComponents.dialog.DialogData

/**
 * Created by aungb on 1/20/2025.
 */

interface NotifyEvents {
    data class Navigate(val route: ScreenRoute, val currentRoute: ScreenRoute? = null) : NotifyEvents

    data class ToggleLoading(val isLoading: Boolean) : NotifyEvents

    data class ShowDialog(
        val dialogData: DialogData,
    ) : NotifyEvents

    data class ShowToastMessage(val msg: String) : NotifyEvents

    data class ShowError(
        val dialogData: DialogData,
        val errorCode: Int? = null,
        val errorStr: String? = null,
    ) : NotifyEvents
}