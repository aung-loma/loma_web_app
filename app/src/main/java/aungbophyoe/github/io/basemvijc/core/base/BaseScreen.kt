package aungbophyoe.github.io.basemvijc.core.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import aungbophyoe.github.io.basemvijc.core.events.CollectEventsFromVM
import aungbophyoe.github.io.basemvijc.core.events.NotifyEvents
import aungbophyoe.github.io.basemvijc.core.navigation.LocalNavigationManager
import aungbophyoe.github.io.basemvijc.core.navigation.graphs.ScreenRoute
import aungbophyoe.github.io.basemvijc.core.resource.icons.AppIcons
import aungbophyoe.github.io.basemvijc.core.uiComponents.dialog.DialogData
import aungbophyoe.github.io.basemvijc.core.uiComponents.dialog.DialogManager
import aungbophyoe.github.io.basemvijc.core.uiComponents.dialog.DialogType
import aungbophyoe.github.io.basemvijc.core.uiComponents.dialog.OnDialogEvents
import aungbophyoe.github.io.basemvijc.core.util.findActivity
import aungbophyoe.github.io.basemvijc.core.util.showToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Created by aungb on 1/22/2025.
 */

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    notifyEventsChannel: Flow<NotifyEvents>,
    dialogEvents: (OnDialogEvents) -> Unit = {},
    content: @Composable () -> Unit,
) {
    val navigator = LocalNavigationManager.current
    val activity = LocalContext.current.findActivity()
    val keyboardManager = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier.fillMaxSize(),
        content = {
            CollectEventsFromVM(flow = notifyEventsChannel) { event ->
                when (event) {
                    is NotifyEvents.Navigate -> {
                        DialogManager.dismissDialog()
                        scope.launch {
                            keyboardManager?.hide()
                        }
                        if (event.route is ScreenRoute.GoBack) {
                            navigator.goBack()
                        } else {
                            navigator.navigate(event.route)
                        }
                    }

                    is NotifyEvents.ToggleLoading -> DialogManager.toggleProgress(event.isLoading)

                    is NotifyEvents.ShowToastMessage -> {
                        activity?.showToast(event.msg)
                    }

                    is NotifyEvents.ShowError -> {
                        if (event.dialogData.dialogType == DialogType.INFO) {
                            DialogManager.showInfoDialog(
                                dialogData = DialogData(
                                    title = event.dialogData.title,
                                    description = event.dialogData.description.toString(),
                                    infoIcon = AppIcons.icBrokenImage // change icon
                                )
                            )
                        } else {
                            DialogManager.showSingleActionDialog(event.dialogData) {
                                dialogEvents.invoke(OnDialogEvents.POSITIVE_CLICK)
                            }
                        }
                    }

                    is NotifyEvents.ShowDialog -> {
                        DialogManager.showMultiActionDialog(event.dialogData,
                            onConfirm = {},
                            onDismiss = {})
                    }
                    else -> Unit
                }
            }
            content()
        },
    )
}
