package aungbophyoe.github.io.basemvijc.core.uiComponents.dialog

/**
 * Created by aungb on 1/22/2025.
 */

sealed class DialogEvent {

    data class SingleAction(
        val dialogData: DialogData,
        val onConfirm: () -> Unit,
    ) : DialogEvent()

    data class MultiAction(
        val dialogData: DialogData,
        val onConfirm: () -> Unit,
        val onDismiss: () -> Unit,
    ) : DialogEvent()

    data class Info(
        val dialogData: DialogData,
    ) : DialogEvent()

    data class ToggleProgress(
        val canShowProgress: Boolean? = null
    ) : DialogEvent()

    data class NoInternetDialog(
        val title: String,
        val message: String,
        val canShowProgress: Boolean,
        val onRetry: () -> Unit = {}
    ) : DialogEvent()

    data object Dismiss : DialogEvent()
}
